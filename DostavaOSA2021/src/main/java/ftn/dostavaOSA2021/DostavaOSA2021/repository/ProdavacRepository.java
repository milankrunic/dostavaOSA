package ftn.dostavaOSA2021.DostavaOSA2021.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;

public interface ProdavacRepository extends JpaRepository<Prodavac, Long>{

	Prodavac findByIdKorisnik(Long idKorisnik);
	
	Prodavac findByKorisnickoImeAndLozinka(String korisnickoIme, String lozinka);
	
}
