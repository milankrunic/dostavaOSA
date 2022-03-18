package ftn.dostavaOSA2021.DostavaOSA2021.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.dostavaOSA2021.DostavaOSA2021.dto.ArtikalDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;

public interface ArtikalRepository extends JpaRepository<Artikal, Long>{

	Artikal findByIdArtikal(Long idArtikal);
	
	List<Artikal> findByProdavac(Prodavac prodavac);
	
//	List<ArtikalDTO> findAllArtikalDTO();
	
}
