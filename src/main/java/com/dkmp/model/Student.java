package com.dkmp.model;

public class Student {
	private long id;
	private String imie;
	private String nazwisko;
	private Praca praca;
	
	public Student() {
		
	}

	public Student(long id, String imie, String nazwisko) {
		super();
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
	}
	
	public String getFormattedInfo() { 
		return imie + " " + nazwisko;
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
	public Praca getPraca() {
		return praca;
	}
	public void setPraca(Praca praca) {
		this.praca = praca;
	}
	
}
