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

import ftn.dostavaOSA2021.DostavaOSA2021.dto.AkcijaDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Akcija;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.AkcijaServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ProdavacServiceInterface;

@RestController
@RequestMapping(value = "api/akcija")
public class AkcijaController {

	@Autowired
	AkcijaServiceInterface akcijaServiceInterface;
	
	@Autowired
	ProdavacServiceInterface prodavacServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<AkcijaDTO>> getAkcije(){

		List<Akcija> akcije = akcijaServiceInterface.findAll();
		
		List<AkcijaDTO> akcijaDTO = new ArrayList<AkcijaDTO>();
		for(Akcija akcija: akcije) {
			akcijaDTO.add(new AkcijaDTO(akcija));
		}
		return new ResponseEntity<List<AkcijaDTO>>(akcijaDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AkcijaDTO> getAkcija(@PathVariable("id") Long id){
		Akcija akcija = akcijaServiceInterface.findOne(id);
		
		if(akcija == null) {
			return new ResponseEntity<AkcijaDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AkcijaDTO>(new AkcijaDTO(akcija), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<AkcijaDTO> addAkcija(@RequestBody AkcijaDTO akcijaDTO){

		Prodavac prodavac = prodavacServiceInterface.findById(akcijaDTO.getIdProdavac());
		
		Akcija a = new Akcija();
		a.setTekst(akcijaDTO.getTekst());
		a.setProcenat(akcijaDTO.getProcenat());
		a.setOdKad(akcijaDTO.getOdKad());
		a.setDoKad(akcijaDTO.getDoKad());
		a.setProdavac(prodavac);
		
		a = akcijaServiceInterface.save(a);
		return new ResponseEntity<AkcijaDTO>(new AkcijaDTO(a), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<AkcijaDTO> updateAkcija(@RequestBody AkcijaDTO akcijaDTO, @PathVariable("id") Long id){

		Akcija akcija = akcijaServiceInterface.findById(id);
		Prodavac prodavac = prodavacServiceInterface.findById(akcijaDTO.getIdProdavac());
		
		if(akcija == null) {
			return new ResponseEntity<AkcijaDTO>(HttpStatus.BAD_REQUEST);
		}
		akcija.setTekst(akcijaDTO.getTekst());
		akcija.setProcenat(akcijaDTO.getProcenat());
		akcija.setOdKad(akcijaDTO.getOdKad());
		akcija.setDoKad(akcijaDTO.getDoKad());
		akcija.setProdavac(prodavac);

		akcija = akcijaServiceInterface.save(akcija);
		return new ResponseEntity<AkcijaDTO>(new AkcijaDTO(akcija), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteAkcija(@PathVariable("id") Long id){
		Akcija akcija = akcijaServiceInterface.findById(id);
		if(akcija != null) {
			akcijaServiceInterface.remove(id);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}