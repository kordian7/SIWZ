package com.dkmp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recenzent {

	private long id;
	private String imie;
	private String nazwisko;
	private int ilPrac;
	private boolean isAvailable;
	
	public Recenzent() {}
	
	public Recenzent(long id, String imie, String nazwisko, int ilPrac) {
		super();
		this.id = id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFormattedInfo() { 
		return imie + " " + nazwisko;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recenzent other = (Recenzent) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recenzent [imie=" + imie + ", nazwisko=" + nazwisko + "]";
	}
	
}