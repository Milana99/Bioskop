package com.example.Bioskop.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bioskop.entity.Administrator;
import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Gledalac;
import com.example.Bioskop.entity.Korisnik;
import com.example.Bioskop.entity.Menadzer;
import com.example.Bioskop.entity.Ocena;
import com.example.Bioskop.entity.Terminski_raspored;
import com.example.Bioskop.entity.dto.FilmDTO;
import com.example.Bioskop.entity.dto.MenadzerDTO;
import com.example.Bioskop.entity.dto.OcenjivanjeDTO;
import com.example.Bioskop.entity.dto.Terminski_rasporedDTO;
import com.example.Bioskop.service.AdministratorService;
import com.example.Bioskop.service.FilmService;
import com.example.Bioskop.service.GledalacService;
import com.example.Bioskop.service.KorisnikService;
import com.example.Bioskop.service.MenadzerService;
import com.example.Bioskop.service.OcenaService;
import com.example.Bioskop.service.Terminski_rasporedService;

@RestController
@RequestMapping(value="/api")
public class KorisnikController {
	
	@Autowired
	private KorisnikService korisnikService;
	@Autowired
	private GledalacService gledalacService;
	@Autowired
	private MenadzerService menadzerService;
	@Autowired
	private AdministratorService administratorService;
	
	
	//za registraciju gledaoca,kada popuni formu
	@PostMapping(
		value="/save-korisnik",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> register(@RequestBody Korisnik k) throws Exception{
		Korisnik korisnik=new Korisnik(k.getId(),k.getKorisnickoIme(),k.getLozinka(),k.getIme(),k.getPrezime(),k.getKontaktTelefon(),k.getEMail(),k.getDatumRodjenja(),k.getUloga());
		Korisnik noviKorisnik=gledalacService.registracija(korisnik);
		return new ResponseEntity<>(noviKorisnik,HttpStatus.OK);
	}
	
	//za logovanje tj pronalazenje u bazi tog korisnika
	@PostMapping(
		value="/get-korisnik",
		consumes = MediaType.APPLICATION_JSON_VALUE,     // tip podataka koje metoda može da primi
        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> login(@RequestBody Korisnik korisnik) throws Exception{
		Gledalac g=this.gledalacService.Find(korisnik.getKorisnickoIme(),korisnik.getLozinka());
		Menadzer m=this.menadzerService.find(korisnik.getKorisnickoIme(),korisnik.getLozinka());
		Administrator a=this.administratorService.find(korisnik.getKorisnickoIme(), korisnik.getLozinka());
		if(g!=null) {
			Korisnik povratna=new Korisnik(g.getId(),g.getKorisnickoIme(),g.getLozinka(),g.getIme(),g.getPrezime(),g.getKontaktTelefon(),g.getEMail(),g.getDatumRodjenja(),g.getUloga());
			System.out.println(povratna.getEMail());
			return new ResponseEntity<>(povratna,HttpStatus.OK);
		}else if(m!=null) {
			Korisnik povratna=new Korisnik(m.getId(),m.getKorisnickoIme(),m.getLozinka(),m.getIme(),m.getPrezime(),m.getKontaktTelefon(),m.getEMail(),m.getDatumRodjenja(),m.getUloga());
			System.out.println(povratna.getEMail());
			return new ResponseEntity<>(povratna,HttpStatus.OK);
		}else if(a!=null) {
			Korisnik povratna=new Korisnik(a.getId(),a.getKorisnickoIme(),a.getLozinka(),a.getIme(),a.getPrezime(),a.getKontaktTelefon(),a.getEMail(),a.getDatumRodjenja(),a.getUloga());
			return new ResponseEntity<>(povratna,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//za registraciju menadzera od strane admina
	@PostMapping(
			value="/save-menadzer",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Korisnik> registerMenadzer(@RequestBody Korisnik k) throws Exception{
			Korisnik korisnik=new Korisnik(k.getId(),k.getKorisnickoIme(),k.getLozinka(),k.getIme(),k.getPrezime(),k.getKontaktTelefon(),k.getEMail(),k.getDatumRodjenja(),k.getUloga());
			Korisnik noviKorisnik=menadzerService.registracija(korisnik);
			return new ResponseEntity<>(noviKorisnik,HttpStatus.OK);
		}
		
	//prikaz svih menadzera
	@GetMapping(
			value="/menadzeri",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MenadzerDTO>> getMenadzeri(){
		List<Menadzer> menadzeri=this.menadzerService.findAll();
		List<MenadzerDTO> menadzeriDTO=new ArrayList<>();
		
		for (Menadzer m : menadzeri) {
			MenadzerDTO menadzerDTO=new MenadzerDTO(m.getId(), m.getKorisnickoIme(),m.getIme(), m.getPrezime());
			menadzeriDTO.add(menadzerDTO);
			
		}
		
		return new ResponseEntity<>(menadzeriDTO,HttpStatus.OK);
	}
	
	@GetMapping(
		value = "/menadzeri-ukloni/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE)  
	public ResponseEntity<MenadzerDTO> ukloni(@PathVariable(name="id") Long id){
		Menadzer m=this.menadzerService.findOne(id);
		MenadzerDTO mDTO=new MenadzerDTO(m.getId(), m.getKorisnickoIme(), m.getIme(), m.getPrezime());
		if(m.getBioskopi().isEmpty()) {
			this.menadzerService.delete(id);
			return new ResponseEntity<>(mDTO,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
