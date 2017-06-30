package com.dkmp.auth.dao.impl;

import org.springframework.stereotype.Service;

import com.dkmp.auth.dao.LoginDao;

@Service // TODO @Repository
public class LoginDaoImpl implements LoginDao {

	// TODO - prawdziwa walidacja z bazy
	
	@Override
	public boolean validate(String username, String password) {
		return username.toLowerCase().equals("test") && password.toLowerCase().equals("test");
	}

}
