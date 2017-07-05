package com.dkmp.model;

import java.util.ArrayList;
import java.util.List;

public class Promotor {
	private long id;
	private String imie;
	private String nazwisko;
	private List<Praca> przypisanePrace;
	
	public Promotor() {
		przypisanePrace = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getFormattedInfo() { 
		return imie + " " + nazwisko;
	}
	
	public List<Praca> getPrzypisanePrace() {
		return przypisanePrace;
	}

	public void setPrzypisanePrace(List<Praca> przypisanePrace) {
		this.przypisanePrace = przypisanePrace;
	}
	
}
