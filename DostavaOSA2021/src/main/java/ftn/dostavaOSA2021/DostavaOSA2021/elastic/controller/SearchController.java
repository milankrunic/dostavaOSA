package ftn.dostavaOSA2021.DostavaOSA2021.elastic.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto.ArtikalEsDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto.TextRequestDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.ArtikalES;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceInterface.ArtikalEsServiceInterface;

@RestController
@RequestMapping(value = "/search")
public class SearchController {
	
	@Autowired
	ArtikalEsServiceInterface artikalEsServiceInterface;
	
	@PostMapping("/artikal")
	public void index(@RequestBody ArtikalES artikalEs) {
		artikalEsServiceInterface.index(artikalEs);
	}
	
	@PostMapping("/artikalNaziv")
	public List<ArtikalES> getByNaziv(@RequestBody TextRequestDTO textRequestDTO){
		return artikalEsServiceInterface.getArtikalByNaziv(textRequestDTO.getText());
	}
	
	@PostMapping("/artikalOpis")
	public List<ArtikalES> getByOpis(@RequestBody TextRequestDTO textRequestDTO){
		return artikalEsServiceInterface.getArtikalByOpis(textRequestDTO.getText());
	}
	
	@PostMapping("/artikalCena")
	public List<ArtikalEsDTO> getByCena(@RequestParam(name = "from") double from, @RequestParam(name = "to") double to){
		return artikalEsServiceInterface.findByCena(from, to);
	}
	
	//reindexira fajlove
	@GetMapping("/reindex")
	public void reindex() {
		artikalEsServiceInterface.reindex();
	}

	@PostMapping(path = "/pdf", consumes = "multipart/form-data")
	public void uploadPdf(@ModelAttribute ArtikalEsDTO uploadModel) throws IOException{
		ArtikalES a = new ArtikalES();
		a.setNaziv(uploadModel.getNaziv());
		a.setCena(uploadModel.getCena());
		
		a = artikalEsServiceInterface.save(a);
		artikalEsServiceInterface.index(a);
		artikalEsServiceInterface.indexUploadFile(uploadModel);
	}
	
	@PostMapping("/nazivQuery")
	public List<ArtikalEsDTO> getByNaziv(@RequestParam(name = "naziv") String naziv){
		return artikalEsServiceInterface.findByNaziv(naziv);
	}
}
