package ftn.dostavaOSA2021.DostavaOSA2021.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.dostavaOSA2021.DostavaOSA2021.model.ArtikalAkcija;

public interface ArtikalAkcijaRepository extends JpaRepository<ArtikalAkcija, Long>{

	ArtikalAkcija findByIdArtikalAkcija(Long idArtikalAkcija);
	
}
