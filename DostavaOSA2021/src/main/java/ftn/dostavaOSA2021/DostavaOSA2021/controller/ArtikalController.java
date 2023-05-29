package ftn.dostavaOSA2021.DostavaOSA2021.controller;

import java.io.IOException;
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
	public ResponseEntity<ArtikalDTO> createArtikal(ArtikalDTO artikalDTO, @RequestParam("file") MultipartFile file) throws Exception {
		
		return ResponseEntity.ok().body(artikalServiceInterface.save(artikalDTO, file));
		
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
	public ResponseEntity<ArtikalDTO> updateArtikal(@RequestBody ArtikalDTO artikalDTO, @PathVariable("id") Long id) throws IOException{
		
		return ResponseEntity.ok().body(artikalServiceInterface.update(id, artikalDTO));
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteArtikal(@PathVariable("id") Long id){
		
		Artikal artikal = artikalServiceInterface.findById(id);
		if(artikal != null) {
			ArtikalES artikalES = artikalEsServiceInterface.getOneByNaziv(artikal.getNaziv());
			artikalServiceInterface.remove(id);
			artikalEsServiceInterface.removeArtikalES(artikalES);
			
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
