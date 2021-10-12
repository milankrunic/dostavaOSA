package ftn.dostavaOSA2021.DostavaOSA2021.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Akcija;

public interface AkcijaRepository extends JpaRepository<Akcija, Long>{

	Akcija findByIdAkcija(Long idAkcija);
	
}
