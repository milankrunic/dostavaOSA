package ftn.dostavaOSA2021.DostavaOSA2021.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.dostavaOSA2021.DostavaOSA2021.dto.KupacDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Korisnik;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Kupac;
import ftn.dostavaOSA2021.DostavaOSA2021.model.TipKorisnika;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KorisnikServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KupacServiceInterface;

@RestController
@RequestMapping(value = "api/kupac")
public class KupacController {
	
	@Autowired
	KupacServiceInterface kupacServiceInterface;
	
	@Autowired
	KorisnikServiceInterface korisnikServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<KupacDTO>> getKupce(){

		List<Kupac> kupci = kupacServiceInterface.findAll();
		
		List<KupacDTO> kupacDTO = new ArrayList<KupacDTO>();
		for(Kupac kupac: kupci) {
			kupacDTO.add(new KupacDTO(kupac));
		}
		return new ResponseEntity<List<KupacDTO>>(kupacDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<KupacDTO> getKupac(@PathVariable("id") Long id, HttpSession session){
		Korisnik korisnik = korisnikServiceInterface.findOne(id);		
		Kupac kupac = kupacServiceInterface.findByKorisnickoIme(korisnik.getKorisnickoIme());
		
		session.setAttribute(KorisnikController.KORISNIK_KEY, kupac);		
		
		if(kupac == null) {
			return new ResponseEntity<KupacDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<KupacDTO>(new KupacDTO(kupac), HttpStatus.OK);
	}
	
	@GetMapping(value = "/podaci")
	public ResponseEntity<KupacDTO> getKupacPodaci(HttpSession session){
		
		Korisnik korisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);	
		Kupac kupac = kupacServiceInterface.findByKorisnickoIme(korisnik.getKorisnickoIme());
		
		if(kupac == null) {
			return new ResponseEntity<KupacDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<KupacDTO>(new KupacDTO(kupac), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<KupacDTO> addKupac(@RequestBody KupacDTO kupacDTO){

		Korisnik korisnik = new Korisnik();
		korisnik.setIme(kupacDTO.getIme());
		korisnik.setPrezime(kupacDTO.getPrezime());
		korisnik.setKorisnickoIme(kupacDTO.getKorIme());
		korisnik.setLozinka(kupacDTO.getLozinka());
		korisnik.setBlokiran(kupacDTO.isBlokiran());
		korisnik.setTipKorisnika(TipKorisnika.KUPAC);
		
		korisnik = korisnikServiceInterface.save(korisnik);
		
		Kupac kupac = new Kupac();
		kupac.setAdresa(kupacDTO.getAdresaKupca());
		kupac.setKorisnik(korisnik);
		
		kupac = kupacServiceInterface.save(kupac);
		return new ResponseEntity<KupacDTO>(new KupacDTO(kupac), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<KupacDTO> updateKupac(@RequestBody KupacDTO kupacDTO, @PathVariable("id") Long id){

		Korisnik korisnik = korisnikServiceInterface.findOne(id);		
		Kupac kupac = kupacServiceInterface.findByKorisnickoIme(korisnik.getKorisnickoIme());
		
		if(kupac == null) {
			return new ResponseEntity<KupacDTO>(HttpStatus.BAD_REQUEST);
		}
		korisnik.setIme(kupacDTO.getIme());
		korisnik.setPrezime(kupacDTO.getPrezime());
		korisnik.setKorisnickoIme(kupacDTO.getKorIme());
		korisnik.setLozinka(kupacDTO.getLozinka());
		
		korisnik = korisnikServiceInterface.save(korisnik);

		kupac.setAdresa(kupacDTO.getAdresaKupca());
		kupac.setKorisnik(korisnik);
		
		kupac = kupacServiceInterface.save(kupac);
		return new ResponseEntity<KupacDTO>(new KupacDTO(kupac), HttpStatus.OK);
	}
	
}