package com.example.Bioskop.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Bioskop implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	@Column
	private String adresa;
	@Column
	private String brojCentrale;
	@Column
	private String email;
	
	//jedan bioskop=jedan menadzer
	//cascade je persist jer ne zelim da mi se obrise menadzer ako obrisem bioskop,posto je taj menadzer mozda zaduzen za jos neki bioskop
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Menadzer menadzer;
	
	//sale koje pripadaju tom bioskopu, dok jedna sala pripada samo jednom bioskopu
	//orphanRemoval je true jer ako se izbrise sala iz liste,treba se izbrisati i iz baze jer sala moze pripadati samo jednom bioskopu
	@OneToMany(mappedBy = "bioskop", fetch = FetchType.LAZY)
    private Set<Sala> sale = new HashSet<>();
	
	//bioskop ima vise rasporeda
	@OneToMany(mappedBy="bioskop")
	private List<Raspored_filmova> rasporedi=new ArrayList<>();
	
	public Bioskop() {}
	
	public Bioskop(String naziv, String adresa, String brojCentrale, String eMail) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.brojCentrale = brojCentrale;
		this.email = eMail;
	}

	/*public Set<Raspored_filmova> getRasporedi() {
		return rasporedi;
	}

	public void setRasporedi(Set<Raspored_filmova> rasporedi) {
		this.rasporedi = rasporedi;
	}*/
	
	public Long getId() {
		return id;
	}

	public List<Raspored_filmova> getRasporedi() {
		return rasporedi;
	}

	public void setRasporedi(List<Raspored_filmova> rasporedi) {
		this.rasporedi = rasporedi;
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrojCentrale() {
		return brojCentrale;
	}

	public void setBrojCentrale(String broj_centrale) {
		this.brojCentrale = broj_centrale;
	}

	public String getEMail() {
		return email;
	}

	public void setEMail(String e_mail) {
		this.email = e_mail;
	}

	public Menadzer getMenadzer() {
		return menadzer;
	}

	public void setMenadzer(Menadzer menadzer) {
		this.menadzer = menadzer;
	}

	public Set<Sala> getSale() {
		return sale;
	}

	public void setSale(Set<Sala> sale) {
		this.sale = sale;
	}
	
	@Override
	public String toString() {
		return "Bioskop {id=" + id + ", naziv=" + naziv + ", adresa=" + adresa + ", broj_centrale=" + brojCentrale
				+ ", e_mail=" + email + "}";
	}
	
}
