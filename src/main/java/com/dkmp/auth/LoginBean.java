package com.dkmp.auth;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.dkmp.auth.dao.LoginDao;

@Named
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	LoginDao loginDao;

	private String username;
	private String password;

	public String validateUser() {
		// TODO stworzenie sesji
		if (loginDao.validate(getUsername(), getPassword()))
			return "home";
		else {
			throwLoginErrorMessage();
			return "login";
		}
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
