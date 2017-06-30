package com.dkmp;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.dkmp.dao.TestDao;

@Component
@ManagedBean
public class HelloBean {

	@Inject
	TestDao testDao;
	
	public String getMessage() {
		return "Hello World" + testDao.getTest();
	}
}
