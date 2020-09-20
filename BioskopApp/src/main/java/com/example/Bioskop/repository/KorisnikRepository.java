package com.example.Bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bioskop.entity.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik,Long> {

}
