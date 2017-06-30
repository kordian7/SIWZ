package com.dkmp.auth;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class UserSessionBean {
	private String username;
	private Long userId;
	
	public void initialize(String username, Long userId) {
		this.username = username;
		this.userId = userId;
	}
	
	public void invalidate() {
		this.username = null;
		this.userId = null;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
