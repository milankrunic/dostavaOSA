package ftn.dostavaOSA2021.DostavaOSA2021.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.dostavaOSA2021.DostavaOSA2021.dto.ArtikalDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.analysers.SerbianAnalyzer;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.model.RequiredHighlight;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.model.ResultData;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.model.SearchType;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.model.SimpleQuery;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.search.QueryBuilder;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.search.ResultRetriever;

@RestController
@RequestMapping(value = "api/search")
public class SearchController {
	
	@PostMapping(value="/term/artikli", consumes="application/json")
	public ResponseEntity<List<ResultData>> searchTermQuery(@RequestBody SimpleQuery simpleQuery) throws Exception {		
		Query query= QueryBuilder.buildQuery(SearchType.REGULAR, simpleQuery.getField(), simpleQuery.getValue());
		List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
		rh.add(new RequiredHighlight(simpleQuery.getField(), simpleQuery.getValue()));
		List<ResultData> results = ResultRetriever.getResultsArtikal(query, rh);			
		return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
	}
	
	@PostMapping(value="/queryParser", consumes="application/json")
	public ResponseEntity<List<ResultData>> search(@RequestBody SimpleQuery simpleQuery) throws Exception {
		QueryParser qp=new QueryParser("title", new SerbianAnalyzer());			
		Query query=qp.parse(simpleQuery.getValue());			
		List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
		List<ResultData> results = ResultRetriever.getResultsArtikal(query, rh);
		return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
	}

}
