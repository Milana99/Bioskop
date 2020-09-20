package com.example.Bioskop.entity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Gledalac extends Korisnik{
	
	@ManyToMany
	@JoinTable(name="lista_odgledanih_filmova",
	joinColumns = @JoinColumn(name = "gledalac_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"))
	private Set<Film> odgledani_filmovi=new HashSet<>();
	
	
	@ManyToMany
	@JoinTable(name="lista_rezervisanih_filmova",
	joinColumns = @JoinColumn(name = "gledalac_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "terminski_raspored_id", referencedColumnName = "id"))
	private Set<Terminski_raspored> rezervisani_filmovi=new HashSet<>();
	
	@ManyToMany
	@JoinTable(name="lista_ocenjenih_filmova",
	joinColumns = @JoinColumn(name = "gledalac_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "ocena_id", referencedColumnName = "id"))
	private Set<Ocena> ocene=new HashSet<>();

	public Set<Film> getOdgledani_filmovi() {
		return odgledani_filmovi;
	}

	public void setOdgledani_filmovi(Set<Film> odgledani_filmovi) {
		this.odgledani_filmovi = odgledani_filmovi;
	}

	public Set<Terminski_raspored> getRezervisani_filmovi() {
		return rezervisani_filmovi;
	}

	public void setRezervisani_filmovi(Set<Terminski_raspored> rezervisani_filmovi) {
		this.rezervisani_filmovi = rezervisani_filmovi;
	}

	public Set<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}
	
}
