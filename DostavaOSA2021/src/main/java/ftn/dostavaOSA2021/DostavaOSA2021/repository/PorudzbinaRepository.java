package ftn.dostavaOSA2021.DostavaOSA2021.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Porudzbina;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Long>{

	Porudzbina findByIdPorudzbina(Long idPorudzbina);
	
}
