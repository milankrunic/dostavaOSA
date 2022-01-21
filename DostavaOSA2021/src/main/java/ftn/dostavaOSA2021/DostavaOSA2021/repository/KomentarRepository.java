package ftn.dostavaOSA2021.DostavaOSA2021.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Komentar;

public interface KomentarRepository extends JpaRepository<Komentar, Long>{

	Komentar findByIdKomentar(Long idKomentar);
	
	List<Komentar> findByArtikal(Artikal artikal);
	
}
