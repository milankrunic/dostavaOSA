package ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceInterface;

import java.io.IOException;
import java.util.List;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto.ArtikalEsDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.ArtikalES;

public interface ArtikalEsServiceInterface {
	
	void index(ArtikalES artikalEs);
	
	List<ArtikalES> getArtikalByNaziv(String naziv);
	
	List<ArtikalES> getArtikalByOpis(String opis);
	
	void reindex();
	
	void indexUploadFile(ArtikalEsDTO artikalEsDTO) throws IOException;
	
	List<ArtikalEsDTO> findByNaziv(String naziv);
	
	List<ArtikalEsDTO> findByOpis(String opis);
	
	List<ArtikalEsDTO> findByCena(double from, double to);
	
	List<ArtikalEsDTO> findByNazivAndCena(String naziv, double from, double to);
	
	List<ArtikalEsDTO> findByNazivOrCena(String naziv, double from, double to);
	
	public void removeArtikalES(ArtikalES artikalES);
	
	public ArtikalES findOne(Long id);
	
	public ArtikalES getOneByNaziv(String naziv);
	
}
