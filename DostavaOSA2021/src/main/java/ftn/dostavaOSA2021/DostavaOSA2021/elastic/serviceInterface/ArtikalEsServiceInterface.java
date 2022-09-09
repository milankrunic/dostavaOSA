package ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceInterface;

import java.util.List;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto.ArtikalEsDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.ArtikalES;


public interface ArtikalEsServiceInterface {

	void index(ArtikalEsDTO artikalEsDTO);
	
	List<ArtikalES> getArtikalByNaziv(String naziv);
	
}
