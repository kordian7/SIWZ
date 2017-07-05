package com.dkmp.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Praca {
	
	public enum Status {
		WAITING_FOR_REC_CHOOSE("Oczekuj¹ca na wybór recenzentów przez studenta"),
		WAITING_FOR_PROMOTOR_REC_CONFIRM("Oczekuj¹ca na potwierdzenie recenzentów przez promotora"),
		WAITING_FOR_STUDENT_REC_CONFIRM("Oczekuj¹ca na potwierdzenie recenzentów przez studenta"),
		REC_CONFIRMED("Recenzenci potwierdzeni");
		
		private String string;
		
		Status(String string) {
			this.string = string;
		}
		
		@Override
		public String toString() {
			return string;
		}
	}
	
	private long idPracy;
	private String tytulPracy;
	private Student student;
	private Promotor promotor;
	private List<Recenzent> listaRecenzentow;
	private List<Recenzent> listaProponowanychRecenzentow;
	private Status status;
	
	private boolean pracaOk = true;
	private String pracaValidationError;
	
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

	public boolean isPracaOk() {
		return pracaOk;
	}
	
	public void setPracaOk(boolean pracaOk) {
		this.pracaOk = pracaOk;
	}

	public String getPracaValidationError() {
		return pracaValidationError;
	}

	public void setPracaValidationError(String pracaValidationError) {
		this.pracaValidationError = pracaValidationError;
	}
	
}
