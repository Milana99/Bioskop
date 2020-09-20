package com.example.Bioskop.entity.dto;

public class MenadzerDTO {
	private Long id;
	private String korisnickoIme;
	private String ime;
	private String prezime;

	public MenadzerDTO(Long id, String korisnickoIme, String ime, String prezime) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

}
