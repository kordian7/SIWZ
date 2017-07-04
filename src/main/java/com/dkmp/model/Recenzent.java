package com.dkmp.model;

public class Recenzent {

	private String imie;
	private String nazwisko;
	private int ilPrac;
	private boolean isAvailable;
	
	public Recenzent() {}
	
	public Recenzent(String imie, String nazwisko, int ilPrac) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.ilPrac = ilPrac;
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
	public int getIlPrac() {
		return ilPrac;
	}
	public void setIlPrac(int ilPrac) {
		this.ilPrac = ilPrac;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Recenzent [imie=" + imie + ", nazwisko=" + nazwisko + "]";
	}
	
}