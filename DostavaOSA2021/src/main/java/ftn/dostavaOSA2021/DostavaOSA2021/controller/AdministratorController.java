package ftn.dostavaOSA2021.DostavaOSA2021.controller;

import java.util.ArrayList;
import java.util.List;

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

import ftn.dostavaOSA2021.DostavaOSA2021.dto.AdministratorDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Administrator;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Korisnik;
import ftn.dostavaOSA2021.DostavaOSA2021.model.TipKorisnika;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.AdministratorServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KorisnikServiceInterface;

@RestController
@RequestMapping(value = "api/admin")
public class AdministratorController { 

	@Autowired
	AdministratorServiceInterface administratorServiceInterface;
	
	@Autowired
	KorisnikServiceInterface korisnikServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<AdministratorDTO>> getAdministratori(){

		List<Administrator> administratori = administratorServiceInterface.findAll();
		
		List<AdministratorDTO> administratorDTO = new ArrayList<AdministratorDTO>();
		for(Administrator admin: administratori) {
			administratorDTO.add(new AdministratorDTO(admin));
		}
		return new ResponseEntity<List<AdministratorDTO>>(administratorDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AdministratorDTO> getAdministrator(@PathVariable("id") Long id){
		Korisnik korisnik = korisnikServiceInterface.findOne(id);
		Administrator admin = administratorServiceInterface.findByKorisnickoIme(korisnik.getKorisnickoIme());
		
		if(admin == null) {
			return new ResponseEntity<AdministratorDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AdministratorDTO>(new AdministratorDTO(admin), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<AdministratorDTO> addAdministrator(@RequestBody AdministratorDTO administratorDTO){

		Korisnik korisnik = new Korisnik();
		korisnik.setIme(administratorDTO.getIme());
		korisnik.setPrezime(administratorDTO.getPrezime());
		korisnik.setKorisnickoIme(administratorDTO.getKorIme());
		korisnik.setLozinka(administratorDTO.getLozinka());
		korisnik.setBlokiran(administratorDTO.isBlokiran());
		korisnik.setTipKorisnika(TipKorisnika.ADMINISTRATOR);
		
		korisnik = korisnikServiceInterface.save(korisnik);
		
		Administrator admin = new Administrator();
		admin.setKorisnik(korisnik);
		
		admin = administratorServiceInterface.save(admin);
		return new ResponseEntity<AdministratorDTO>(new AdministratorDTO(admin), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<AdministratorDTO> updateAdministrator(@RequestBody AdministratorDTO administratorDTO, @PathVariable("id") Long id){
		
		Korisnik korisnik = korisnikServiceInterface.findOne(id);
		Administrator admin = administratorServiceInterface.findByKorisnickoIme(korisnik.getKorisnickoIme());
		
		if(admin == null) {
			return new ResponseEntity<AdministratorDTO>(HttpStatus.BAD_REQUEST);
		}
		korisnik.setIme(administratorDTO.getIme());
		korisnik.setPrezime(administratorDTO.getPrezime());
		korisnik.setKorisnickoIme(administratorDTO.getKorIme());
		korisnik.setLozinka(administratorDTO.getLozinka());
		
		korisnik = korisnikServiceInterface.save(korisnik);

		admin.setKorisnik(korisnik);
		return new ResponseEntity<AdministratorDTO>(new AdministratorDTO(admin), HttpStatus.OK);
	}
	
}
