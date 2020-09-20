package com.example.Bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bioskop.entity.Film;

public interface FilmRepository extends JpaRepository<Film,Long>{

	List<Film> findAllByNazivIgnoreCase(String naziv);
	
	//istovremena pretraga:Naziv,zanr,opis,ocena,cena,vreme projekcije
	
	//???KAko da stavim vreme i datum i cenu ako je razlicita za razlicite bioskope
	
	List<Film> findByNazivIgnoreCaseAndZanrIgnoreCaseAndOpisIgnoreCaseAndSrednjaOcena(String naziv,String zanr,String opis,Double o);
	
	List<Film> findBySrednjaOcenaBetween(Double ol,Double oh);
	
	List<Film> findAllByOrderByNaziv();
	
	List<Film> findAllByOrderBySrednjaOcenaDesc();
	
	List<Film> findAllByOrderByTrajanje();
	
	Film findByNazivIgnoreCase(String naziv);

}
