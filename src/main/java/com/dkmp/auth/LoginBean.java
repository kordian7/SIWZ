package com.dkmp.auth;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.dkmp.auth.dao.LoginDao;
import com.dkmp.auth.utils.SessionUtils;

@Named
@RequestScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	LoginDao loginDao;

	@Inject
	UserSessionBean userSessionBean;
	
	private String username;
	private String password;

	public String validateUser() {
		// TODO stworzenie sesji
		if (loginDao.validate(getUsername(), getPassword())) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", getUsername());
			session.setAttribute("userId", 1L /*TODO*/);
			userSessionBean.initialize(getUsername(), 1L);
			return "home?faces-redirect=true";
		} else {
			throwLoginErrorMessage();
			return "login?faces-redirect=true";
		}
	}
	
	public String logout() {
		SessionUtils.getSession().invalidate();
		userSessionBean.invalidate();
		return "login?faces-redirect=true";
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
