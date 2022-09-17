package ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceImpl;

import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto.PorudzbinaEsDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto.SimpleQueryES;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.mapper.PorudzbinaMapper;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.PorudzbinaES;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.repository.PorudzbinaEsRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceInterface.PorudzbinaEsServiceInterface;

@Service
public class PorudzbinaEsService implements PorudzbinaEsServiceInterface{

	private final PorudzbinaEsRepository porudzbinaEsRepository;
	private final ElasticsearchRestTemplate elasticsearchRestTemplate;
	
	public PorudzbinaEsService(PorudzbinaEsRepository porudzbinaEsRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
		this.porudzbinaEsRepository = porudzbinaEsRepository;
		this.elasticsearchRestTemplate = elasticsearchRestTemplate;
	}

	@Override
	public void index(PorudzbinaES porudzbinaES) {
		porudzbinaEsRepository.save(porudzbinaES);
	}

	@Override
	public List<PorudzbinaES> getPorudzbinaByKomentar(String komentar) {
		return porudzbinaEsRepository.findAllByKomentar(komentar);
	}

	@Override
	public List<PorudzbinaEsDTO> findByOcena(double from, double to) {

		String range = from + "-" + to;
		
		QueryBuilder priceQuery = SearchQueryGenerator.createRangeQueryBuilder(new SimpleQueryES("ocena", range));
		
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
				.must(priceQuery);
		
		return PorudzbinaMapper.mapDtos(searchByBoolQuery(boolQuery));
		
	}

    private SearchHits<PorudzbinaES> searchByBoolQuery(BoolQueryBuilder boolQuery) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .build();

        return elasticsearchRestTemplate.search(searchQuery, PorudzbinaES.class,  IndexCoordinates.of("porudzbine"));
    }

}
