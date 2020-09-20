package com.example.Bioskop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Bioskop.entity.Administrator;
import com.example.Bioskop.repository.AdministratorRepository;

@Service
public class AdministratorService {
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	public Administrator findOne(Long id) {
		return this.administratorRepository.getOne(id);
	}
	
	public List<Administrator> findAll(){
		return this.administratorRepository.findAll();
	}
	
	public Administrator save(Administrator a) {
		return this.administratorRepository.save(a);
	}

	public void delete(Long id) {
		this.administratorRepository.deleteById(id);
	}
	
	public Administrator find(String korisnickoIme,String lozinka) {
		Administrator a=this.administratorRepository.findByKorisnickoImeAndLozinka(korisnickoIme, lozinka);
		return a;
	}
}
