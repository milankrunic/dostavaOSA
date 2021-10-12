package ftn.dostavaOSA2021.DostavaOSA2021.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;

public interface ArtikalRepository extends JpaRepository<Artikal, Long>{

	Artikal findByIdArtikal(Long idArtikal);
	
}
