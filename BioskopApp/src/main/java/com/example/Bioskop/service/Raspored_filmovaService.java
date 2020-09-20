package com.example.Bioskop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bioskop.entity.Raspored_filmova;
import com.example.Bioskop.repository.Raspored_filmovaRepository;

@Service
public class Raspored_filmovaService {

		@Autowired
		private Raspored_filmovaRepository raspored_filmovaResposiotry;
		
		public Raspored_filmova save(Raspored_filmova r) {
			return this.raspored_filmovaResposiotry.save(r);
		}
		public void delete(Long id) {
			this.raspored_filmovaResposiotry.deleteById(id);
		}
}
