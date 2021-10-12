package ftn.dostavaOSA2021.DostavaOSA2021.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Kupac;

public interface KupacRepository extends JpaRepository<Kupac, Long>{

	Kupac findByIdKupac(Long idKupac);
	
}
