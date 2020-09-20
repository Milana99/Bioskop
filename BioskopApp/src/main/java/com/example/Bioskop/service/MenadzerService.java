package com.example.Bioskop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.Bioskop.entity.Korisnik;
import com.example.Bioskop.entity.Menadzer;
import com.example.Bioskop.repository.MenadzerRepository;

@Service
public class MenadzerService {
	
	@Autowired
	private MenadzerRepository menadzerRepository;
	
	public Menadzer findOne(Long id) {
		Menadzer m=this.menadzerRepository.getOne(id);
		return m;
	}
	
	public List<Menadzer> findAll(){
		List<Menadzer> menadzeri=this.menadzerRepository.findAll();
		return menadzeri;
	}
	
	public Menadzer save(Menadzer m) {
		return this.menadzerRepository.save(m);
	}
	
	public void delete(Long id) {
		this.menadzerRepository.deleteById(id);
	}

	//registracija tj dodavanje novog
	public Menadzer registracija(Korisnik g) {
		//treba da imam throws kao exception da li vec postiji takav
		Menadzer m=new Menadzer();
		m.setKorisnickoIme(g.getKorisnickoIme());
		m.setLozinka(g.getLozinka());
		m.setIme(g.getIme());
		m.setPrezime(g.getPrezime());
		m.setKontaktTelefon(g.getKontaktTelefon());
		m.setDatumRodjenja(g.getDatumRodjenja());
		m.setEMail(g.getEMail());
		//nez jel ovako treba uloga i aktivnost
		m.setUloga("Menadzer");
		m.setAktivan(true);
		
		return this.menadzerRepository.save(m);
		
	}
	
	//pronalaxak odredjeng,ako postiji,za login
	public Menadzer find(String korisnickoIme,String lozinka) {
		Menadzer m=this.menadzerRepository.findByKorisnickoImeAndLozinka(korisnickoIme, lozinka);
		return m;
		
	}
	
	public Menadzer findKorisnickoIme(String korisnickoIme) {
		Menadzer m=this.menadzerRepository.findByKorisnickoIme(korisnickoIme);
		return  m;
	}
	
	public List<Menadzer>findByAktivan(Boolean a){
		List<Menadzer> menadzeri=this.menadzerRepository.findAllByAktivan(a);
		return menadzeri;
	}
	
	
}
