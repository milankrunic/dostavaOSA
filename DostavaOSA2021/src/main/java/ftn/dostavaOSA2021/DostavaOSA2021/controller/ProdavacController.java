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

import ftn.dostavaOSA2021.DostavaOSA2021.dto.ProdavacDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;
import ftn.dostavaOSA2021.DostavaOSA2021.model.TipKorisnika;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ProdavacServiceInterface;

@RestController
@RequestMapping(value = "api/prodavac")
public class ProdavacController {
	
	@Autowired
	ProdavacServiceInterface prodavacServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<ProdavacDTO>> getProdavci(){

		List<Prodavac> prodavci = prodavacServiceInterface.findAll();
		
		List<ProdavacDTO> prodavacDTO = new ArrayList<ProdavacDTO>();
		for(Prodavac prodavac: prodavci) {
			prodavacDTO.add(new ProdavacDTO(prodavac));
		}
		return new ResponseEntity<List<ProdavacDTO>>(prodavacDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdavacDTO> getProdavac(@PathVariable("id") Long id){
		Prodavac prodavac = prodavacServiceInterface.findOne(id);
		
		if(prodavac == null) {
			return new ResponseEntity<ProdavacDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProdavacDTO>(new ProdavacDTO(prodavac), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ProdavacDTO> addProdavac(@RequestBody ProdavacDTO prodavacDTO){

		Prodavac prodavac = new Prodavac();
		prodavac.setIme(prodavacDTO.getIme());
		prodavac.setPrezime(prodavacDTO.getPrezime());
		prodavac.setKorisnickoIme(prodavacDTO.getKorIme());
		prodavac.setLozinka(prodavacDTO.getLozinka());
		prodavac.setBlokiran(prodavacDTO.isBlokiran()); //?
		prodavac.setNazivProdavca(prodavacDTO.getNazivProdavca());
		prodavac.setEmail(prodavacDTO.getEmail());
		prodavac.setAdresa(prodavacDTO.getAdresa());
		prodavac.setPoslujeOd(prodavacDTO.getPoslujeOd());
		prodavac.setTipKorisnika(TipKorisnika.PRODAVAC);
		
		prodavac = prodavacServiceInterface.save(prodavac);
		return new ResponseEntity<ProdavacDTO>(new ProdavacDTO(prodavac), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<ProdavacDTO> updateProdavac(@RequestBody ProdavacDTO prodavacDTO, @PathVariable("id") Long id){

		Prodavac prodavac = prodavacServiceInterface.findById(id);
		
		if(prodavac == null) {
			return new ResponseEntity<ProdavacDTO>(HttpStatus.BAD_REQUEST);
		}
		prodavac.setIme(prodavacDTO.getIme());
		prodavac.setPrezime(prodavacDTO.getPrezime());
		prodavac.setKorisnickoIme(prodavacDTO.getKorIme());
		prodavac.setLozinka(prodavacDTO.getLozinka());
		prodavac.setBlokiran(prodavacDTO.isBlokiran()); //?
		prodavac.setNazivProdavca(prodavacDTO.getNazivProdavca());
		prodavac.setEmail(prodavacDTO.getEmail());
		prodavac.setAdresa(prodavacDTO.getAdresa());
		prodavac.setPoslujeOd(prodavacDTO.getPoslujeOd());

		prodavac = prodavacServiceInterface.save(prodavac);
		return new ResponseEntity<ProdavacDTO>(new ProdavacDTO(prodavac), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteProdavac(@PathVariable("id") Long id){
		Prodavac prodavac = prodavacServiceInterface.findById(id);
		if(prodavac != null) {
			prodavacServiceInterface.remove(id);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
