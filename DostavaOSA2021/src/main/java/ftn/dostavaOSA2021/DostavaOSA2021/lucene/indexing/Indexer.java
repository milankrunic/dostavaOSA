package ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.nio.file.FileSystems;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.SimpleFSDirectory;

import org.springframework.stereotype.Service;

import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.analysers.SerbianAnalyzer;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.handlers.DocumentHandler;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.handlers.PDFHandler;

//@Service //verovatno treba da pise service mozda i ne
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
//			return new TextDocHandler();
		}else if(fileName.endsWith(".pdf")){
			return new PDFHandler();
//		}else if(fileName.endsWith(".doc")){
//			return new WordHandler();
//		}else if(fileName.endsWith(".docx")){
//			return new Word2007Handler();
		}else{
			return null;
		}
		return null; //verovatno nije dobro
	}
}
