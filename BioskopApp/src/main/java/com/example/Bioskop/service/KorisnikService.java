package com.example.Bioskop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bioskop.entity.Korisnik;
import com.example.Bioskop.repository.KorisnikRepository;

@Service
public class KorisnikService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public Korisnik findOne(Long id) {
		Korisnik k=this.korisnikRepository.getOne(id);
		return k;
	}
	
	public List<Korisnik> findAll(){
		List<Korisnik> korisnici=this.korisnikRepository.findAll();
		return korisnici;
	}

	public Korisnik save(Korisnik korisnik) {
		return this.korisnikRepository.save(korisnik);
	}
}
