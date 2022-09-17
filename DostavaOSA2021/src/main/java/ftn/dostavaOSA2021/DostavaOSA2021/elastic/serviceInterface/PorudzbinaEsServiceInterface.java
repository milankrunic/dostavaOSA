package ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceInterface;

import java.util.List;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto.PorudzbinaEsDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.PorudzbinaES;

public interface PorudzbinaEsServiceInterface {

	void index(PorudzbinaES porudzbinaES);
	
	List<PorudzbinaES> getPorudzbinaByKomentar(String komentar);
	
	List<PorudzbinaEsDTO> findByOcena(double from, double to);
	
	List<PorudzbinaEsDTO> findByKomentarAndOcena(String komentar, double from, double to);
	
	List<PorudzbinaEsDTO> findByKomentarOrOcena(String komentar, double from, double to);
	
}
