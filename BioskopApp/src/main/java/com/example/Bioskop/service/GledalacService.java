package com.example.Bioskop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bioskop.entity.Gledalac;
import com.example.Bioskop.entity.Korisnik;
import com.example.Bioskop.repository.GledalacRepository;

@Service
public class GledalacService {
	
	@Autowired
	private GledalacRepository gledalacRepository;
	
	
	//da nadjem odredjenog gledaoca
	public Gledalac findOne(Long id) {
		Gledalac gledalac=this.gledalacRepository.getOne(id);
		return gledalac;
	}
	
	//sve gledaoce
	public List<Gledalac> findAll(){
		List<Gledalac> gledaoci=this.gledalacRepository.findAll();
		return gledaoci;
	}
	
	//cuvanje odredjenog gledaoca
	public Gledalac save(Gledalac gledalac) {
		return this.gledalacRepository.save(gledalac);
	}
	
	//brisanje 
	public void delete(Long id) {
		this.gledalacRepository.deleteById(id);
	}
	
	//registracija gledaoca
	public Gledalac registracija(Korisnik g) {
		//treba da imam throws kao exception da li vec postiji takav
		Gledalac gledalac=new Gledalac();
		gledalac.setKorisnickoIme(g.getKorisnickoIme());
		gledalac.setLozinka(g.getLozinka());
		gledalac.setIme(g.getIme());
		gledalac.setPrezime(g.getPrezime());
		gledalac.setKontaktTelefon(g.getKontaktTelefon());
		gledalac.setDatumRodjenja(g.getDatumRodjenja());
		gledalac.setEMail(g.getEMail());
		//nez jel ovako treba uloga i aktivnost
		gledalac.setUloga("Gledalac");
		gledalac.setAktivan(true);
		
		return this.gledalacRepository.save(gledalac);
		
	}
	
	//prijava na sistem????
	//putem korisnickog imena
	public Gledalac prijava(String korisnickoIme) {
		//vracam gledaoca sa tim korisnickoim imenom
		Gledalac g=this.gledalacRepository.findByKorisnickoIme(korisnickoIme);
		if(g==null) {
			//treba da napisem poruku da to korisnicko ime ne postoji
			return null;
		}
		else {
			return g;
		}
	}
	
	public Gledalac Find(String korisnickoIme,String lozinka) {
		Gledalac g=this.gledalacRepository.findByKorisnickoImeAndLozinka(korisnickoIme, lozinka);
		return g;
	}

}
