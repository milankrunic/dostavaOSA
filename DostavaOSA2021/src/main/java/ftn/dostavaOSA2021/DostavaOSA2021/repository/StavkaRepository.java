package ftn.dostavaOSA2021.DostavaOSA2021.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Stavka;

public interface StavkaRepository extends JpaRepository<Stavka, Long>{

	Stavka findByIdStavka(Long idStavka);
	
}
