package com.example.Bioskop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Terminski_raspored implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//svaki raspored je za jedan film,dok jedan film moze imati vise raporeda
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn
	private Film film;
	
	@Column
	private Date datumOdrzavanja;
	@Column
	private String vremePocetka;
	@Column
	private int cena;
	@Column
	private int brojRezervacija;
	
	//persist u slucaju da obrisem terminski raspored,da mi se ne obrise ta sala iz baze
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn
	private Sala sala;
	
	@ManyToMany(mappedBy="rezervisani_filmovi")
	private Set<Gledalac> gledaoci_koji_su_rezervisali_film=new HashSet<>();

	@OneToMany(mappedBy="terminski_raspored",cascade=CascadeType.ALL)
	private Set<Raspored_filmova> rasporedi=new HashSet<>();
	
	public Terminski_raspored () {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public Date getDatum_odrzavanja() {
		return datumOdrzavanja;
	}
	public void setDatum_odrzavanja(Date datum_odrzavanja) {
		this.datumOdrzavanja = datum_odrzavanja;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public int getBroj_rezervacija() {
		return brojRezervacija;
	}
	public void setBroj_rezervacija(int broj_rezervacija) {
		this.brojRezervacija = broj_rezervacija;
	}
	public String getVreme_pocetka() {
		return vremePocetka;
	}
	public void setVreme_pocetka(String vreme_pocetka) {
		this.vremePocetka = vreme_pocetka;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	public Set<Raspored_filmova> getRasporedi() {
		return rasporedi;
	}
	public void setRasporedi(Set<Raspored_filmova> rasporedi) {
		this.rasporedi = rasporedi;
	}
	public Set<Gledalac> getGledaoci_koji_su_rezervisali_film() {
		return gledaoci_koji_su_rezervisali_film;
	}
	public void setGledaoci_koji_su_rezervisali_film(Set<Gledalac> gledaoci_koji_su_rezervisali_film) {
		this.gledaoci_koji_su_rezervisali_film = gledaoci_koji_su_rezervisali_film;
	}
	@Override
	public String toString() {
		return "Terminski raspored {id=" + id + ", naziv_filma=" + film.getNaziv()+ ", datum_odrzavanja=" + datumOdrzavanja
				+ ", pocetak=" + vremePocetka + ", cena=" + cena + ", broj_rezervacija=" + brojRezervacija + "}";
	}
	
	
	
	
	

}
