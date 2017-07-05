package com.dkmp.auth.dao;

import com.dkmp.auth.dto.LoginResponse;
import com.dkmp.auth.exceptions.AuthenticationException;

public interface LoginDao {
	LoginResponse authenticateUser(String username, String password) throws AuthenticationException;
}
