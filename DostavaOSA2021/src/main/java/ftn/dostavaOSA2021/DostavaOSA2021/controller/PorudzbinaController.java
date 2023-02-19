package ftn.dostavaOSA2021.DostavaOSA2021.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.dostavaOSA2021.DostavaOSA2021.dto.PorudzbinaDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.PorudzbinaES;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceInterface.PorudzbinaEsServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Kupac;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Porudzbina;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KupacServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.PorudzbinaServiceInterface;

@RestController
@RequestMapping(value = "api/porudzbina")
public class PorudzbinaController {

	@Autowired
	PorudzbinaServiceInterface porudzbinaServiceInterface;
	
	@Autowired
	KupacServiceInterface kupacServiceInterface;
	
	@Autowired
	PorudzbinaEsServiceInterface porudzbinaEsServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<PorudzbinaDTO>> getPorudzbine(){

		List<Porudzbina> porudzbine = porudzbinaServiceInterface.findAll();
		
		List<PorudzbinaDTO> porudzbinaDTO = new ArrayList<PorudzbinaDTO>();
		for(Porudzbina por: porudzbine) {
			porudzbinaDTO.add(new PorudzbinaDTO(por));
		}
		return new ResponseEntity<List<PorudzbinaDTO>>(porudzbinaDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PorudzbinaDTO> getPorudzbina(@PathVariable("id") Long id){
		Porudzbina porudzbina = porudzbinaServiceInterface.findOne(id);
		
		if(porudzbina == null) {
			return new ResponseEntity<PorudzbinaDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PorudzbinaDTO>(new PorudzbinaDTO(porudzbina), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PorudzbinaDTO> addPorudzbina(@RequestBody PorudzbinaDTO porudzbinaDTO){

		Kupac kupac = kupacServiceInterface.findOne(porudzbinaDTO.getIdKupac());
		
		Porudzbina por = new Porudzbina();
		por.setSatnica(new Date());
		por.setOcena(porudzbinaDTO.getOcena());
		por.setKomentar(porudzbinaDTO.getKomentar());
		por.setCena(porudzbinaDTO.getCena());
		por.setDostavljeno(porudzbinaDTO.isDostavljeno()); 
		por.setAnonimanKomentar(porudzbinaDTO.isAnonimanKomentar()); 
		por.setArhiviranKomentar(porudzbinaDTO.isArhiviranKomentar()); 
		por.setKupac(kupac);
		
		por = porudzbinaServiceInterface.save(por);
		porudzbinaEsServiceInterface.index(new PorudzbinaES(por));
		return new ResponseEntity<PorudzbinaDTO>(new PorudzbinaDTO(por), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePorudzbina(@PathVariable("id") Long id){
		Porudzbina porudzbina = porudzbinaServiceInterface.findById(id);
		if(porudzbina != null) {
			porudzbinaServiceInterface.remove(id);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
