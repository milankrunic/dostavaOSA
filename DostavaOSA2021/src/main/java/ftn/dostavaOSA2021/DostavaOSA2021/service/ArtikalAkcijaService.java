package ftn.dostavaOSA2021.DostavaOSA2021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.dostavaOSA2021.DostavaOSA2021.model.ArtikalAkcija;
import ftn.dostavaOSA2021.DostavaOSA2021.repository.ArtikalAkcijaRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ArtikalAkcijaServiceInterface;

@Service
public class ArtikalAkcijaService implements ArtikalAkcijaServiceInterface{

	@Autowired
	ArtikalAkcijaRepository artikalAkcijaRepository;
	
	@Override
	public List<ArtikalAkcija> findAll() {
		return artikalAkcijaRepository.findAll();
	}

	@Override
	public ArtikalAkcija findOne(Long id) {
		return artikalAkcijaRepository.getOne(id);
	}

	@Override
	public ArtikalAkcija findById(Long artikalAkcijaId) {
		return artikalAkcijaRepository.findByIdArtikalAkcija(artikalAkcijaId);
	}

	@Override
	public ArtikalAkcija save(ArtikalAkcija artikalAkcija) {
		return artikalAkcijaRepository.save(artikalAkcija);
	}

	@Override
	public void remove(Long id) {
		artikalAkcijaRepository.deleteById(id);
		
	}

}
