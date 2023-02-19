package ftn.dostavaOSA2021.DostavaOSA2021.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long>{

	Administrator findByKorisnik_korisnickoIme(String korisnickoIme);
	
}
