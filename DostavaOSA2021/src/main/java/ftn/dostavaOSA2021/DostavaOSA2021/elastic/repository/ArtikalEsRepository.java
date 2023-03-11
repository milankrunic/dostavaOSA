package ftn.dostavaOSA2021.DostavaOSA2021.elastic.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.ArtikalES;

@Repository
public interface ArtikalEsRepository extends ElasticsearchRepository<ArtikalES, String>{

	List<ArtikalES> findAllByNaziv(String naziv);
	
	List<ArtikalES> findAllByOpis(String opis);
	
	ArtikalES findByIdArtikla(Long idArtikalES);
	
	ArtikalES findByNaziv(String naziv);
	
}
