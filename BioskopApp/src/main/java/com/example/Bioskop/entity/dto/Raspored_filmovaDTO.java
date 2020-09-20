package com.example.Bioskop.entity.dto;

import com.example.Bioskop.entity.Bioskop;
import com.example.Bioskop.entity.Terminski_raspored;

public class Raspored_filmovaDTO {
	private Long id;
	private Bioskop bioskop;
	private Terminski_raspored t;
	
	public Raspored_filmovaDTO() {}
	
	public Raspored_filmovaDTO(Long id,Bioskop bioskop, Terminski_raspored t) {
		super();
		this.id=id;
		this.bioskop = bioskop;
		this.t = t;
	}

	public Bioskop getBioskop() {
		return bioskop;
	}
	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}
	public Terminski_raspored getT() {
		return t;
	}
	public void setT(Terminski_raspored t) {
		this.t = t;
	}

}
