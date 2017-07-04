package com.dkmp.model;

import java.util.ArrayList;
import java.util.List;

public class Praca {
	
	public enum Status {
		WAITING_FOR_REC_CHOOSE,
		WAITING_FOR_PROMOTOR_REC_CONFIRM,
		WAITING_FOR_STUDENT_REC_CONFIRM,
		REC_CONFIRMED
	}
	
	private long idPracy;
	private String tytulPracy;
	private Student student;
	private Promotor promotor;
	private List<Recenzent> listaRecenzentow;
	private List<Recenzent> listaProponowanychRecenzentow;
	
	private Status status;
	
	public Praca() {
		listaRecenzentow = new ArrayList<>();
		listaProponowanychRecenzentow  = new ArrayList<>();
	}

	public long getIdPracy() {
		return idPracy;
	}

	public void setIdPracy(long idPracy) {
		this.idPracy = idPracy;
	}

	public String getTytulPracy() {
		return tytulPracy;
	}

	public void setTytulPracy(String tytulPracy) {
		this.tytulPracy = tytulPracy;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Promotor getPromotor() {
		return promotor;
	}

	public void setPromotor(Promotor promotor) {
		this.promotor = promotor;
	}

	public List<Recenzent> getListaRecenzentow() {
		return listaRecenzentow;
	}

	public void setListaRecenzentow(List<Recenzent> listaRecenzentow) {
		this.listaRecenzentow = listaRecenzentow;
	}

	public List<Recenzent> getListaProponowanychRecenzentow() {
		return listaProponowanychRecenzentow;
	}

	public void setListaProponowanychRecenzentow(List<Recenzent> listaProponowanychRecenzentow) {
		this.listaProponowanychRecenzentow = listaProponowanychRecenzentow;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
