package com.dkmp.auth;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope(value="session")
public class UserSessionBean {
	private String username;
	private Long userId;
	
	@PostConstruct
	public void init() {
		System.out.println("UserSessionBean.init()");
	}
	
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
