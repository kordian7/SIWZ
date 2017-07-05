package com.dkmp.dao.impl;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dkmp.common.utils.BundleProducer;
import com.dkmp.dao.RecenzentDao;
import com.dkmp.model.Recenzent;

@Service
public class RecenzentDaoImpl implements RecenzentDao {

	@Inject
	BundleProducer bundleProducer;
	
	@Override
	public List<Recenzent> getAllRecenzenci() {
		Recenzent[] recArr = new RestTemplate().getForObject(bundleProducer.getBundle().getString("server.url") + "/reviewer/all", Recenzent[].class);
		List<Recenzent> recs = Arrays.asList(recArr);
 		return recs;
	}
	
}
