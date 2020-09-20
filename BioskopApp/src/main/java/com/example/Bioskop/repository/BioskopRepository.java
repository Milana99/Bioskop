package com.example.Bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bioskop.entity.Bioskop;

public interface BioskopRepository extends JpaRepository<Bioskop,Long>{
		
	Bioskop findByNazivIgnoreCase(String naziv);
 }
