package ftn.dostavaOSA2021.DostavaOSA2021.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.dostavaOSA2021.DostavaOSA2021.dto.KorisnikDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Korisnik;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KorisnikServiceInterface;

@RestController
@RequestMapping(value = "api/login")
public class LoginController {

	@Autowired
	KorisnikServiceInterface korisnikServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<KorisnikDTO>> getKorisnici(){
		List<Korisnik> korisnici = korisnikServiceInterface.findAll();
		List<KorisnikDTO> korisniciDTO = new ArrayList<KorisnikDTO>();
		for (Korisnik kor : korisnici) {
			korisniciDTO.add(new KorisnikDTO(kor));
		}
		return new ResponseEntity<List<KorisnikDTO>>(korisniciDTO, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<KorisnikDTO> login(@RequestBody KorisnikDTO korisnikDTO){
		Korisnik korisnik = korisnikServiceInterface.findByKorImeAndLozinka(korisnikDTO.getKorIme(), korisnikDTO.getLozinka());
		if (korisnik==null) {
			return new ResponseEntity<KorisnikDTO>(HttpStatus.NOT_FOUND); 
		}else {
			KorisnikDTO korDTO = new KorisnikDTO();
			korDTO.setKorIme(korisnik.getKorisnickoIme());
			korDTO.setLozinka(korisnik.getLozinka());

			return new ResponseEntity<KorisnikDTO>(korDTO, HttpStatus.OK);
		}
	}
	
	
}
