package ftn.dostavaOSA2021.DostavaOSA2021.elastic.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.ArtikalES;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.repository.ArtikalEsRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceImpl.ArtikalEsService;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;
import ftn.dostavaOSA2021.DostavaOSA2021.repository.ArtikalRepository;

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

    @PostConstruct
    @Transactional
    public void loadAll(){

        artikalEsRepository.deleteAll();

        List<ArtikalES> artikli = new ArrayList<>();
        for(Artikal artikal: artikalRepository.findAll()){
            artikli.add(new ArtikalES(artikal));
        }

        artikalEsRepository.saveAll(artikli);

    }
}