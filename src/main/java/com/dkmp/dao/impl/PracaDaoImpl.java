package com.dkmp.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dkmp.dao.PracaDao;
import com.dkmp.model.Praca;
import com.dkmp.model.Praca.Status;
import com.dkmp.model.Promotor;
import com.dkmp.model.Student;

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
		praca.setTytulPracy("Pierwsza praca in퓓nierska");
		//praca.setStatus(Praca.Status.WAITING_FOR_REC_CHOOSE);
		praca.setStatus(Praca.Status.WAITING_FOR_STUDENT_REC_CONFIRM);
		//praca.setStatus(Praca.Status.WAITING_FOR_PROMOTOR_REC_CONFIRM);
		//praca.setStatus(Praca.Status.REC_CONFIRMED);
		praca.setPromotor(promotor);
		return praca;
	}

	@Override
	// TODO - mock
	public List<Praca> getPromotorPrace(Long userId) {
		
		Praca praca1 = new Praca();
		praca1.setIdPracy(1L);
		praca1.setTytulPracy("Pierwsza praca in퓓nierska");
		praca1.setStatus(Status.WAITING_FOR_REC_CHOOSE);
		praca1.setStudent(new Student(11L, "Marek", "Nowak"));
		
		Praca praca2 = new Praca();
		praca2.setIdPracy(2L);
		praca2.setTytulPracy("Druga praca in퓓nierska");
		praca2.setStatus(Status.WAITING_FOR_PROMOTOR_REC_CONFIRM);
		praca2.setStudent(new Student(12L, "Norbert", "Nowak"));
		
		Praca praca3 = new Praca();
		praca3.setIdPracy(3L);
		praca3.setTytulPracy("Trzecia praca in퓓nierska");
		praca3.setStatus(Status.WAITING_FOR_STUDENT_REC_CONFIRM);
		praca3.setStudent(new Student(13L, "Olgierd", "Nowak"));
		
		Praca praca4 = new Praca();
		praca4.setIdPracy(4L);
		praca4.setTytulPracy("Czwarta praca in퓓nierska");
		praca4.setStatus(Status.REC_CONFIRMED);
		praca4.setStudent(new Student(14L, "Pawe�", "Nowak"));
		
		return Arrays.asList(praca1, praca2, praca3, praca4);
	}

	// TODO - mock
	@Override
	public Praca getPracaById(Long pracaId) {
		Praca praca = new Praca();
		Promotor promotor = new Promotor();
		promotor.setImie("Jan");
		promotor.setNazwisko("Kowalski");
		promotor.setId(1L);
		
		Student student = new Student(11L, "Marek", "Nowak");
		
		praca.setIdPracy(1L);
		praca.setTytulPracy("Pierwsza praca in퓓nierska");
		//praca.setStatus(Praca.Status.WAITING_FOR_REC_CHOOSE);
		//praca.setStatus(Praca.Status.WAITING_FOR_STUDENT_REC_CONFIRM);
		praca.setStatus(Praca.Status.WAITING_FOR_PROMOTOR_REC_CONFIRM);
		//praca.setStatus(Praca.Status.REC_CONFIRMED);
		praca.setPromotor(promotor);
		praca.setStudent(student);
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
