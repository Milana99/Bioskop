package com.example.Bioskop.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Korisnik implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	protected String korisnickoIme;
	@Column
	protected String lozinka;
	@Column
	protected String ime;
	@Column
	protected String prezime;
	@Column
	protected String kontaktTelefon;
	@Column
	protected String email;
	@Column
	protected String datumRodjenja;
	@Column
	protected String uloga;
	@Column
	protected Boolean aktivan;
	
	
	public Korisnik() {}
	
	public Korisnik(Long id,String korisnickoIme, String lozinka, String ime, String prezime, String kontaktTelefon,
			String eadresa, String datumRodjenja,String uloga) {
		super();
		this.id=id;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.kontaktTelefon = kontaktTelefon;
		this.email = eadresa;
		this.datumRodjenja = datumRodjenja;
		this.uloga=uloga;
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
	public void setKorisnickoIme(String korisnicko_ime) {
		this.korisnickoIme = korisnicko_ime;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
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
	public String getKontaktTelefon() {
		return kontaktTelefon;
	}
	public void setKontaktTelefon(String kontakt_telefon) {
		this.kontaktTelefon = kontakt_telefon;
	}
	public String getEMail() {
		return email;
	}
	public void setEMail(String e_adresa) {
		this.email= e_adresa;
	}
	public String getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(String datum_rodjenja) {
		this.datumRodjenja = datum_rodjenja;
	}
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	public Boolean isAktivan() {
		return aktivan;
	}
	public void setAktivan(Boolean aktivan) {
		this.aktivan = aktivan;
	}
	
	/*@Override
	public String toString() {
		return "Korisnik {id=" + id + ", korisnicko_ime=" + korisnicko_ime + ", ime=" + ime + ", prezime=" + prezime
				+ ", lozinka=" + lozinka + ", kontakt_telefon=" + kontakt_telefon + ", e_adresa=" + e_adresa
				+ ", datum_rodjenja=" + datum_rodjenja + ", uloga=" + uloga + ", aktivan=" + aktivan + "}";
	}
	*/
	
}
