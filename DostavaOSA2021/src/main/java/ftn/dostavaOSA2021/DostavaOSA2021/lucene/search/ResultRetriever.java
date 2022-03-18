package ftn.dostavaOSA2021.DostavaOSA2021.lucene.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.FileSystems;
import java.util.ResourceBundle;

import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.SimpleFSDirectory;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.elasticsearch.index.query.QueryBuilder;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ftn.dostavaOSA2021.DostavaOSA2021.dto.ArtikalDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.analysers.SerbianAnalyzer;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.handlers.DocumentHandler;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.handlers.PDFHandler;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.model.RequiredHighlight;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.model.ResultData;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;

public class ResultRetriever {
	
	 private static final Logger log = LoggerFactory.getLogger(ResultRetriever.class);
	private static int maxHits = 10;

	public static void setMaxHits(int maxHits) {
		ResultRetriever.maxHits = maxHits;
	}

	public static int getMaxHits() {
		return ResultRetriever.maxHits;
	}
	
    private static JestClient client;

    static {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200")
                .multiThreaded(true)
                .build());
        ResultRetriever.client = factory.getObject();

    }
	
//	public static List<ResultData> getResultsArtikal(Query query,
//			List<RequiredHighlight> requiredHighlights) {
//		if (query == null) {
//			return null;
//		}
//		try {
//			Directory indexDir = new SimpleFSDirectory(FileSystems.getDefault().getPath(ResourceBundle.getBundle("application").getString("index")));
//			DirectoryReader reader = DirectoryReader.open(indexDir);
//			IndexSearcher is = new IndexSearcher(reader);
//			TopScoreDocCollector collector = TopScoreDocCollector.create(maxHits);
//
//			List<ResultData> results = new ArrayList<ResultData>();
//
//			is.search(query, collector);
//			ScoreDoc[] hits = collector.topDocs().scoreDocs;
//
//			ResultData rd;
//			Document doc;
//			Highlighter hl;
//			SerbianAnalyzer sa = new SerbianAnalyzer();
//			
//			for (ScoreDoc sd : hits) {
//				doc = is.doc(sd.doc);
//				String[] allKeywords = doc.getValues("keyword");
//				String keywords = "";
//				for (String keyword : allKeywords) {
//					keywords += keyword.trim() + " ";
//				}
//				keywords = keywords.trim();
//				//za pocetak po nazivu i opisu
//				String title = doc.get("title");
//				String location = doc.get("filename");
//				String highlight = "";
//				for (RequiredHighlight rh : requiredHighlights) {
//					hl = new Highlighter(new QueryScorer(query, reader, rh.getFieldName()));
//					try{
//						highlight += hl.getBestFragment(sa, rh.getFieldName(), ""/* + getDocumentText(location)*/);
//					}catch (InvalidTokenOffsetsException e) {
//						//throw new IllegalArgumentException("Unable to make highlight"); //kod profesora zakomentarisano
//					}
//				}
//				
////				ar = new Artikal(idArtikal, naziv, opis, cena, putanjaSlike, artikalAkcija, prodavac, stavke);
////				results.add(ar);
//				rd = new ResultData(title, keywords, location,
//						highlight);
//				results.add(rd);
//			}
//			reader.close();
//			return results;
//
//		} catch (IOException e) {
//			throw new IllegalArgumentException(
//					"U prosledjenom direktorijumu ne postoje indeksi ili je direktorijum zakljucan");
//		} 
//	}
	
    public static List<ArtikalDTO> getResultsArtikal(QueryBuilder query, List<RequiredHighlight> highlights) {
        if(query == null){
            throw new IllegalArgumentException();
        }

        List<ArtikalDTO> results = new ArrayList<>();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(query);
        searchSourceBuilder.size(maxHits);

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("naziv");
        highlightBuilder.field("opis");
        highlightBuilder.field("cena");

        highlightBuilder.preTags("<spam style='color:red'>").postTags("</spam>");
        highlightBuilder.fragmentSize(200);
        searchSourceBuilder.highlighter(highlightBuilder);
        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex("artikal")
                .addType("artikal")
                .build();
        SearchResult result;

        try {
            result = client.execute(search);
            if(result.isSucceeded()) {
                log.warn("Search succeed");
            }else {
                log.warn("An error has occurred during searching: " + result.getErrorMessage());

            }
            List<SearchResult.Hit<ArtikalDTO, Void>> hits = result.getHits(ArtikalDTO.class);
            ArtikalDTO rd = new ArtikalDTO();

            for (SearchResult.Hit<ArtikalDTO, Void> sd : hits) {
                String highlight = "";
                for (String hf : sd.highlight.keySet() ) {
                    for (RequiredHighlight rh : highlights) {
                        if(hf.equals(rh.getFieldName())){
                            highlight += sd.highlight.get(hf).toString();
                        }
                    }
                }
                rd.setNaziv(sd.source.getNaziv());
                rd.setOpis(sd.source.getOpis());
                rd.setCena(sd.source.getCena());
                results.add(rd);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

}
