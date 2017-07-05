package com.dkmp.auth.dao.impl;

import javax.inject.Inject;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dkmp.auth.dao.LoginDao;
import com.dkmp.auth.dto.LoginRequest;
import com.dkmp.auth.dto.LoginResponse;
import com.dkmp.auth.exceptions.AuthenticationException;
import com.dkmp.common.utils.BundleProducer;

@Service
public class LoginDaoImpl implements LoginDao {
	
	@Inject
	BundleProducer bundleProducer;
	
	@Override
	public LoginResponse authenticateUser(String username, String password) throws AuthenticationException {
		LoginResponse loginResponse;
		HttpEntity<LoginRequest> request = new HttpEntity<>(new LoginRequest(username, password));
		try {
			ResponseEntity<LoginResponse> response = new RestTemplate()
					.exchange(bundleProducer.getBundle().getString("server.url") + "/session/login", HttpMethod.POST, request, LoginResponse.class);
			loginResponse = response.getBody();
			System.out.println("Response: " + loginResponse.getToken() + " " + loginResponse.getRole() + " "
					+ response.getStatusCode());
		} catch (Exception e) {
			throw new AuthenticationException();
		}
		return loginResponse;

	}

}
