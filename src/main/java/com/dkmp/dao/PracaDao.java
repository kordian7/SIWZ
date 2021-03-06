package com.dkmp.dao;

import java.util.List;

import com.dkmp.model.Praca;

public interface PracaDao {

	Praca getStudentPraca(Long userId);
	
	List<Praca> getPromotorPrace(Long userId);
	
	Praca getPracaById(Long pracaId);
	
	boolean zatwierdzPropozycjeRecenzentow(Praca praca);

	boolean przeslijPropozycjeRecenzentowStudenta(Praca praca);

	boolean przeslijPropozycjeRecenzentowPromotora(Praca praca);
	
}
