package ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.handlers;

import java.io.File;

import ftn.dostavaOSA2021.DostavaOSA2021.lucene.model.IndexUnit;

public abstract class DocumentHandler {

	public abstract IndexUnit getIndexUnit(File file);
	public abstract String getText(File file);

}
