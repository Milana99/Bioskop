package com.example.Bioskop.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Menadzer extends Korisnik{
	
	//jedan menadzer ima vise bioskopa, i cascadeType je PERSIST jer ne zelim da izbrisem Bioskop ako izbrisem Menadzera

	@OneToMany(mappedBy="menadzer",fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Bioskop> bioskopi=new HashSet<>();

	public Set<Bioskop> getBioskopi() {
		return bioskopi;
	}

	public void setBioskopi(Set<Bioskop> bioskopi) {
		this.bioskopi = bioskopi;
	}

	
}
