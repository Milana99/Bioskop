package com.example.Bioskop.entity.dto;

public class SalaDTO {
	private Long id;
	private String oznakaSale;
	private int kapacitet;
	private String bioskop;
	
	public SalaDTO () {}

	public SalaDTO(Long id, String oznakaSale, int kapacitet, String bioskop) {
		super();
		this.id = id;
		this.oznakaSale = oznakaSale;
		this.kapacitet = kapacitet;
		this.bioskop = bioskop;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOznakaSale() {
		return oznakaSale;
	}

	public void setOznakaSale(String oznakaSale) {
		this.oznakaSale = oznakaSale;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public String getBioskop() {
		return bioskop;
	}

	public void setBioskop(String bioskop) {
		this.bioskop = bioskop;
	}
	
}
