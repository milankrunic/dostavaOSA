package ftn.dostavaOSA2021.DostavaOSA2021.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	public static final String KOMENTAR_KEY = "odabraniKomentar";
	
	@Autowired
	KomentarServiceInterface komentarServiceInterface;
	
	@Autowired
	ArtikalServiceInterface artikalServiceInterface;
	
	@Autowired
	KupacServiceInterface kupacServiceInterface;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;
	
	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}

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
	public ResponseEntity<KomentarDTO> addKomentar(@RequestBody KomentarDTO komentarDTO, HttpSession session){

		Artikal artikal = (Artikal) session.getAttribute(ArtikalController.ODABRANI_ARTIKAL);
		Kupac kupac = (Kupac) session.getAttribute(KorisnikController.KORISNIK_KEY);
		
		Komentar kom = new Komentar();
		kom.setTekst(komentarDTO.getTekst());
		kom.setOcena(komentarDTO.getOcena());
		kom.setPrihvacen(komentarDTO.isPrihvacen());
		kom.setArtikal(artikal);
		kom.setKupac(kupac);
		
		kom = komentarServiceInterface.save(kom);
		return new ResponseEntity<KomentarDTO>(new KomentarDTO(kom), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/odobrenje")
	public void odobri(@RequestParam Long id, HttpServletResponse response) throws IOException {

		Komentar komentar = komentarServiceInterface.findOne(id);
		
		if(komentar.isPrihvacen() == false) {
			komentar.setPrihvacen(true);;
		}else {
			komentar.setPrihvacen(true);;
		}
		
		komentarServiceInterface.save(komentar);
		
		response.sendRedirect(baseURL + "admin.html");

	}
	
	@PostMapping(value = "/zabrani")
	public void zabrani(@RequestParam Long id, HttpServletResponse response) throws IOException {

		Komentar komentar = komentarServiceInterface.findOne(id);
		
		if(komentar.isPrihvacen() == true) {
			komentar.setPrihvacen(false);;
		}else {
			komentar.setPrihvacen(false);;
		}
		
		komentarServiceInterface.save(komentar);

		response.sendRedirect(baseURL + "admin.html");
	}
	
	@PostMapping(value = "/arhivirajProdavac")
	public void arhiviraj(@RequestParam Long id, HttpServletResponse response) throws IOException {

		Komentar komentar = komentarServiceInterface.findOne(id);
		
		if(komentar.isArhiviran() == false) {
			komentar.setArhiviran(true);;
		}else {
			komentar.setArhiviran(true);;
		}
		
		komentarServiceInterface.save(komentar);

		response.sendRedirect(baseURL + "prodavac.html");
	}
	
}
