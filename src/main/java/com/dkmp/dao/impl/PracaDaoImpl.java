package com.dkmp.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dkmp.dao.PracaDao;
import com.dkmp.model.Praca;

@Service
public class PracaDaoImpl implements PracaDao {

	@Override
	public Praca getStudentPraca(Long userId) {
		Praca praca = new RestTemplate().getForObject("http://a056503e.ngrok.io/student/" + userId + "/task", Praca.class);
		return praca;
	}

	@Override
	public List<Praca> getPromotorPrace(Long userId) {
		Praca[] praceArr = new RestTemplate().getForObject("http://a056503e.ngrok.io/prometer/" + userId + "/tasks", Praca[].class);
		List<Praca> prace = Arrays.asList(praceArr);
 		return prace;
	}

	@Override
	public Praca getPracaById(Long pracaId) {
		Praca praca = new RestTemplate().getForObject("http://a056503e.ngrok.io/task/" + pracaId, Praca.class);
		return praca;
	}

	@Override
	public boolean przeslijPropozycjeRecenzentowStudenta(Praca praca) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean przeslijPropozycjeRecenzentowPromotora(Praca praca) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zatwierdzPropozycjeRecenzentow(Praca praca) {
		// TODO Auto-generated method stub
		return false;
	}

}
