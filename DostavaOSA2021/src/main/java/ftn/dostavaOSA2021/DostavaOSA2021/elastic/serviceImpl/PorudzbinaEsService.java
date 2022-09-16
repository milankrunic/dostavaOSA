package ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.PorudzbinaES;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.repository.PorudzbinaEsRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceInterface.PorudzbinaEsServiceInterface;

@Service
public class PorudzbinaEsService implements PorudzbinaEsServiceInterface{

	@Autowired
	PorudzbinaEsRepository porudzbinaEsRepository;
	
	@Override
	public void index(PorudzbinaES porudzbinaES) {
		porudzbinaEsRepository.save(porudzbinaES);
	}

	@Override
	public List<PorudzbinaES> getPorudzbinaByKomentar(String komentar) {
		return porudzbinaEsRepository.findAllByKomentar(komentar);
	}

}
