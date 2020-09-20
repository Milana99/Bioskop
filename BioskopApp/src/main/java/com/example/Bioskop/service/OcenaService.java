package com.example.Bioskop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Bioskop.entity.Ocena;
import com.example.Bioskop.repository.OcenaRepository;

@Service
public class OcenaService {

	@Autowired
	private OcenaRepository ocenaRepository;
	
	public Ocena save(Ocena o) {
		return this.ocenaRepository.save(o);
	}
}
