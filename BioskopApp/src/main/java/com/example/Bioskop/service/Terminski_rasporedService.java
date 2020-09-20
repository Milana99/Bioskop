package com.example.Bioskop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Terminski_raspored;
import com.example.Bioskop.repository.Terminski_rasporedRepository;
@Service
public class Terminski_rasporedService {

	@Autowired
	private Terminski_rasporedRepository terminski_rasporedRepository;
	
	public Terminski_raspored findOne(Long id) {
        Terminski_raspored projekcija= this.terminski_rasporedRepository.getOne(id);
        return projekcija;
    }
	
	public List<Terminski_raspored> findAll(){
		List<Terminski_raspored> projekcije=this.terminski_rasporedRepository.findAll();
		return projekcije;
	}
	
	public List<Terminski_raspored> findByFilm(Film f){
		List<Terminski_raspored> projekcije=this.terminski_rasporedRepository.findAllByFilm(f);
		return projekcije;
	}
	
	public Terminski_raspored save(Terminski_raspored t) {
		Terminski_raspored te=this.terminski_rasporedRepository.save(t);
		return te;
	}
	
	public void delete(  Long id) {
		this.terminski_rasporedRepository.deleteById(id);
	}
}
