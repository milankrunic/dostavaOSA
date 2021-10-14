package ftn.dostavaOSA2021.DostavaOSA2021.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.dostavaOSA2021.DostavaOSA2021.dto.AdministratorDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Administrator;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.AdministratorServiceInterface;

@RestController
@RequestMapping(value = "api/admin")
public class AdministratorController {

	@Autowired
	AdministratorServiceInterface administratorServiceInterface;
	
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
		Administrator admin = administratorServiceInterface.findOne(id);
		
		if(admin == null) {
			return new ResponseEntity<AdministratorDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AdministratorDTO>(new AdministratorDTO(admin), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<AdministratorDTO> addAdministrator(@RequestBody AdministratorDTO administratorDTO){

		Administrator admin = new Administrator();
		admin.setIme(administratorDTO.getIme());
		admin.setPrezime(administratorDTO.getPrezime());
		admin.setKorisnickoIme(administratorDTO.getKorIme());
		admin.setLozinka(administratorDTO.getLozinka());
		admin.setBlokiran(administratorDTO.isBlokiran()); //?
		
		admin = administratorServiceInterface.save(admin);
		return new ResponseEntity<AdministratorDTO>(new AdministratorDTO(admin), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<AdministratorDTO> updateAdministrator(@RequestBody AdministratorDTO administratorDTO, @PathVariable("id") Long id){

		Administrator admin = administratorServiceInterface.findById(id);
		
		if(admin == null) {
			return new ResponseEntity<AdministratorDTO>(HttpStatus.BAD_REQUEST);
		}
		admin.setIme(administratorDTO.getIme());
		admin.setPrezime(administratorDTO.getPrezime());
		admin.setKorisnickoIme(administratorDTO.getKorIme());
		admin.setLozinka(administratorDTO.getLozinka());
		admin.setBlokiran(administratorDTO.isBlokiran()); //?

		admin = administratorServiceInterface.save(admin);
		return new ResponseEntity<AdministratorDTO>(new AdministratorDTO(admin), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteAdministrator(@PathVariable("id") Long id){
		Administrator admin = administratorServiceInterface.findById(id);
		if(admin != null) {
			administratorServiceInterface.remove(id);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
