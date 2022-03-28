package ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.nio.file.FileSystems;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
//import io.searchbox.client.JestClient;
//import io.searchbox.client.JestClientFactory;
//import io.searchbox.client.JestResult;
//import io.searchbox.client.config.HttpClientConfig;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.SimpleFSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import io.searchbox.core.Index;
//import io.searchbox.core.DocumentResult;
//import io.searchbox.core.Get;
//import io.searchbox.core.Delete;

import org.springframework.stereotype.Service;

import ftn.dostavaOSA2021.DostavaOSA2021.dto.ArtikalDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.analysers.SerbianAnalyzer;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.handlers.DocumentHandler;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.handlers.PDFHandler;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;

//@Service
public class Indexer {
	
	private File indexDirPath;
	private IndexWriter indexWriter;
	private Directory indexDir;
	
	private static Indexer indexer = new Indexer(true);
	
	public static Indexer getInstance(){
		return indexer;
	}
	
	private Indexer(String path, boolean restart) {
		System.out.println("PATH: " + path);
		IndexWriterConfig iwc = new IndexWriterConfig(new SerbianAnalyzer());
		if(restart){
			iwc.setOpenMode(OpenMode.CREATE);
		}else{
			iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
		}
		
		try{
			this.indexDir = new SimpleFSDirectory(FileSystems.getDefault().getPath(path));
			this.indexWriter = new IndexWriter(this.indexDir, iwc);
		}catch(IOException ioe){
			throw new IllegalArgumentException("Path not correct");
		}
		
	}
	
	private Indexer(boolean restart){
		this(ResourceBundle.getBundle("application").getString("index"), restart);
	}

	private Indexer(){
		this(false);
	}
	
	public IndexWriter getIndexWriter(){
		return this.indexWriter;
	}
	
	public Directory getIndexDir(){
		return indexDir;
	}
	
	public File getIndexDirPath(){
		return indexDirPath;
	}
	
	public int index(File file){		
		DocumentHandler handler = null;
		String fileName = null;
		try {
			File[] files;
			if(file.isDirectory()){
				files = file.listFiles();
			}else{
				files = new File[1];
				files[0] = file;
			}
			for(File newFile : files){
				if(newFile.isFile()){
					fileName = newFile.getName();
					handler = getHandler(fileName);
					if(handler == null){
						System.out.println("Nije moguce indeksirati dokument sa nazivom: " + fileName);
						continue;
					}
					this.indexWriter.addDocument(handler.getIndexUnit(newFile).getLuceneDocument());
				} else if (newFile.isDirectory()){
					index(newFile);
				}
			}
			this.indexWriter.commit();
			System.out.println("indexing done");
		} catch (IOException e) {
			System.out.println("indexing NOT done");
		}
		return this.indexWriter.numDocs();
	}
	
	public DocumentHandler getHandler(String fileName){
		if(fileName.endsWith(".txt")){
			return null;
		}else if(fileName.endsWith(".pdf")){
			return new PDFHandler();
//		}else if(fileName.endsWith(".doc")){
//			return new WordHandler();
//		}else if(fileName.endsWith(".docx")){
//			return new Word2007Handler();
		}else{
			return null;
		}

	}
	
//SA CASA (gore)
//-----------------------------------------------------------------------------------------------------------
//NIJE SA CASA (dole)
	
//TREBA I @Service GORE IZNAD SVEGA
	
//    private JestClient jestClient;
//
////  private static final Logger log = LoggerFactory.getLogger(Indexer.class);
//
//  private static Indexer indexer = new Indexer();
//
//  public static Indexer getInstance(){ return indexer; }
//
//  private Indexer() {
//      JestClientFactory factory = new JestClientFactory();
//      factory.setHttpClientConfig(
//              new HttpClientConfig.Builder("http://localhost:9200")
//                      .multiThreaded(true)
//                      .defaultMaxTotalConnectionPerRoute(2)
//                      .maxTotalConnection(10)
//                      .build());
//      jestClient = factory.getObject();
//  }
//
//  //verovatno treba artikalDTO da stoji
//  public int indexArtikal(List<Artikal> artikli) throws IOException {
//      JestResult result = null;
//      int retVal = 0;
//      for(Artikal artikalDTO : artikli){
//          Index index = new Index.Builder(artikalDTO).index("artikal").type("artikal").id(artikalDTO.getIdArtikal().toString()).build();
//          result = jestClient.execute(index);
//      }
//      if(result.isSucceeded()){
////          log.warn("Artikal indexing has successful finished.");
//      	System.out.println("Artikal indexing has successful finished.");
//          return retVal += artikli.size();
//      }else{
////          log.warn("Artikal indexing has failed.  " + result.getErrorMessage());
//      	System.out.println("Artikal indexing has failed.  " + result.getErrorMessage());
//          return -1;
//      }
//  }
//
//  public boolean addArtikal(ArtikalDTO artikalDTO){
//      Index index = new Index.Builder(artikalDTO).index("artikal").type("artikal").id(artikalDTO.getIdArtikla().toString()).build();
//      JestResult result;
//      try {
//          result = jestClient.execute(index);
////          log.warn("Inserting artikal with id: " + artikalDTO.getIdArtikla() + " in index structure is succeeded: " + result.isSucceeded());
//          System.out.println("Inserting artikal with id: " + artikalDTO.getIdArtikla() + " in index structure is succeeded: " + result.isSucceeded());
//          return result.isSucceeded();
//      } catch (IOException e) {
//          e.printStackTrace();
//      }
//      return false;
//  }
//
//  public boolean existArtikal(int id) throws IOException {
//      DocumentResult result = jestClient.execute(new Get.Builder("artikal", id + "").build());
//      return result.isSucceeded();
//  }
//
//  public boolean deleteArtikal(int id){
//      JestResult result;
//      try {
//          result = jestClient.execute(new Delete.Builder(id+"")
//                  .index("artikal")
//                  .type("artikal")
//                  .build());
////          log.warn("Deleting artikal with id: " + id + " in index structure is succeeded: " + result.isSucceeded());
//          System.out.println("Deleting artikal with id: " + id + " in index structure is succeeded: " + result.isSucceeded());
//          if(result.isSucceeded())
//              return true;
//      } catch (IOException e) {
//          e.printStackTrace();
//      }
//      return false;
//  }
}
