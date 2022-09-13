package ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceInterface;

import java.io.IOException;
import java.util.List;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto.ArtikalEsDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.ArtikalES;


public interface ArtikalEsServiceInterface {

	void index(ArtikalES artikalEs);
	
	List<ArtikalES> getArtikalByNaziv(String naziv);
	
	void reindex();
	
	void indexUploadFile(ArtikalEsDTO artikalEsDTO) throws IOException;
	
}
