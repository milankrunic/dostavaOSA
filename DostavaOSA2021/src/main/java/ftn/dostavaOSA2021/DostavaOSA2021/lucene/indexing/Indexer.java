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
}
