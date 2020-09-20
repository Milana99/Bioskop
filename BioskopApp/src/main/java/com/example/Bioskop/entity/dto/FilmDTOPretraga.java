package com.example.Bioskop.entity.dto;

import java.util.Date;

public class FilmDTOPretraga {
	private Long id;
	private String naziv;
	private String opis;
	private String zanr;
	private Double srednjaOcena;
	private int cena;
	private Date datumOdrzavanja;
	
	public FilmDTOPretraga(Long id, String naziv, String opis, String zanr, Double srednjaOcena, int cena, Date datumOdrzavanja) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.zanr = zanr;
		this.srednjaOcena = srednjaOcena;
		this.cena = cena;
		this.datumOdrzavanja = datumOdrzavanja;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getZanr() {
		return zanr;
	}
	public void setZanr(String zanr) {
		this.zanr = zanr;
	}
	public Double getSrednjaOcena() {
		return srednjaOcena;
	}
	public void setSrednjaOcena(Double srednjaOcena) {
		this.srednjaOcena = srednjaOcena;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public Date getDatumOdrzavanja() {
		return datumOdrzavanja;
	}
	public void setDatumOdrzavanja(Date datumOdrzavanja) {
		this.datumOdrzavanja = datumOdrzavanja;
	}

}
