package com.dkmp.auth;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;

import com.dkmp.auth.dao.LoginDao;
import com.dkmp.auth.dto.LoginResponse;
import com.dkmp.auth.exceptions.AuthenticationException;
import com.dkmp.auth.utils.SessionUtils;

@Named
@Scope("session")
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	LoginDao loginDao;

	@Inject
	UserSessionBean userSessionBean;
	
	private String username;
	private String password;

	public String validateUser() {
		try {
			LoginResponse loginResponse = loginDao.authenticateUser(getUsername(), getPassword());
			HttpSession session = SessionUtils.getSession();
			Long userId = Long.valueOf(loginResponse.getToken());
			session.setAttribute("username", getUsername());
			session.setAttribute("userId", userId);
			userSessionBean.initialize(getUsername(), userId, loginResponse.getRole());
			return "home";
		} catch (AuthenticationException e) {
			throwLoginErrorMessage();
			return null;
		}
	}
	
	public String logout() {
		SessionUtils.getSession().invalidate();
		userSessionBean.invalidate();
		return "login";
	}

	private void throwLoginErrorMessage() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³êdne dane logowania", "Podaj poprawne dane"));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
