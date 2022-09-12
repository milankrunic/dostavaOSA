package ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.ArtikalES;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.repository.ArtikalEsRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceInterface.ArtikalEsServiceInterface;

@Service
public class ArtikalEsService implements ArtikalEsServiceInterface{

	@Autowired
	ArtikalEsRepository artikalEsRepository;
	
	@Override
	public void index(ArtikalES artikalEs) {
		artikalEsRepository.save(artikalEs);
		
	}
	
	@Override
    public List<ArtikalES> getArtikalByNaziv(String naziv) {
        return artikalEsRepository.findAllByNaziv(naziv);
    }

}
