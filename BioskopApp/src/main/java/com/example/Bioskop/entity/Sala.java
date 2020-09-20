 package com.example.Bioskop.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Sala implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private int kapacitet;
	@Column
	private String oznakaSale;
	
	//sala pripada jednom bioskopu
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.PERSIST)
	private Bioskop bioskop;

	//sala ima vise terminskih rasporeda,ako obrisem salu,obrisace mi se teminski raspored koji je u njoj
	@OneToMany(mappedBy="sala",cascade=CascadeType.REMOVE)
	private List<Terminski_raspored> projekcije=new ArrayList<>();
 
	public Sala(){}
	
	public Sala(int kapacitet, String oznakaSale, Bioskop bioskop) {
		super();
		this.kapacitet = kapacitet;
		this.oznakaSale = oznakaSale;
		this.bioskop = bioskop;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public String getOznaka_sale() {
		return oznakaSale;
	}

	public void setOznaka_sale(String oznakaSale) {
		this.oznakaSale = oznakaSale;
	}

	public Bioskop getBioskop() {
		return bioskop;
	}

	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}
	
	public List<Terminski_raspored> getProjekcije() {
		return projekcije;
	}


	public void setProjekcije(List<Terminski_raspored> projekcije) {
		this.projekcije = projekcije;
	}


	/*public Set<Terminski_raspored> getProjekcije() {
		return projekcije;
	}

	public void setProjekcije(Set<Terminski_raspored> projekcije) {
		this.projekcije = projekcije;
	}
	*/
	@Override
	public String toString() {
		return "Sala {id=" + id + ", kapacitet=" + kapacitet + ", oznaka_sale=" + oznakaSale + ",bioskop="+bioskop+"}";
	}
	
}
