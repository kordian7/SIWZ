package com.dkmp.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dkmp.dao.RecenzentDao;
import com.dkmp.model.Recenzent;

@Service
public class RecenzentDaoImpl implements RecenzentDao {

	@Override
	public List<Recenzent> getAllRecenzenci() {
		Recenzent[] recArr = new RestTemplate().getForObject("http://a056503e.ngrok.io/reviewer/all", Recenzent[].class);
		List<Recenzent> recs = Arrays.asList(recArr);
 		return recs;
	}
	
}
