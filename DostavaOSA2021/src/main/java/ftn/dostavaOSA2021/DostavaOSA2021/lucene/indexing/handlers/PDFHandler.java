package ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.handlers;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.ArtikalES;

public class PDFHandler extends DocumentHandler {

	@Override
	public ArtikalES getIndexUnit(File file) {
		ArtikalES retVal = new ArtikalES();
		try {
			PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
			parser.parse();
			String opis = getText(parser);
			retVal.setOpis(opis);;

			// metadata extraction
			PDDocument pdf = parser.getPDDocument();
			PDDocumentInformation info = pdf.getDocumentInformation();

			String naziv = ""+info.getTitle();
			retVal.setNaziv(naziv);

//			String keywords = ""+info.getKeywords();
//			retVal.setKeywords(keywords);
//			
//			retVal.setFilename(file.getCanonicalPath());
//		
//			String nodificationDate= DateTools.dateToString(new Date(file.lastModified()), DateTools.Resolution.DAY);
			
			pdf.close();
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}

		return retVal;
	}

	@Override
	public String getText(File file) {
		try {
			PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
			parser.parse();
			PDFTextStripper textStripper = new PDFTextStripper();
			return textStripper.getText(parser.getPDDocument());
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}
		return null;
	}
	
	public String getText(PDFParser parser) {
		try {
			PDFTextStripper textStripper = new PDFTextStripper();
			return textStripper.getText(parser.getPDDocument());
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}
		return null;
	}

}


