package com.dkmp.dao.impl;

import org.springframework.stereotype.Service;

import com.dkmp.dao.TestDao;

@Service
public class TestDaoImpl implements TestDao {
	
	@Override
	public String getTest() {
		return "test123";
	}
}
