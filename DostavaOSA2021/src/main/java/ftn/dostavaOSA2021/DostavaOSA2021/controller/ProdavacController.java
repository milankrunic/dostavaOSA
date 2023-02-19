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

import ftn.dostavaOSA2021.DostavaOSA2021.dto.ArtikalDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.dto.ProdavacDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Korisnik;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;
import ftn.dostavaOSA2021.DostavaOSA2021.model.TipKorisnika;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ArtikalServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KorisnikServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ProdavacServiceInterface;

@RestController
@RequestMapping(value = "api/prodavac")
public class ProdavacController {
	
	@Autowired
	ProdavacServiceInterface prodavacServiceInterface;
	
	@Autowired
	KorisnikServiceInterface korisnikServiceInterface;
	
	@Autowired
	ArtikalServiceInterface artikalServiceInterface;
	
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
		Korisnik korisnik = korisnikServiceInterface.findOne(id);
		Prodavac prodavac = prodavacServiceInterface.findByKorisnickoIme(korisnik.getKorisnickoIme());
		
		if(prodavac == null) {
			return new ResponseEntity<ProdavacDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProdavacDTO>(new ProdavacDTO(prodavac), HttpStatus.OK);
	}
	
	@GetMapping(value = "/artikli")
	public ResponseEntity<List<ArtikalDTO>> getArtikleProdavca(HttpSession session){
				
		Korisnik korisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		Prodavac prodavac = prodavacServiceInterface.findByKorisnickoIme(korisnik.getKorisnickoIme());
		
		if(prodavac == null) {
			return new ResponseEntity<List<ArtikalDTO>>(HttpStatus.NOT_FOUND);
		}else {
			List<Artikal> artikli = artikalServiceInterface.findAllByProdavac(prodavac);
			List<ArtikalDTO> artikalDTO = new ArrayList<ArtikalDTO>();
			for (Artikal artikal : artikli) {
				ArtikalDTO dto = new ArtikalDTO(artikal);
				artikalDTO.add(dto);
			}
			return new ResponseEntity<List<ArtikalDTO>>(artikalDTO, HttpStatus.OK);
		}
		
	}
	
	@GetMapping(value = "/{id}/artikli")
	public ResponseEntity<List<ArtikalDTO>> getArtikleProdavcaKodAdmina(@PathVariable("id") Long id, HttpSession session){
		
		Korisnik korisnik = korisnikServiceInterface.findOne(id);
		Prodavac prodavac = prodavacServiceInterface.findByKorisnickoIme(korisnik.getKorisnickoIme());
		
		if(prodavac == null) {
			return new ResponseEntity<List<ArtikalDTO>>(HttpStatus.NOT_FOUND);
		}else {
			List<Artikal> artikli = artikalServiceInterface.findAllByProdavac(prodavac);
			List<ArtikalDTO> artikalDTO = new ArrayList<ArtikalDTO>();
			for (Artikal artikal : artikli) {
				session.setAttribute(ArtikalController.ODABRANI_ARTIKAL, artikal);
				Artikal ar = (Artikal) session.getAttribute(ArtikalController.ODABRANI_ARTIKAL);
				System.out.println("Artikal je: "+ar.getNaziv());
				ArtikalDTO dto = new ArtikalDTO(artikal);
				artikalDTO.add(dto);
			}
			return new ResponseEntity<List<ArtikalDTO>>(artikalDTO, HttpStatus.OK);
		}	
	}
	
	@PostMapping
	public ResponseEntity<ProdavacDTO> addProdavac(@RequestBody ProdavacDTO prodavacDTO){

		Korisnik korisnik = new Korisnik();
		korisnik.setIme(prodavacDTO.getIme());
		korisnik.setPrezime(prodavacDTO.getPrezime());
		korisnik.setKorisnickoIme(prodavacDTO.getKorIme());
		korisnik.setLozinka(prodavacDTO.getLozinka());
		korisnik.setBlokiran(prodavacDTO.isBlokiran());
		korisnik.setTipKorisnika(TipKorisnika.PRODAVAC);
		
		korisnik = korisnikServiceInterface.save(korisnik);
		
		Prodavac prodavac = new Prodavac(); 
		prodavac.setAdresa(prodavacDTO.getAdresa());
		prodavac.setNazivProdavca(prodavacDTO.getNazivProdavca());
		prodavac.setEmail(prodavacDTO.getEmail());
		prodavac.setPoslujeOd(prodavacDTO.getPoslujeOd());
		prodavac.setKorisnik(korisnik);
		
		prodavac = prodavacServiceInterface.save(prodavac);
		return new ResponseEntity<ProdavacDTO>(new ProdavacDTO(prodavac), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<ProdavacDTO> updateProdavac(@RequestBody ProdavacDTO prodavacDTO, @PathVariable("id") Long id){

		Korisnik korisnik = korisnikServiceInterface.findOne(id);
		Prodavac prodavac = prodavacServiceInterface.findByKorisnickoIme(korisnik.getKorisnickoIme());
		
		if(prodavac == null) {
			return new ResponseEntity<ProdavacDTO>(HttpStatus.BAD_REQUEST);
		}
		
		korisnik.setIme(prodavacDTO.getIme());
		korisnik.setPrezime(prodavacDTO.getPrezime());
		korisnik.setKorisnickoIme(prodavacDTO.getKorIme());
		korisnik.setLozinka(prodavacDTO.getLozinka());
		
		korisnik = korisnikServiceInterface.save(korisnik);
		
		prodavac.setAdresa(prodavacDTO.getAdresa());
		prodavac.setNazivProdavca(prodavacDTO.getNazivProdavca());
		prodavac.setEmail(prodavacDTO.getEmail());
		prodavac.setPoslujeOd(prodavacDTO.getPoslujeOd());
		prodavac.setKorisnik(korisnik);

		prodavac = prodavacServiceInterface.save(prodavac);
		return new ResponseEntity<ProdavacDTO>(new ProdavacDTO(prodavac), HttpStatus.OK);
	}
	
}
