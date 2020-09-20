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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Gledalac;
import com.example.Bioskop.entity.Terminski_raspored;
import com.example.Bioskop.entity.dto.FilmDTO;
import com.example.Bioskop.entity.dto.FilmDTOPretraga;
import com.example.Bioskop.entity.dto.RezervacijaDTO;
import com.example.Bioskop.entity.dto.Terminski_rasporedDTO;
import com.example.Bioskop.service.FilmService;
import com.example.Bioskop.service.GledalacService;
import com.example.Bioskop.service.Terminski_rasporedService;

@RestController
@RequestMapping(value="/api/filmovi")
public class FilmController {

	@Autowired
	private FilmService filmService;
	@Autowired
	private Terminski_rasporedService terminski_rasporedSrevice;
	@Autowired
	private GledalacService gledalacService;
	
	
	@GetMapping(
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> getFilmovi(){
		List<Film> filmovi=this.filmService.findAll();
		
		List<FilmDTO> filmoviDTO=new ArrayList<>();
		
		for(Film film:filmovi) {
			FilmDTO filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getSrednja_ocena());
			filmoviDTO.add(filmDTO);
		}
		return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
	}
	
	@PostMapping(
			value="/add-film",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Film> dodaj(@RequestBody FilmDTO f)throws Exception{
		Film film=new Film();
		film.setNaziv(f.getNaziv());
		film.setOpis(f.getOpis());
		film.setZanr(f.getZanr());
		film.setTrajanje(f.getTrajanje());
		Double o=Double.parseDouble("0");
		film.setSrednja_ocena(o);
		
		this.filmService.save(film);
		
		return new ResponseEntity<>(film,HttpStatus.OK);
	}
	
	@GetMapping(
			value="/sortNaziv",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> sortNaziv(){
		List<Film> filmovi=this.filmService.orderNaziv();
	
		
		List<FilmDTO> filmoviDTO=new ArrayList<>();
		
		for(Film film:filmovi) {
			FilmDTO filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getSrednja_ocena());
			filmoviDTO.add(filmDTO);
		}
		return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
	}
	

	@GetMapping(
			value="/sortOcena",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> sortOcena(){
		List<Film> filmovi=this.filmService.orderOcena();
	
		
		List<FilmDTO> filmoviDTO=new ArrayList<>();
		
		for(Film film:filmovi) {
			FilmDTO filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getSrednja_ocena());
			filmoviDTO.add(filmDTO);
		}
		return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
	}
	
	@GetMapping(
			value="/sortTrajanje",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> sortTrajanje(){
		List<Film> filmovi=this.filmService.orderTrajanje() ;
		
		List<FilmDTO> filmoviDTO=new ArrayList<>();
		
		for(Film film:filmovi) {
			FilmDTO filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getSrednja_ocena());
			filmoviDTO.add(filmDTO);
		}
		return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
	}
	
	@PostMapping(
			value="/pretraga",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> pretraga(@RequestBody FilmDTOPretraga f)throws Exception{
			List<Terminski_raspored> projekcije=this.terminski_rasporedSrevice.findAll();
			List<FilmDTO> filmoviDTO =new ArrayList<>();
			List<Film> filmovi=this.filmService.findAll();
			int znak=0; //znak da je bar neki parametar bio kod nekog filma
			boolean r=true;  //razlicit
			
		
			for (Terminski_raspored tr : projekcije) {
				r=true;
				if(f.getNaziv()!="") {
					if(tr.getFilm().getNaziv().equalsIgnoreCase(f.getNaziv())) {
						znak=1;
					}else {
						r=false;
					}
				}if(f.getZanr()!="") {
					if(tr.getFilm().getZanr().equalsIgnoreCase(f.getZanr())) {
						znak=1;	
					}else {
						r=false;
					}
					
				}if(f.getOpis()!="") {
					if(tr.getFilm().getOpis().equalsIgnoreCase(f.getOpis())) {
						znak=1;
						
					}else {
						r=false;	
					}
				}if(f.getSrednjaOcena()!=null) {
					if(Double.compare(f.getSrednjaOcena(), tr.getFilm().getSrednja_ocena())==0) {
						znak=1;
					}else {
						r=false;
					
					}
				}
					
				if(f.getCena()!=0) {
					if(tr.getCena()==f.getCena()) {
						znak=1;
						
					}else {
						r=false;
						
					}
				}
				
				if(znak==1) {
					if(r==true) {
						FilmDTO filmDTO=new FilmDTO(tr.getFilm().getId(),tr.getFilm().getNaziv(),tr.getFilm().getOpis(),tr.getFilm().getZanr(),tr.getFilm().getTrajanje(),tr.getFilm().getSrednja_ocena());
						
						boolean postoji=false; //da ne bi bilo dupliranja filmova
						for (FilmDTO film : filmoviDTO) {
							if(film.getNaziv().equalsIgnoreCase(filmDTO.getNaziv())) {
								postoji=true;
							}
						}
						if(postoji==false) {
							filmoviDTO.add(filmDTO);
						}
						
						
					}
				}
			}
			
			//ako nema terminski listi idem po filmovima ali tada zanemarujem polja datum i cenu jer te podatke ni nemam o tim filmovima
			for (Film f1 : filmovi) {
				r=true;
				if(f.getNaziv()!="") {
					if(f1.getNaziv().equalsIgnoreCase(f.getNaziv())) {
						znak=1;
					}else {
						r=false;
					}
				}if(f.getZanr()!="") {
					if(f1.getZanr().equalsIgnoreCase(f.getZanr())) {
						znak=1;	
					}else {
						r=false;
					}
					
				}if(f.getOpis()!="") {
					if(f1.getOpis().equalsIgnoreCase(f.getOpis())) {
						znak=1;
						
					}else {
						r=false;
						
					}
				}if(f.getSrednjaOcena()!=null) {
					if(Double.compare(f.getSrednjaOcena(), f1.getSrednja_ocena())==0) {
						znak=1;
					}else {
						r=false;
					
					}
				}
					
				if(f.getCena()!=0) {
					r=false;
				}
				
				if(znak==1) {
					if(r==true) {
						FilmDTO filmDTO=new FilmDTO(f1.getId(),f1.getNaziv(),f1.getOpis(),f1.getZanr(),f1.getTrajanje(),f1.getSrednja_ocena());
				
						boolean postoji=false;
						for (FilmDTO film : filmoviDTO) {
							if(film.getNaziv().equalsIgnoreCase(filmDTO.getNaziv())) {
								postoji=true;
							}
						}
						if(postoji==false) {
							filmoviDTO.add(filmDTO);
						}

					}
				}
			}
			
		
			if(filmoviDTO.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
			}
			return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
		
	}
	
	@GetMapping(
			value="/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Terminski_rasporedDTO>> prikaz(@PathVariable(name="id") Long id){
		Film f=this.filmService.findOne(id);
		List<Terminski_rasporedDTO> p=new ArrayList<>();
		List<Terminski_raspored> projekcije=f.getProjekcije();
		if(projekcije.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		for (Terminski_raspored t : projekcije) {
			Terminski_rasporedDTO tr=new Terminski_rasporedDTO();
			tr.setId(t.getId());
			tr.setBrojRezervacija(t.getBroj_rezervacija());
			tr.setNaziv(t.getFilm().getNaziv());
			tr.setOpis(t.getFilm().getOpis());
			tr.setZanr(t.getFilm().getZanr());
			tr.setTrajanje(t.getFilm().getTrajanje());
			tr.setSrednjaOcena(t.getFilm().getSrednja_ocena());
			tr.setCena(t.getCena());
			tr.setDatumOdrzavanja(t.getDatum_odrzavanja());
			tr.setSalaOznaka(t.getSala().getOznaka_sale());
			tr.setVremePocetka(t.getVreme_pocetka());
			tr.setBioskop(t.getSala().getBioskop().getNaziv());
			p.add(tr);
		}
	
		
		return new ResponseEntity<>(p,HttpStatus.OK);
		
	}
	
	@GetMapping(
			value="/rezervisi/{id}",/*lista terminskih rasporeda i klikom na to se pojavi forma za rezervaciju*/
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Terminski_rasporedDTO> rezervisi(@PathVariable(name="id")Long id){
			Terminski_raspored t=this.terminski_rasporedSrevice.findOne(id);
			Terminski_rasporedDTO td=new Terminski_rasporedDTO();
			td.setId(t.getId());
			
			return new ResponseEntity<>(td,HttpStatus.OK);
	}
	@PostMapping(
			value="/rezervacija",/*dugme na formi kad potvrdim rezervaciju*/
			consumes = MediaType.APPLICATION_JSON_VALUE,     // tip podataka koje metoda može da primi
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> rez(@RequestBody RezervacijaDTO r)throws Exception{
			Gledalac g=this.gledalacService.Find(r.getKorisnickoIme(), r.getLozinka());
			RezervacijaDTO rd=new RezervacijaDTO();
			rd.setId(g.getId().toString());
			if(g==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else {
				Long id=Long.parseLong(r.getId());
				Terminski_raspored te=this.terminski_rasporedSrevice.findOne(id);
				if(te==null) {
				//	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				Set<Terminski_raspored> rezervisani=g.getRezervisani_filmovi();
				//uslov da bi mogao da rezervise
				if(te.getSala().getKapacitet()>te.getBroj_rezervacija()) {
					te.setBroj_rezervacija(te.getBroj_rezervacija()+1);
					rezervisani.add(te);

					this.gledalacService.save(g);
				}else {
					//nema dovoljno mesta
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}

				return new ResponseEntity<>(rd,HttpStatus.OK);
			}
	}
}