package ftn.dostavaOSA2021.DostavaOSA2021.lucene.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class IndexUnit {

	private String text;
	private String title;
	private List<String> keywords = new ArrayList<String>();
	private String fileName;
	private String fileDate;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileDate() {
		return fileDate;
	}
	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}
	
	public Document getLuceneDocument(){
		Document retVal = new Document();
		retVal.add(new TextField("text", text, Store.NO));
		retVal.add(new TextField("title", title, Store.YES));
		for (String keyword : keywords) {
			retVal.add(new TextField("keyword", keyword, Store.YES));
		}
		retVal.add(new StringField("filename", fileName, Store.YES));
		retVal.add(new TextField("filedate",fileDate,Store.YES));
		return retVal;
	}
}
