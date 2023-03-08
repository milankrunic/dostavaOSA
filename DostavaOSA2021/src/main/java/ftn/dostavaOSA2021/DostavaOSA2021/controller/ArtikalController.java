package ftn.dostavaOSA2021.DostavaOSA2021.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import ftn.dostavaOSA2021.DostavaOSA2021.dto.ArtikalDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.dto.KomentarDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.ArtikalES;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceInterface.ArtikalEsServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Komentar;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Korisnik;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ArtikalServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KomentarServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KorisnikServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ProdavacServiceInterface;

@RestController
@RequestMapping(value = "api/artikal")
public class ArtikalController {
	
	public static final String ODABRANI_ARTIKAL = "odabraniArtikal";	
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/files";
	
	@Autowired
	ArtikalServiceInterface artikalServiceInterface;
	
	@Autowired
	ProdavacServiceInterface prodavacServiceInterface;
	
	@Autowired
	KomentarServiceInterface komentarServiceInterface;
	
	@Autowired
	ArtikalEsServiceInterface artikalEsServiceInterface;
	
	@Autowired
	KorisnikServiceInterface korisnikServiceInterface;
	
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
		
		if(artikal == null) {
			return new ResponseEntity<ArtikalDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ArtikalDTO>(new ArtikalDTO(artikal), HttpStatus.OK);
	}
	
	@PostMapping(consumes = { "multipart/form-data" })
	public ResponseEntity<ArtikalDTO> createEmployee(ArtikalDTO artikalDTO, @RequestParam("file") MultipartFile file) {
		try {

			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();

			// Cuvanje fajla lokalno
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(filePath));
			stream.write(file.getBytes());
			stream.close();
			
			Korisnik korisnik = korisnikServiceInterface.findOne(artikalDTO.getIdProdavac());
			Prodavac prodavac = prodavacServiceInterface.findByKorisnickoIme(korisnik.getKorisnickoIme());
			
			Artikal a = new Artikal();
			a.setNaziv(artikalDTO.getNaziv());
			a.setOpis(artikalDTO.getOpis());
			a.setCena(artikalDTO.getCena());
			a.setNazivFajla(fileName);
			a.setPutanjaFajla(filePath);
			a.setProdavac(prodavac);
			
			a = artikalServiceInterface.save(a);
			artikalEsServiceInterface.index(new ArtikalES(a));
			return new ResponseEntity<ArtikalDTO>(new ArtikalDTO(a), HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/downloadFile/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {
		// Ucitavanje fajla iz Resource
		Resource resource = artikalServiceInterface.loadFileAsResource(fileName); 

		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		// Ako tip nije mogao da se odredi vrati se na podrazumevani tip sadrzaja
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<ArtikalDTO> updateArtikal(@RequestBody ArtikalDTO artikalDTO, @PathVariable("id") Long id){
		
		Artikal artikal = artikalServiceInterface.findById(id);
		ArtikalES artikalES = artikalEsServiceInterface.findOne(id);
		Korisnik korisnik = korisnikServiceInterface.findOne(artikalDTO.getIdProdavac());
		Prodavac prodavac = prodavacServiceInterface.findByKorisnickoIme(korisnik.getKorisnickoIme());
		
		if(artikal == null && artikalES == null) {
			return new ResponseEntity<ArtikalDTO>(HttpStatus.BAD_REQUEST);
		}
		
		artikal.setNaziv(artikalDTO.getNaziv());
		artikal.setOpis(artikalDTO.getOpis());
		artikal.setCena(artikalDTO.getCena());
		artikal.setProdavac(prodavac);
		artikalES.setNaziv(artikalDTO.getNaziv());
		artikalES.setOpis(artikalDTO.getOpis());
		artikalES.setCena(artikalDTO.getCena());

		artikal = artikalServiceInterface.save(artikal);
		artikalEsServiceInterface.save(artikalES);
		return new ResponseEntity<ArtikalDTO>(new ArtikalDTO(artikal), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteArtikal(@PathVariable("id") Long id){
		
		Artikal artikal = artikalServiceInterface.findById(id);
		if(artikal != null) {
			artikalServiceInterface.remove(id);
			artikalEsServiceInterface.removeArtikalES(id);
			
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
