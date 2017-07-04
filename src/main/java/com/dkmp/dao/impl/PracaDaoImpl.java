package com.dkmp.dao.impl;

import org.springframework.stereotype.Service;

import com.dkmp.dao.PracaDao;
import com.dkmp.model.Praca;
import com.dkmp.model.Promotor;

@Service
public class PracaDaoImpl implements PracaDao {

	@Override
	// TODO - mock
	public Praca getStudentPraca(Long userId) {
		Praca praca = new Praca();
		Promotor promotor = new Promotor();
		promotor.setImie("Jan");
		promotor.setNazwisko("Kowalski");
		promotor.setId(1L);
		praca.setIdPracy(1L);
		praca.setTytulPracy("Pierwsza praca in¿ynierska");
		praca.setStatus(Praca.Status.WAITING_FOR_REC_CHOOSE);
		praca.setPromotor(promotor);
		return praca;
	}

}
