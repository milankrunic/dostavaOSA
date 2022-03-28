package ftn.dostavaOSA2021.DostavaOSA2021.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.Indexer;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;
import ftn.dostavaOSA2021.DostavaOSA2021.service.ArtikalService;
import ftn.dostavaOSA2021.DostavaOSA2021.service.ProdavacService;

@RestController
@RequestMapping(value = "/api")
public class IndexController {

	//TAKODJE U POM.XML TREBA SE MENJATI
	private static String DATA_DIR_PATH;
	
	static {
		ResourceBundle rb=ResourceBundle.getBundle("application");
		DATA_DIR_PATH=rb.getString("dataDir");
	}
	
	@GetMapping("/reindex")
	public ResponseEntity<String> index() throws IOException {
		File dataDir = getResourceFilePath(DATA_DIR_PATH);
		long start = new Date().getTime();
		int numIndexed = Indexer.getInstance().index(dataDir);
		long end = new Date().getTime();
		String text = "Indexing " + numIndexed + " files took "
				+ (end - start) + " milliseconds";
		return new ResponseEntity<String>(text, HttpStatus.OK);
	}
	
	private File getResourceFilePath(String path) {
	    URL url = this.getClass().getClassLoader().getResource(path);
	    File file = null;
	    try {
	        file = new File(url.toURI());
	    } catch (URISyntaxException e) {
	        file = new File(url.getPath());
	    }   
	    return file;
	}
	
//SA CASA (gore)
//-----------------------------------------------------------------------------------------------------------
//NIJE SA CASA (dole)
	
//    @Autowired
//    private Indexer indexer;
//
//    @Autowired
//    private ArtikalService artikalService;
//    
//    @Autowired
//    private ProdavacService prodavacService;
//    
//
//    //TREBA SE OVO PROVERITI
//    @GetMapping("/reindex-artikal/{id}")
//    public ResponseEntity<String> indexArtikal(@PathVariable("id") Long id) throws IOException {
////    	File dataDir = getResourceFilePath(DATA_DIR_PATH);
//    	Prodavac prodavac = prodavacService.findOne(id);
//        long start = new Date().getTime();
//        int numIndexed = indexer.indexArtikal(artikalService.findAllByProdavac(prodavac));
//        long end = new Date().getTime();
//        String text = "Indexing " + numIndexed + " object took "
//                + (end - start) + " milliseconds";
//        return new ResponseEntity<>(text, HttpStatus.OK);
//    }
	
	
}
