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

import ftn.dostavaOSA2021.DostavaOSA2021.dto.KupacDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Kupac;
import ftn.dostavaOSA2021.DostavaOSA2021.model.TipKorisnika;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KupacServiceInterface;

@RestController
@RequestMapping(value = "api/kupac")
public class KupacController {

	@Autowired
	KupacServiceInterface kupacServiceInterface;
	
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
	public ResponseEntity<KupacDTO> getKupac(@PathVariable("id") Long id){
		Kupac kupac = kupacServiceInterface.findOne(id);
		
		if(kupac == null) {
			return new ResponseEntity<KupacDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<KupacDTO>(new KupacDTO(kupac), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<KupacDTO> addKupac(@RequestBody KupacDTO kupacDTO){

		Kupac kupac = new Kupac();
		kupac.setIme(kupacDTO.getImeKupca());
		kupac.setPrezime(kupacDTO.getPrezimeKupca());
		kupac.setKorisnickoIme(kupacDTO.getKorImeKupca());
		kupac.setLozinka(kupacDTO.getLozinkaKupca());
		kupac.setBlokiran(kupacDTO.isBlokiran()); //?
		kupac.setAdresa(kupacDTO.getAdresaKupca());
		kupac.setTipKorisnika(TipKorisnika.KUPAC);
		
		kupac = kupacServiceInterface.save(kupac);
		return new ResponseEntity<KupacDTO>(new KupacDTO(kupac), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<KupacDTO> updateKupac(@RequestBody KupacDTO kupacDTO, @PathVariable("id") Long id){

		Kupac kupac = kupacServiceInterface.findById(id);
		
		if(kupac == null) {
			return new ResponseEntity<KupacDTO>(HttpStatus.BAD_REQUEST);
		}
		kupac.setIme(kupacDTO.getImeKupca());
		kupac.setPrezime(kupacDTO.getPrezimeKupca());
		kupac.setKorisnickoIme(kupacDTO.getKorImeKupca());
		kupac.setLozinka(kupacDTO.getLozinkaKupca());
		kupac.setBlokiran(kupacDTO.isBlokiran()); //?
		kupac.setAdresa(kupacDTO.getAdresaKupca());

		kupac = kupacServiceInterface.save(kupac);
		return new ResponseEntity<KupacDTO>(new KupacDTO(kupac), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteKupac(@PathVariable("id") Long id){
		
		Kupac kupac = kupacServiceInterface.findById(id);		
		if(kupac != null) {
			kupacServiceInterface.remove(id);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value = "/{id}/blokiranje")
	public ResponseEntity<KupacDTO> blok(@PathVariable("id") Long id){

		Kupac kupac = kupacServiceInterface.findById(id);
		
		if(kupac.isBlokiran() == false) {
			kupac.setBlokiran(true);
		}else {
			kupac.setBlokiran(false);
		}
		
		kupac = kupacServiceInterface.save(kupac);
		return new ResponseEntity<KupacDTO>(new KupacDTO(kupac), HttpStatus.CREATED);
	}
	
}