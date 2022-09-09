package ftn.dostavaOSA2021.DostavaOSA2021.elastic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public void index(@RequestBody ArtikalEsDTO artikalEsDTO) {
		artikalEsServiceInterface.index(artikalEsDTO);
	}
	
	@PostMapping("/artikalNaziv")
	public List<ArtikalES> getByNaziv(@RequestBody TextRequestDTO textRequestDTO){
		return artikalEsServiceInterface.getArtikalByNaziv(textRequestDTO.getText());
	}

}
