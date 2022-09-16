package ftn.dostavaOSA2021.DostavaOSA2021.elastic.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.PorudzbinaES;

@Repository
public interface PorudzbinaEsRepository extends ElasticsearchRepository<PorudzbinaES, String>{

	List<PorudzbinaES> findAllByKomentar(String komentar);
	
}
