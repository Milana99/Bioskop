 package com.example.Bioskop.entity.dto;

import java.util.Date;

import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Sala;

public class Terminski_rasporedDTO {
	private Long id;
	private String naziv;
	private String zanr;
	private String opis;
	private int trajanje;
	private Double srednjaOcena;
	private Date datumOdrzavanja;
	private String vremePocetka;
	private int cena;
	private int brojRezervacija;
	private String salaOznaka;
	private String bioskop;
	private Long gledalacId;
	
	public Long getGledalacId() {
		return gledalacId;
	}
	public void setGledalacId(Long gledalacId) {
		this.gledalacId = gledalacId;
	}
	public String getSalaOznaka() {
		return salaOznaka;
	}
	public void setSalaOznaka(String salaOznaka) {
		this.salaOznaka = salaOznaka;
	}
	public String getBioskop() {
		return bioskop;
	}
	public void setBioskop(String bioskop) {
		this.bioskop = bioskop;
	}
	public Terminski_rasporedDTO() {}
	/*public Terminski_rasporedDTO(Long id, Film film, Date datumOdrzavanja, String vrijemePocetka, int cijena,
			int brojRezervacija, Sala sala) {
		super();
		this.id = id;
		this.film = film;
		this.datumOdrzavanja = datumOdrzavanja;
		this.vrijemePocetka = vrijemePocetka;
		this.cijena = cijena;
		this.brojRezervacija = brojRezervacija;
		this.sala = sala;
	}*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDatumOdrzavanja() {
		return datumOdrzavanja;
	}
	public void setDatumOdrzavanja(Date datumOdrzavanja) {
		this.datumOdrzavanja = datumOdrzavanja;
	}
	public String getVremePocetka() {
		return vremePocetka;
	}
	public void setVremePocetka(String vremePocetka) {
		this.vremePocetka = vremePocetka;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public int getBrojRezervacija() {
		return brojRezervacija;
	}
	public void setBrojRezervacija(int brojRezervacija) {
		this.brojRezervacija = brojRezervacija;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getZanr() {
		return zanr;
	}
	public void setZanr(String zanr) {
		this.zanr = zanr;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public int getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}
	public Double getSrednjaOcena() {
		return srednjaOcena;
	}
	public void setSrednjaOcena(Double srednjaOcena) {
		this.srednjaOcena = srednjaOcena;
	}

}
