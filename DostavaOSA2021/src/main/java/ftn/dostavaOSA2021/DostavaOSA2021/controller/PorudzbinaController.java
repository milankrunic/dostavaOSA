package ftn.dostavaOSA2021.DostavaOSA2021.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.dostavaOSA2021.DostavaOSA2021.dto.PorudzbinaDTO;
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
	
//	@PostMapping
//	public ResponseEntity<PorudzbinaDTO> addPorudzbina(@RequestBody PorudzbinaDTO porudzbinaDTO){
//
//		Kupac kupac = kupacServiceInterface.findById(porudzbinaDTO.getIdKupca());
//		
//		Porudzbina por = new Porudzbina();
//		por.setSatnica(new Date());
//		por.setOcena(porudzbinaDTO.getOcena());
//		por.setKomentar(porudzbinaDTO.getKomentar());
//		por.setDostavljeno(porudzbinaDTO.isDostavljeno()); //?
//		por.setAnonimanKomentar(porudzbinaDTO.isAnonimanKomentar()); //?
//		por.setArhiviranKomentar(porudzbinaDTO.isArhiviranKomentar()); //?
//		por.setKupac(kupac);
//		
//		por = porudzbinaServiceInterface.save(por);
//		return new ResponseEntity<PorudzbinaDTO>(new PorudzbinaDTO(por), HttpStatus.CREATED);
//	}

//	@PutMapping(value = "/{id}", consumes = "application/json")
//	public ResponseEntity<PorudzbinaDTO> updatePorudzbina(@RequestBody PorudzbinaDTO porudzbinaDTO, @PathVariable("id") Long id){
//		
//		Porudzbina porudzbina = porudzbinaServiceInterface.findById(id);
//		Kupac kupac = kupacServiceInterface.findById(porudzbinaDTO.getIdKupca());
//		
//		if(porudzbina == null) {
//			return new ResponseEntity<PorudzbinaDTO>(HttpStatus.BAD_REQUEST);
//		}
//		
//		porudzbina.setSatnica(porudzbinaDTO.getSatnica());
//		porudzbina.setOcena(porudzbinaDTO.getOcena());
//		porudzbina.setKomentar(porudzbinaDTO.getKomentar());
//		porudzbina.setDostavljeno(porudzbinaDTO.isDostavljeno()); //?
//		porudzbina.setAnonimanKomentar(porudzbinaDTO.isAnonimanKomentar()); //?
//		porudzbina.setArhiviranKomentar(porudzbinaDTO.isArhiviranKomentar()); //?
//		porudzbina.setKupac(kupac);
//
//		porudzbina = porudzbinaServiceInterface.save(porudzbina);
//		return new ResponseEntity<PorudzbinaDTO>(new PorudzbinaDTO(porudzbina), HttpStatus.OK);
//	}
	
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
