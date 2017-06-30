package com.dkmp.auth.dao;

public interface LoginDao {
	boolean validate(String username, String password);
}
