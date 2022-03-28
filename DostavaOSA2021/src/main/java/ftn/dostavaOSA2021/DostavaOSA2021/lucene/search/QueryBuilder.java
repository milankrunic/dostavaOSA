package ftn.dostavaOSA2021.DostavaOSA2021.lucene.search;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.BytesRef;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.common.unit.Fuzziness;

import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.analysers.SerbianAnalyzer;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.model.SearchType;


public class QueryBuilder {
	
	private static SerbianAnalyzer analyzer = new SerbianAnalyzer();
	private static int maxEdits = 1;
	
	public static int getMaxEdits(){
		return maxEdits;
	}
	
	public static void setMaxEdits(int maxEdits){
		QueryBuilder.maxEdits = maxEdits;
	}
	
	public static Query buildQuery(SearchType queryType, String field, String value) throws IllegalArgumentException, ParseException{
		QueryParser parser = new QueryParser(field, analyzer);
		String errorMessage = "";
		if(field == null || field.equals("")){
			errorMessage += "Field not specified";
		}
		if(value == null){
			if(!errorMessage.equals("")) errorMessage += "\n";
			errorMessage += "Value not specified";
		}
		if(!errorMessage.equals("")){
			throw new IllegalArgumentException(errorMessage);
		}
		
		Query query = null;
		if(queryType.equals(SearchType.REGULAR)){
			Term term = new Term(field, value);
			query = new TermQuery(term);
		}else if(queryType.equals(SearchType.FUZZY)){
			Term term = new Term(field, value);
			query = new FuzzyQuery(term, maxEdits);
		}else if(queryType.equals(SearchType.PREFIX)){
			Term term = new Term(field, value);
			query = new PrefixQuery(term);
		}else if(queryType.equals(SearchType.RANGE)){
			String[] values = value.split(" ");
			query = new TermRangeQuery(field, new BytesRef(values[0]), new BytesRef(values[1]), true, true);
		}else{
			String[] values = value.split(" ");
			PhraseQuery.Builder builder = new PhraseQuery.Builder();
			for(String word : values){
				Term term = new Term(field, word);
				builder.add(term);
			}
			query = builder.build();
		}
		
		return parser.parse(query.toString(field));
	}
	
//SA CASA (gore)
//-----------------------------------------------------------------------------------------------------------
//NIJE SA CASA (dole)	
	
//	private static int maxEdits = 1;
//	
//	public static int getMaxEdits(){
//		return maxEdits;
//	}
//	
//	public static void setMaxEdits(int maxEdits){
//		QueryBuilder.maxEdits = maxEdits;
//	}
//	
//	public static org.elasticsearch.index.query.QueryBuilder buildQuery(SearchType queryType, String field, String value) throws IllegalArgumentException, ParseException{
//
//		String errorMessage = "";
//		if(field == null || field.equals("")){
//			errorMessage += "Field not specified";
//		}
//		if(value == null){
//			if(!errorMessage.equals("")) errorMessage += "\n";
//			errorMessage += "Value not specified";
//		}
//		if(!errorMessage.equals("")){
//			throw new IllegalArgumentException(errorMessage);
//		}
//		
//		org.elasticsearch.index.query.QueryBuilder query = null;
//		if(queryType.equals(SearchType.REGULAR)){
//			query = QueryBuilders.termQuery(field, value);
//		}else if(queryType.equals(SearchType.FUZZY)){
//			query = QueryBuilders.fuzzyQuery(field, value).fuzziness(Fuzziness.fromEdits(maxEdits));
//		}else if(queryType.equals(SearchType.PREFIX)){
//			query = QueryBuilders.prefixQuery(field, value);
//		}else if(queryType.equals(SearchType.RANGE)){
//			String[] values = value.split(" ");
//			query = QueryBuilders.rangeQuery(field).from(values[0]).to(values[1]);
//		}else{
//			query = QueryBuilders.matchPhraseQuery(field, value);
//		}
//		
//		return query;
//	}

}
