package com.example.Bioskop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bioskop.entity.Bioskop;
import com.example.Bioskop.entity.Menadzer;
import com.example.Bioskop.entity.Raspored_filmova;
import com.example.Bioskop.entity.Sala;
import com.example.Bioskop.entity.dto.BioskopDTO;
import com.example.Bioskop.entity.dto.SalaDTO;
import com.example.Bioskop.entity.dto.Terminski_rasporedDTO;
import com.example.Bioskop.service.BioskopService;
import com.example.Bioskop.service.MenadzerService;
import com.example.Bioskop.service.SalaService;

@RestController
@RequestMapping(value="/api")
public class MenadzerController {
	@Autowired
	private MenadzerService menadzerService;
	@Autowired
	private BioskopService bioskopService;
	@Autowired 
	private SalaService salaService;

	@GetMapping(
			value="/sale/{id}", //id bioskopa
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SalaDTO>> sale(@PathVariable(name="id") Long id){
			Bioskop b=this.bioskopService.findOne(id);
			if(b==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Set<Sala> sale=b.getSale();
			/*if(sale.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}*/
			List<SalaDTO> povratna=new ArrayList<>();
			for(Sala s:sale) {
				SalaDTO sd=new SalaDTO();
				sd.setId(s.getId());
				sd.setKapacitet(s.getKapacitet());
				sd.setOznakaSale(s.getOznaka_sale());
				sd.setBioskop(b.getNaziv());
				povratna.add(sd);
			}
			
			return new ResponseEntity<>(povratna,HttpStatus.OK);
	}
	
	@GetMapping(
			value="/saleUkloni/{id}",  //id sale
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SalaDTO> ukloniSalu(@PathVariable(name="id")Long id){
			Sala s=this.salaService.findOne(id);
			 SalaDTO salaDTO=new SalaDTO(s.getId(), s.getOznaka_sale(), s.getKapacitet(), s.getBioskop().getNaziv());
			 this.salaService.deleteById(id);
			 
			 return new ResponseEntity<>(salaDTO,HttpStatus.OK);
	}
	
	@GetMapping(
			value="/repertoar/{id}",  //dobijem id bioskopa 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Terminski_rasporedDTO>> repertoar(@PathVariable(name="id")Long id){
			Bioskop b=this.bioskopService.findOne(id);
			List<Terminski_rasporedDTO> povratna=new ArrayList<>();
			List<Raspored_filmova> rasporedi=b.getRasporedi();
			/*if(rasporedi.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}*/
			for(Raspored_filmova r:rasporedi) {
				Terminski_rasporedDTO t=new Terminski_rasporedDTO();
				t.setId(r.getTerminski_raspored().getId());
				t.setBioskop(b.getNaziv());
				t.setSalaOznaka(r.getTerminski_raspored().getSala().getOznaka_sale());
				t.setDatumOdrzavanja(r.getTerminski_raspored().getDatum_odrzavanja());
				t.setNaziv(r.getTerminski_raspored().getFilm().getNaziv());
				t.setVremePocetka(r.getTerminski_raspored().getVreme_pocetka());
				t.setSalaOznaka(r.getTerminski_raspored().getSala().getOznaka_sale());
				t.setCena(r.getTerminski_raspored().getCena());
				t.setBrojRezervacija(r.getTerminski_raspored().getBroj_rezervacija());
				povratna.add(t);
				
			}
			
			return new ResponseEntity<>(povratna,HttpStatus.OK);

	}

}
