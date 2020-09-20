package com.example.Bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bioskop.entity.Administrator;
import com.example.Bioskop.entity.Menadzer;

public interface AdministratorRepository extends JpaRepository<Administrator,Long>{

	Administrator findByKorisnickoImeAndLozinka(String korisnickoIme,String lozinka);
}
