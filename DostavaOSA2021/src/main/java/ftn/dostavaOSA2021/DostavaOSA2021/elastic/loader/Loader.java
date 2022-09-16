package ftn.dostavaOSA2021.DostavaOSA2021.elastic.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.ArtikalES;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.PorudzbinaES;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.repository.ArtikalEsRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.repository.PorudzbinaEsRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceImpl.ArtikalEsService;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Porudzbina;
import ftn.dostavaOSA2021.DostavaOSA2021.repository.ArtikalRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.repository.PorudzbinaRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class Loader {

    /*Komponenta služi da prilikom svakog pokretanja podatke iz sql ubacuje u elastic search bazu*/

    @Autowired
    ArtikalRepository artikalRepository;

    @Autowired
    ArtikalEsRepository artikalEsRepository;

    @Autowired
    ArtikalEsService artikalESService;
    
    @Autowired
    PorudzbinaRepository porudzbinaRepository;
    
    @Autowired
    PorudzbinaEsRepository porudzbinaEsRepository;

	@PostConstruct
    @Transactional
    public void loadAll(){
		
        List<ArtikalES> artikliES = new ArrayList<>();
        for(Artikal artikal: artikalRepository.findAll()){
        	
        	//ovaj if da se ne bi stalno duplirale vrednosti u elasticsearchu
        	if(artikal.getNaziv() != null) {
        		continue;
        	}else {
        		artikliES.add(new ArtikalES(artikal));
        	}        	
        }
        artikalEsRepository.saveAll(artikliES);
        
        List<PorudzbinaES> porudzbinaES = new ArrayList<>();
        for(Porudzbina porudzbina: porudzbinaRepository.findAll()){
        	
        	//ovaj if da se ne bi stalno duplirale vrednosti u elasticsearchu
        	if(porudzbina.getKomentar() != null) {
        		continue;
        	}else {
        		porudzbinaES.add(new PorudzbinaES(porudzbina));
        	} 	
        }
        porudzbinaEsRepository.saveAll(porudzbinaES);

    }
	
//	@PostConstruct
//    @Transactional
//    public void loadAll(){
//
//		artikalEsRepository.deleteAll();
//		porudzbinaEsRepository.deleteAll();
//		
//        List<ArtikalES> artikli = new ArrayList<>();
//        for(Artikal artikal: artikalRepository.findAll()){
//        	
//        	artikli.add(new ArtikalES(artikal));
//        	       	
//        }
//        artikalEsRepository.saveAll(artikli);
//        
//        List<PorudzbinaES> porudzbinaES = new ArrayList<>();
//        for(Porudzbina porudzbina: porudzbinaRepository.findAll()){
//            porudzbinaES.add(new PorudzbinaES(porudzbina));
//        }
//
//        porudzbinaEsRepository.saveAll(porudzbinaES);
//
//    }
}