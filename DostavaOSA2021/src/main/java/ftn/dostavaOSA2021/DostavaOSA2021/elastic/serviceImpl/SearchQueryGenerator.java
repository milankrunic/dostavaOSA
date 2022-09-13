package ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceImpl;

import org.elasticsearch.index.query.QueryBuilder;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto.SimpleQueryES;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.search.QueryBuilderCustom;
import ftn.dostavaOSA2021.DostavaOSA2021.util.SearchType;

public class SearchQueryGenerator {
	
    public static QueryBuilder createMatchQueryBuilder(SimpleQueryES simpleQueryES) {

        if(simpleQueryES.getValue().startsWith("\"") && simpleQueryES.getValue().endsWith("\"") ){
            return QueryBuilderCustom.buildQuery(SearchType.PHRASE, simpleQueryES.getField(), simpleQueryES.getValue());

        }else{
            return QueryBuilderCustom.buildQuery(SearchType.MATCH,simpleQueryES.getField(),simpleQueryES.getValue());
        }
    }
    
    public static QueryBuilder createRangeQueryBuilder(SimpleQueryES simpleQueryES){
        return QueryBuilderCustom.buildQuery(SearchType.RANGE, simpleQueryES.getField(), simpleQueryES.getValue());

    }

}

