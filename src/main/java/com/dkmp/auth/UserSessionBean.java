package com.dkmp.auth;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope(value="session")
public class UserSessionBean {
	private String username;
	private Long userId;
	private AppRole appRole;
	
	@PostConstruct
	public void init() {
		System.out.println("UserSessionBean.init()");
	}
	
	public void initialize(String username, Long userId, AppRole appRole) {
		this.username = username;
		this.userId = userId;
		this.appRole = appRole;
	}
	
	public void invalidate() {
		this.username = null;
		this.userId = null;
		this.appRole = null;
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

	public AppRole getAppRole() {
		return appRole;
	}

	public void setAppRole(AppRole appRole) {
		this.appRole = appRole;
	}
	
}
