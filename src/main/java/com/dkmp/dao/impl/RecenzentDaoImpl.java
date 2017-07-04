package com.dkmp.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dkmp.dao.RecenzentDao;
import com.dkmp.model.Recenzent;

@Service
public class RecenzentDaoImpl implements RecenzentDao {

	@Override
	public List<Recenzent> getAllRecenzenci() {
		// TODO
		Recenzent rec1 = new Recenzent(1L, "Adam", "Kowalski", 3);
		Recenzent rec2 = new Recenzent(2L, "Bartosz", "Kowalski", 6);
		Recenzent rec3 = new Recenzent(3L, "Cezary", "Kowalski", 5);
		Recenzent rec4 = new Recenzent(4L, "Damian", "Kowalski", 7);
		Recenzent rec5 = new Recenzent(5L, "Emil", "Kowalski", 2);
		Recenzent rec6 = new Recenzent(6L, "Franciszek", "Kowalski", 1);

		List<Recenzent> recs = Arrays.asList(rec1, rec2, rec3, rec4, rec5, rec6);
		return recs;
	}
	
}
