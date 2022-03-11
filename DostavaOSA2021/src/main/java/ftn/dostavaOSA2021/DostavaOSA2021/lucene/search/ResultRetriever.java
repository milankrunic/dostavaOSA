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
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import ftn.dostavaOSA2021.DostavaOSA2021.dto.ArtikalDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.analysers.SerbianAnalyzer;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.handlers.DocumentHandler;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.handlers.PDFHandler;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.model.RequiredHighlight;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.model.ResultData;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;

public class ResultRetriever {
	
	private static int maxHits = 10;

	public static void setMaxHits(int maxHits) {
		ResultRetriever.maxHits = maxHits;
	}

	public static int getMaxHits() {
		return ResultRetriever.maxHits;
	}
	
	public static List<ResultData> getResultsArtikal(Query query,
			List<RequiredHighlight> requiredHighlights) {
		if (query == null) {
			return null;
		}
		try {
			Directory indexDir = new SimpleFSDirectory(FileSystems.getDefault().getPath(ResourceBundle.getBundle("application").getString("index")));
			DirectoryReader reader = DirectoryReader.open(indexDir);
			IndexSearcher is = new IndexSearcher(reader);
			TopScoreDocCollector collector = TopScoreDocCollector.create(maxHits);

			List<ResultData> results = new ArrayList<ResultData>();

			is.search(query, collector);
			ScoreDoc[] hits = collector.topDocs().scoreDocs;

			ResultData rd;
			Document doc;
			Highlighter hl;
			SerbianAnalyzer sa = new SerbianAnalyzer();
			
			for (ScoreDoc sd : hits) {
				doc = is.doc(sd.doc);
				String[] allKeywords = doc.getValues("keyword");
				String keywords = "";
				for (String keyword : allKeywords) {
					keywords += keyword.trim() + " ";
				}
				keywords = keywords.trim();
				//za pocetak po nazivu i opisu
				String title = doc.get("title");
				String location = doc.get("filename");
				String highlight = "";
				for (RequiredHighlight rh : requiredHighlights) {
					hl = new Highlighter(new QueryScorer(query, reader, rh.getFieldName()));
					try{
						highlight += hl.getBestFragment(sa, rh.getFieldName(),
								"" + getDocumentText(location));
					}catch (InvalidTokenOffsetsException e) {
						//throw new IllegalArgumentException("Unable to make highlight"); //kod profesora zakomentarisano
					}
				}
//				ar = new Artikal(idArtikal, naziv, opis, cena, putanjaSlike, artikalAkcija, prodavac, stavke);
//				results.add(ar);
				rd = new ResultData(title, keywords, location,
						highlight);
				results.add(rd);
			}
			reader.close();
			return results;

		} catch (IOException e) {
			throw new IllegalArgumentException(
					"U prosledjenom direktorijumu ne postoje indeksi ili je direktorijum zakljucan");
		} 
	}
	
	private static String getDocumentText(String location){
		File file = new File(location);
		DocumentHandler handler = getHandler(location);
		return handler.getText(file);
	}
	
	// ne znam dal treba
	
	protected static DocumentHandler getHandler(String fileName){
		if(fileName.endsWith(".txt")){
//			return new TextDocHandler();
		}else if(fileName.endsWith(".pdf")){
			return new PDFHandler();
		}
		return null;
	}

}
