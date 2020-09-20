package com.example.Bioskop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Bioskop.entity.Film;
import com.example.Bioskop.repository.FilmRepository;

@Service
public class FilmService {

	@Autowired
	private FilmRepository filmRepository;
	
	public Film findOne(Long id) {
        Film film = this.filmRepository.getOne(id);
        return film;
    }
	
	public List<Film> findAll(){
		List<Film> filmovi=this.filmRepository.findAll();
		return filmovi;
	}
	
	public Film save(Film f) {
		return this.filmRepository.save(f);
	}
	public List<Film> orderNaziv(){
		return this.filmRepository.findAllByOrderByNaziv();
	}
	
	public List<Film> orderOcena(){
		return this.filmRepository.findAllByOrderBySrednjaOcenaDesc();
	}
	
	public List<Film> orderTrajanje(){
		return this.filmRepository.findAllByOrderByTrajanje();
	}
	
	public List<Film> findByNaziv(String naziv){
		List<Film> filmovi=this.filmRepository.findAllByNazivIgnoreCase(naziv);
		return filmovi;
	}
	
	public List<Film> findByKriterijumi(String n,String z,String op,Double oc){
		List<Film> filmovi=this.filmRepository.findByNazivIgnoreCaseAndZanrIgnoreCaseAndOpisIgnoreCaseAndSrednjaOcena(n, z, op,oc);
		return filmovi;
	}
	
	public Film findNaziv(String n) {
		Film f=this.filmRepository.findByNazivIgnoreCase(n);
		return f;
	}
}
