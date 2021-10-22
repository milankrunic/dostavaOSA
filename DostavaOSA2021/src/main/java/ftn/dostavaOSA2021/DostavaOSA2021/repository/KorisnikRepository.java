package ftn.dostavaOSA2021.DostavaOSA2021.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{
	
	Korisnik findByKorisnickoImeAndLozinka(String korisnickoIme, String lozinka);
	
}
