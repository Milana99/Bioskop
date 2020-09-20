package com.example.Bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bioskop.entity.Menadzer;

public interface MenadzerRepository extends JpaRepository<Menadzer,Long>{

	Menadzer findByKorisnickoImeAndLozinka(String korisnickoIme,String lozinka);
	
	Menadzer findByKorisnickoIme(String korisnickoIme);
	
	List<Menadzer> findAllByAktivan(Boolean aktivan);
}
