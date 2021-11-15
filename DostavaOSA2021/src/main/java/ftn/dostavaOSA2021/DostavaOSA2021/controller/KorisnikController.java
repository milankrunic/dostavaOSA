package ftn.dostavaOSA2021.DostavaOSA2021.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.dostavaOSA2021.DostavaOSA2021.dto.KorisnikDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Korisnik;
import ftn.dostavaOSA2021.DostavaOSA2021.model.TipKorisnika;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.AdministratorServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KorisnikServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KupacServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ProdavacServiceInterface;

@RestController
@RequestMapping(value = "api/korisnik")
public class KorisnikController {
	
	@Autowired
	KorisnikServiceInterface korisnikServiceInterface;
	
	@Autowired
	AdministratorServiceInterface administratorServiceInterface;
	
	@Autowired
	ProdavacServiceInterface prodavacServiceInterface;
	
	@Autowired
	KupacServiceInterface kupacServiceInterface;
	
//	@GetMapping
//	public ResponseEntity<List<KorisnikDTO>> getKorisnici(){
//		List<Administrator> admini = administratorServiceInterface.findAll();
//		List<Prodavac> prodavci = prodavacServiceInterface.findAll();
//		List<Kupac> kupci = kupacServiceInterface.findAll();
//		List<KorisnikDTO> korisniciDTO = new ArrayList<KorisnikDTO>();
//		for (Administrator adm : admini) {
//			korisniciDTO.add(new KorisnikDTO(adm));
//		}
//		for (Prodavac pro : prodavci) {
//			korisniciDTO.add(new KorisnikDTO(pro));
//		}
//		for (Kupac kup : kupci) {
//			korisniciDTO.add(new KorisnikDTO(kup));
//		}
//		return new ResponseEntity<List<KorisnikDTO>>(korisniciDTO, HttpStatus.OK);
//	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<KorisnikDTO> login(@RequestBody KorisnikDTO korisnikDTO){

		Korisnik korisnik =  kupacServiceInterface.findByKorImeAndLozinka(korisnikDTO.getKorIme(), korisnikDTO.getLozinka());
		Korisnik admin = administratorServiceInterface.findByKorImeAndLozinka(korisnikDTO.getKorIme(), korisnikDTO.getLozinka());
		Korisnik prodavac = prodavacServiceInterface.findByKorImeAndLozinka(korisnikDTO.getKorIme(), korisnikDTO.getLozinka());
		
		if(korisnik==null) {
			korisnik = prodavac;
		}
		if(korisnik==null) {
			korisnik = admin;
		}
				
		if (korisnik==null) {
			return new ResponseEntity<KorisnikDTO>(HttpStatus.NOT_FOUND); 
		}else{
			KorisnikDTO korDTO = new KorisnikDTO();
			korDTO.setKorIme(korisnik.getKorisnickoIme());
			korDTO.setLozinka(korisnik.getLozinka());
			korDTO.setTipKorisnika(korisnik.getTipKorisnika());
			
			return new ResponseEntity<KorisnikDTO>(korDTO,HttpStatus.OK);
		}

	}

}
