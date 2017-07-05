package com.dkmp.common.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class WebMessageAdder {
	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d", message));
	}

	public static void addInfoMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));
	}
}
