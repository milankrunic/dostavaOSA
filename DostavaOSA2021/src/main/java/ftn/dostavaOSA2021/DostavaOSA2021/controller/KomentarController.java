package ftn.dostavaOSA2021.DostavaOSA2021.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.dostavaOSA2021.DostavaOSA2021.dto.KomentarDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Komentar;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Kupac;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ArtikalServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KomentarServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KupacServiceInterface;

@RestController
@RequestMapping(value = "api/komentar")
public class KomentarController {
	
	@Autowired
	KomentarServiceInterface komentarServiceInterface;
	
	@Autowired
	ArtikalServiceInterface artikalServiceInterface;
	
	@Autowired
	KupacServiceInterface kupacServiceInterface;

	@GetMapping
	public ResponseEntity<List<KomentarDTO>> getKomentare(){

		List<Komentar> komentari = komentarServiceInterface.findAll();
		
		List<KomentarDTO> komentarDTO = new ArrayList<KomentarDTO>();
		for(Komentar kom: komentari) {
			komentarDTO.add(new KomentarDTO(kom));
		}
		return new ResponseEntity<List<KomentarDTO>>(komentarDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<KomentarDTO> getKomentar(@PathVariable("id") Long id){
		Komentar komentar = komentarServiceInterface.findById(id);
		
		if(komentar == null) {
			return new ResponseEntity<KomentarDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<KomentarDTO>(new KomentarDTO(komentar), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<KomentarDTO> addKomentar(@RequestBody KomentarDTO komentarDTO){

		Artikal artikal = artikalServiceInterface.findById(komentarDTO.getIdArtikla());
		Kupac kupac = kupacServiceInterface.findById(komentarDTO.getIdKupac());
		
		Komentar kom = new Komentar();
		kom.setTekst(komentarDTO.getTekst());
		kom.setOcena(komentarDTO.getOcena());
		kom.setPrihvacen(komentarDTO.isPrihvacen());
		kom.setArtikal(artikal);
		kom.setKupac(kupac);
		
		kom = komentarServiceInterface.save(kom);
		return new ResponseEntity<KomentarDTO>(new KomentarDTO(kom), HttpStatus.CREATED);
	}
	
}
