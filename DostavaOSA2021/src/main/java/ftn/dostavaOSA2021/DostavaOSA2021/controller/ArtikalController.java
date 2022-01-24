package ftn.dostavaOSA2021.DostavaOSA2021.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ftn.dostavaOSA2021.DostavaOSA2021.dto.ArtikalDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.dto.KomentarDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Komentar;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ArtikalServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KomentarServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ProdavacServiceInterface;

@RestController
@RequestMapping(value = "api/artikal")
public class ArtikalController {
	
	public static final String ODABRANI_ARTIKAL = "odabraniArtikal";
	
	@Autowired
	ArtikalServiceInterface artikalServiceInterface;
	
	@Autowired
	ProdavacServiceInterface prodavacServiceInterface;
	
	@Autowired
	KomentarServiceInterface komentarServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<ArtikalDTO>> getArtikle(){

		List<Artikal> artikli = artikalServiceInterface.findAll();
		
		List<ArtikalDTO> artikalDTO = new ArrayList<ArtikalDTO>();
		for(Artikal art: artikli) {
			artikalDTO.add(new ArtikalDTO(art));
		}
		return new ResponseEntity<List<ArtikalDTO>>(artikalDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ArtikalDTO> getArtikal(@PathVariable("id") Long id, HttpSession session){
		Artikal artikal = artikalServiceInterface.findOne(id);

		session.setAttribute(ArtikalController.ODABRANI_ARTIKAL, artikal);
//		Artikal a = (Artikal) session.getAttribute(ArtikalController.ODABRANI_ARTIKAL);
//		
//		System.out.println("ARTIKAL JE: "+a.getNaziv());
		
		if(artikal == null) {
			return new ResponseEntity<ArtikalDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ArtikalDTO>(new ArtikalDTO(artikal), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ArtikalDTO> addArtikal(@RequestBody ArtikalDTO artikalDTO){

		Prodavac prodavac = prodavacServiceInterface.findById(artikalDTO.getIdProdavac());
		
		Artikal a = new Artikal();
		a.setNaziv(artikalDTO.getNaziv());
		a.setOpis(artikalDTO.getOpis());
		a.setCena(artikalDTO.getCena());
		a.setPutanjaSlike(null);
		a.setProdavac(prodavac);
		
		a = artikalServiceInterface.save(a);
		return new ResponseEntity<ArtikalDTO>(new ArtikalDTO(a), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<ArtikalDTO> updateArtikal(@RequestBody ArtikalDTO artikalDTO, @PathVariable("id") Long id){
		
		Artikal artikal = artikalServiceInterface.findById(id);
		Prodavac prodavac = prodavacServiceInterface.findById(artikalDTO.getIdProdavac());
		
		if(artikal == null) {
			return new ResponseEntity<ArtikalDTO>(HttpStatus.BAD_REQUEST);
		}
		
		artikal.setNaziv(artikalDTO.getNaziv());
		artikal.setOpis(artikalDTO.getOpis());
		artikal.setCena(artikalDTO.getCena());
//		artikal.setPutanjaSlike(artikalDTO.getPutanjaSlike());
		artikal.setProdavac(prodavac);

		artikal = artikalServiceInterface.save(artikal);
		return new ResponseEntity<ArtikalDTO>(new ArtikalDTO(artikal), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteArtikal(@PathVariable("id") Long id){
		Artikal artikal = artikalServiceInterface.findById(id);
		if(artikal != null) {
			artikalServiceInterface.remove(id);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/{id}/komentari")
	public ResponseEntity<List<KomentarDTO>> getKomentareArtikla(@PathVariable("id") Long id){
				
		Artikal artikal = artikalServiceInterface.findById(id);
		
		if(artikal == null) {
			return new ResponseEntity<List<KomentarDTO>>(HttpStatus.NOT_FOUND);
		}else {
			List<Komentar> komentari = komentarServiceInterface.findAllByArtikal(artikal);
			List<KomentarDTO> komentarDTO = new ArrayList<KomentarDTO>();
			for (Komentar komentar : komentari) {
				KomentarDTO dto = new KomentarDTO(komentar);
				komentarDTO.add(dto);
			}
			return new ResponseEntity<List<KomentarDTO>>(komentarDTO, HttpStatus.OK);
		}
		
	}

//	@SuppressWarnings("unchecked")
	@GetMapping(value = "/{id}/stavka")
	public ResponseEntity<ArtikalDTO> getPocetnaKorpa(@PathVariable("id") Long id, HttpSession session){
				
		Artikal artikal = artikalServiceInterface.findOne(id);
		
		if(artikal == null) {
			return new ResponseEntity<ArtikalDTO>(HttpStatus.NOT_FOUND);
		}else {
//			List<Artikal> artikli = (List<Artikal>) session.getAttribute(ArtikalController.ODABRANI_ARTIKAL);
//			if (!artikli.contains(artikal)) {
//				artikli.add(artikal);

//			}
		
		}	
		return new ResponseEntity<ArtikalDTO>(new ArtikalDTO(artikal), HttpStatus.OK);
				
	}
	
//	@GetMapping(value = "/{id}/artikli")
//	public ResponseEntity<List<ArtikalDTO>> getArtikleProdavcaKodAdmina(@PathVariable("id") Long id){
//				
//		Prodavac prodavac = prodavacServiceInterface.findOne(id);
//		
//		if(prodavac == null) {
//			return new ResponseEntity<List<ArtikalDTO>>(HttpStatus.NOT_FOUND);
//		}else {
//			List<Artikal> artikli = artikalServiceInterface.findAllByProdavac(prodavac);
//			List<ArtikalDTO> artikalDTO = new ArrayList<ArtikalDTO>();
//			for (Artikal artikal : artikli) {
//				ArtikalDTO dto = new ArtikalDTO(artikal);
//				artikalDTO.add(dto);
//			}
//			return new ResponseEntity<List<ArtikalDTO>>(artikalDTO, HttpStatus.OK);
//		}
//		
//	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/pocetnaKorpa")
	public ModelAndView pocetnaKorpa(@RequestParam Long id, HttpSession session ) throws IOException {
				
		Artikal artikal = artikalServiceInterface.findOne(id);
		
		List<Artikal> artikli = (List<Artikal>) session.getAttribute(ArtikalController.ODABRANI_ARTIKAL);
		if (!artikli.contains(artikal)) {
			artikli.add(artikal);

		}

		ModelAndView rezultat = new ModelAndView("pocetnaKorpa");
		rezultat.addObject("pocetnaKorpa",artikal);

		return rezultat;
	}

}
