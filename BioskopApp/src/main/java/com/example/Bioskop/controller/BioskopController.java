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

import com.example.Bioskop.entity.Bioskop;
import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Gledalac;
import com.example.Bioskop.entity.Menadzer;
import com.example.Bioskop.entity.Raspored_filmova;
import com.example.Bioskop.entity.Sala;
import com.example.Bioskop.entity.Terminski_raspored;
import com.example.Bioskop.entity.dto.BioskopDTO;
import com.example.Bioskop.entity.dto.BioskopDTOAdd;
import com.example.Bioskop.entity.dto.SalaDTO;
import com.example.Bioskop.entity.dto.Terminski_rasporedDTO;
import com.example.Bioskop.service.BioskopService;
import com.example.Bioskop.service.FilmService;
import com.example.Bioskop.service.GledalacService;
import com.example.Bioskop.service.MenadzerService;
import com.example.Bioskop.service.Raspored_filmovaService;
import com.example.Bioskop.service.SalaService;
import com.example.Bioskop.service.Terminski_rasporedService;

@RestController
@RequestMapping(value="/api")
public class BioskopController {
	
	@Autowired
	private BioskopService bioskopService;
	@Autowired
	private MenadzerService menadzerService;
	@Autowired
	private GledalacService gledalacService;
	@Autowired
	private SalaService salaservice;
	@Autowired 
	private FilmService filmService;
	@Autowired 
	private Raspored_filmovaService raspored_filmovaService;
	@Autowired
	private Terminski_rasporedService terminski_rasporedService;
	

	//prikaz bioskopa koji su u nadleznosti nekog menadzera
	@GetMapping(
			value="/bioskopi/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BioskopDTO>> bioskopi(@PathVariable(name="id")Long id){
			Menadzer m=this.menadzerService.findOne(id);
			Set<Bioskop> bioskopi=m.getBioskopi();
			List<BioskopDTO> povratna=new ArrayList<>();
			
			for(Bioskop b:bioskopi) {
				BioskopDTO bd=new BioskopDTO();
				bd.setId(b.getId());
				bd.setNaziv(b.getNaziv());
				bd.setAdresa(b.getAdresa());
				bd.setBrojCentrale(b.getBrojCentrale());
				bd.seteMail(b.getEMail());
				povratna.add(bd);
			}
			
			return new ResponseEntity<>(povratna,HttpStatus.OK);
	}
	
