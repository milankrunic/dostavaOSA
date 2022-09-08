package ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.handlers;

import java.io.File;

public abstract class DocumentHandler {
	/**
	 * Od prosledjene datoteke se konstruise Lucene Document
	 * 
	 * @param file
	 *            datoteka u kojoj se nalaze informacije
	 * @return Lucene Document
	 */
//	public abstract Book getIndexUnit(File file);
	public abstract String getText(File file);

}
