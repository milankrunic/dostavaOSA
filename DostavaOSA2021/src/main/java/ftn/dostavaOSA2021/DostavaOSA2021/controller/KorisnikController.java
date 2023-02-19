package ftn.dostavaOSA2021.DostavaOSA2021.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.dostavaOSA2021.DostavaOSA2021.dto.KorisnikDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Korisnik;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.AdministratorServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KorisnikServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KupacServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ProdavacServiceInterface;

@RestController
@RequestMapping(value = "api/korisnik")
public class KorisnikController {
	
	public static final String KORISNIK_KEY = "prijavljeniKorisnik";
	
	@Autowired
	KorisnikServiceInterface korisnikServiceInterface;
	
	@Autowired
	AdministratorServiceInterface administratorServiceInterface;
	
	@Autowired
	ProdavacServiceInterface prodavacServiceInterface;
	
	@Autowired
	KupacServiceInterface kupacServiceInterface;
	
	@PostMapping(value = "/login")
	public ResponseEntity<KorisnikDTO> login(@RequestBody KorisnikDTO korisnikDTO, HttpSession session){
		
		Korisnik korisnik =  korisnikServiceInterface.findByKorImeAndLozinka(korisnikDTO.getKorIme(), korisnikDTO.getLozinka());
		
		session.setAttribute(KorisnikController.KORISNIK_KEY, korisnik);
				
		if (korisnik==null) {
			return new ResponseEntity<KorisnikDTO>(HttpStatus.NOT_FOUND); 
		}else{
			KorisnikDTO korDTO = new KorisnikDTO();
			korDTO.setIdKorisnik(korisnik.getIdKorisnik());
			korDTO.setKorIme(korisnik.getKorisnickoIme());
			korDTO.setLozinka(korisnik.getLozinka());
			korDTO.setTipKorisnika(korisnik.getTipKorisnika());
			korDTO.setBlokiran(korisnik.isBlokiran());

			return new ResponseEntity<KorisnikDTO>(korDTO,HttpStatus.OK);
		}

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		Korisnik korisnik = korisnikServiceInterface.findOne(id);
		if(korisnik != null) {
			korisnikServiceInterface.remove(id);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value = "/{id}/blokiranje")
	public ResponseEntity<KorisnikDTO> blok(@PathVariable("id") Long id){

		Korisnik korisnik = korisnikServiceInterface.findOne(id);
		
		if(korisnik.isBlokiran() == false) {
			korisnik.setBlokiran(true);
		}else {
			korisnik.setBlokiran(false);
		}
		
		korisnik = korisnikServiceInterface.save(korisnik);
		return new ResponseEntity<KorisnikDTO>(new KorisnikDTO(korisnik), HttpStatus.CREATED);
	}

}
