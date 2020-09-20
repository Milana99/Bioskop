package com.example.Bioskop.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Terminski_raspored;

public interface Terminski_rasporedRepository extends JpaRepository<Terminski_raspored,Long>{

	List<Terminski_raspored> findAllByFilm(Film film);
}
