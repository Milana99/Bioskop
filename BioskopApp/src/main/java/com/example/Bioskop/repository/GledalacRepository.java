package com.example.Bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bioskop.entity.Gledalac;

public interface GledalacRepository extends JpaRepository<Gledalac,Long> {

	Gledalac findByKorisnickoIme(String korisnickoIme);
	
	Gledalac findByKorisnickoImeAndLozinka(String korisnickoIme,String lozinka);
	
	List<Gledalac> findAllByImeOrPrezimeIgnoreCase(String ime,String prezime);
}