	//dodavanje bioskopa na osnovu skupljenih podataka iz forme
	@PostMapping(
		value="/add-bioskop",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BioskopDTOAdd> dodaj(@RequestBody BioskopDTOAdd b) throws Exception	{/*RequestBody je onaj podatak data:newBioskopJSON koji saljemo kontroleru*/
		Bioskop bioskop=new Bioskop(b.getNaziv(), b.getAdresa(), b.getBrojCentrale(), b.geteMail());/*sve sto smo uneli u formu pravimo objekat od tih podataka, podacima iz forme pravimo novi bioskop, ti podaci se nalaze u b*/
		String m=b.getMenadzer();
		Menadzer menadzer=this.menadzerService.findKorisnickoIme(m);/*korisnicno ime menadzera polje u formi trazimo i ako ga nema izbacuje gresku*/
	
		//ako je unet nepostojeci menadzer
		if(menadzer==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			bioskop.setMenadzer(menadzer);/*postavljamo ovog unetog menadzera za menadzera za bioskop koji smo dodali */
			this.bioskopService.save(bioskop);
			
			/*pravimo bioskodtoadd jer smo u resposneEntity stavili bioskopdtoadd*/
			BioskopDTOAdd bioskopDTO=new BioskopDTOAdd(bioskop.getId(),bioskop.getNaziv(),bioskop.getAdresa(),bioskop.getBrojCentrale(),bioskop.getEMail());
			return new ResponseEntity<>(bioskopDTO,HttpStatus.OK);
		}
		
	}
	//svi bioskopi
	@GetMapping(
			value="/bioskopi",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BioskopDTO>> getBioskopi(){
		List<Bioskop> bioskopi=this.bioskopService.findAll();
		List<BioskopDTO> bioskopiDTO=new ArrayList<>();
		
		for (Bioskop b : bioskopi) {
			BioskopDTO bioskopDTO=new BioskopDTO(b.getId(), b.getNaziv(), b.getAdresa(), b.getBrojCentrale(), b.getEMail());
			bioskopiDTO.add(bioskopDTO);
			
		}
		
		return new ResponseEntity<>(bioskopiDTO,HttpStatus.OK);
	}
	
	//uklanjanje bioskopa,sve se rucno brise
	@GetMapping(
			value="/bioskopi/ukloni/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BioskopDTO> ukloni(@PathVariable(name="id") Long id){/*patvariable dovablja id od bioskopa (this.id iz jsa)*/
		Bioskop b=this.bioskopService.findOne(id);
		if(b==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		BioskopDTO bioskop=new BioskopDTO(b.getId(), b.getNaziv(), b.getAdresa(), b.getBrojCentrale(), b.getEMail());
		/*prolazi kroz projekcije sale i sve brise i na kraju obrise tu salu*/
		Set<Sala> sale=b.getSale();
		for (Sala sala : sale) {
			List<Terminski_raspored> projekcije=sala.getProjekcije();
			for (Terminski_raspored t : projekcije) {
				Set<Gledalac> gledaoci=t.getGledaoci_koji_su_rezervisali_film();
				for (Gledalac g : gledaoci) {
					if(g.getRezervisani_filmovi().contains(t)) {
						g.getRezervisani_filmovi().remove(t);
					}
				}
				Set<Raspored_filmova> rasporedi=t.getRasporedi();
				for(Raspored_filmova r:rasporedi) {
					this.raspored_filmovaService.delete(t.getId());
				}
				//this.terminski_rasporedService.delete(t.getId());
			}
			this.salaservice.deleteById(sala.getId());
		}
		this.bioskopService.delete(id); 
		
		
		return new ResponseEntity<>(bioskop,HttpStatus.OK);
	}
	
	//dodavanje sala
	@PostMapping(
			value="/add-sala",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<SalaDTO> dodaj(@RequestBody SalaDTO s) throws Exception	{
			Bioskop b=this.bioskopService.findNaziv(s.getBioskop());
			//ako je uneti bioskop nepostojeci
			if(b==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else {
				Sala sala=new Sala(s.getKapacitet(), s.getOznakaSale(), b);
				Sala newSala=this.salaservice.save(sala);
				
				SalaDTO salaDTO=new SalaDTO(newSala.getId(), newSala.getOznaka_sale(), newSala.getKapacitet(), newSala.getBioskop().getNaziv());
				return new ResponseEntity<>(salaDTO,HttpStatus.OK);
			}
		}
	
	@PostMapping(
			value="/add-raspored",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Raspored_filmova> dodajRaspored(@RequestBody Terminski_rasporedDTO t)throws Exception{
			Bioskop b=this.bioskopService.findNaziv(t.getBioskop());/*prvo proveravamo da li postoje film i bioskop u bazi, ako ne postoje izbacuje gresku*/
			if(b==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Film f=this.filmService.findNaziv(t.getNaziv());
			if(f==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			/*pravimo novi terminski raspored*/
			Terminski_raspored te=new Terminski_raspored();
			/*pravimo kopiranje filma i bioskopa jer nesto nije htelo da radi*/
			Film f1=new Film(f.getNaziv(), f.getOpis(), f.getZanr(), f.getTrajanje(), f.getSrednja_ocena());
			f1.setId(f.getId());
			this.filmService.save(f1);/*cuvam kopiju filma*/
			/*u terminskom rasporedu dodajemo novu kopiju filma, broj rezervacija stavljamo na 0, provera*/
			te.setFilm(f1);
			te.setBroj_rezervacija(0);
			te.setCena(t.getCena());
			te.setDatum_odrzavanja(t.getDatumOdrzavanja());
			te.setVreme_pocetka(t.getVremePocetka());
			Sala s=this.salaservice.findOznaka(t.getSalaOznaka());/* proveravam da li postoji sala */
			if(s==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			if(s.getBioskop()!=b) {/*proveravam da li postoji sala u tom bioskopu koji smo izabrali*/
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			/*pravi se kopija bioskopa jer nesto nije htelo da radi*/
			Bioskop b2=new Bioskop(b.getNaziv(), b.getAdresa(), b.getBrojCentrale(), b.getEMail());
			b2.setId(b.getId());
			Menadzer m2=new Menadzer();
			Menadzer m=b.getMenadzer();
			m2.setId(m.getId());
			m2.setAktivan(true);
			m2.setDatumRodjenja(m.getDatumRodjenja());
			m2.setIme(m.getIme());
			m2.setEMail(m.getEMail());
			m2.setKorisnickoIme(m.getKorisnickoIme());
			m2.setPrezime(m.getPrezime());
			m2.setLozinka(m.getLozinka());
			m2.setKontaktTelefon(m.getKontaktTelefon());
			m2.setUloga(m.getUloga());
			this.menadzerService.save(m2);
			b2.setMenadzer(m2);
			
			
			
			Raspored_filmova rf=new Raspored_filmova();
			
			this.bioskopService.save(b2);
			Sala s2=new Sala(s.getKapacitet(),s.getOznaka_sale(),b2);
			s2.setId(s.getId());
			this.salaservice.save(s2);
			te.setSala(s2);
			this.terminski_rasporedService.save(te);
			rf.setBioskop(b2);
			rf.setTerminski_raspored(te);
			
			this.raspored_filmovaService.save(rf);
		
			
			return new ResponseEntity<>(rf,HttpStatus.OK);
			
	}
	
	//izmena sale
	@GetMapping(
			value="sala-izmeni/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SalaDTO> sala(@PathVariable(name="id") Long id){
		Sala s=this.salaservice.findOne(id);
		SalaDTO s1=new SalaDTO();
		s1.setId(s.getId());
		s1.setKapacitet(s.getKapacitet());
		s1.setOznakaSale(s.getOznaka_sale());
		
		return new ResponseEntity<>(s1,HttpStatus.OK);
	}
	@PostMapping(
			value="sala/izmenjivanje",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SalaDTO> salaIzmena(@RequestBody SalaDTO s)throws Exception{
		Sala sala=this.salaservice.findOne(s.getId());
		if(sala==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		sala.setKapacitet(s.getKapacitet());
		sala.setOznaka_sale(s.getOznakaSale());
		this.salaservice.save(sala);
		SalaDTO s1=new SalaDTO();
		s1.setId(sala.getId());
		return new ResponseEntity<>(s1,HttpStatus.OK);
	}
	
	@GetMapping(
			value="/repertoar-izmeni/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Terminski_rasporedDTO> repertoar(@PathVariable(name="id") Long id){
			Terminski_raspored t=this.terminski_rasporedService.findOne(id);
			Terminski_rasporedDTO t1=new Terminski_rasporedDTO();
			t1.setId(t.getId());
			t1.setNaziv(t.getFilm().getNaziv());
			t1.setDatumOdrzavanja(t.getDatum_odrzavanja());
			t1.setVremePocetka(t.getVreme_pocetka());
			t1.setCena(t.getCena());
			t1.setSalaOznaka(t.getSala().getOznaka_sale());
			
			return new ResponseEntity<>(t1,HttpStatus.OK);

	}
	
	@PostMapping(
			value="/repertoar/izmenjivanje",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Terminski_rasporedDTO> repertoarIzmena(@RequestBody Terminski_rasporedDTO t)throws Exception{
			Terminski_raspored tr=this.terminski_rasporedService.findOne(t.getId());
			if(tr==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Film f=this.filmService.findNaziv(t.getNaziv());
			if(f==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Sala s=this.salaservice.findOznaka(t.getSalaOznaka());
			if(s==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			if(t.getDatumOdrzavanja()!=null) {
				tr.setDatum_odrzavanja(t.getDatumOdrzavanja());
			}
			tr.setFilm(f);
			
			tr.setVreme_pocetka(t.getVremePocetka());
			tr.setCena(t.getCena());
			tr.setSala(s);
			this.terminski_rasporedService.save(tr);
			Terminski_rasporedDTO tr1=new Terminski_rasporedDTO();
			tr1.setId(tr.getId());
			return new ResponseEntity<>(tr1,HttpStatus.OK);
	}
	
	@GetMapping(
			value="/bioskop-izmeni/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BioskopDTO> bioskop(@PathVariable(name="id") Long id){
			Bioskop b=this.bioskopService.findOne(id);
			BioskopDTO b1=new BioskopDTO();
			b1.setId(b.getId());
			b1.setNaziv(b.getNaziv());
			b1.setAdresa(b.getAdresa());
			b1.setBrojCentrale(b.getBrojCentrale());
			b1.seteMail(b.getEMail());
			
			return new ResponseEntity<>(b1,HttpStatus.OK);

	}
	
	@PostMapping(
			value="bioskop/izmenjivanje",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BioskopDTO> bioskopIzmena(@RequestBody BioskopDTO b)throws Exception{
		Bioskop bioskop=this.bioskopService.findOne(b.getId());
		if(bioskop==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Bioskop b1=new Bioskop();
		
		b1.setId(bioskop.getId());
		b1.setAdresa(b.getAdresa());
		if(b.getAdresa()==null) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		b1.setBrojCentrale(b.getBrojCentrale());
		b1.setNaziv(b.getNaziv());
		b1.setEMail(b.geteMail());
		this.bioskopService.save(b1);
		BioskopDTO b2=new BioskopDTO();
		b2.setId(bioskop.getId());
		return new ResponseEntity<>(b2,HttpStatus.OK);
			
	}

}