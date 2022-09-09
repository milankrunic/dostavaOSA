package ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto.ArtikalEsDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.mapper.ArtikalEsMapper;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.ArtikalES;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.repository.ArtikalEsRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceInterface.ArtikalEsServiceInterface;

@Service
public class ArtikalEsService implements ArtikalEsServiceInterface{

	@Autowired
	ArtikalEsRepository artikalEsRepository;
	
	@Override
	public void index(ArtikalEsDTO artikalEsDTO) {
		artikalEsRepository.save(ArtikalEsMapper.mapModel(artikalEsDTO));
		
	}
	
	@Override
    public List<ArtikalES> getArtikalByNaziv(String naziv) {
        return artikalEsRepository.findAllByNaziv(naziv);
    }

}
