package com.example.Bioskop.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Raspored_filmova implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//raspored ima jedan bioskop,dok bioskop moze biti u vise rasporeda
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn
	private Bioskop bioskop;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn
	private Terminski_raspored terminski_raspored;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Bioskop getBioskop() {
		return bioskop;
	}

	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}

	public Terminski_raspored getTerminski_raspored() {
		return terminski_raspored;
	}

	public void setTerminski_raspored(Terminski_raspored terminski_raspored) {
		this.terminski_raspored = terminski_raspored;
	}
	
}
