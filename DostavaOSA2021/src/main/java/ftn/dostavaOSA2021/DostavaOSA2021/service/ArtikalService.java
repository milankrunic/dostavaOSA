package ftn.dostavaOSA2021.DostavaOSA2021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;
import ftn.dostavaOSA2021.DostavaOSA2021.repository.ArtikalRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ArtikalServiceInterface;

@Service
public class ArtikalService implements ArtikalServiceInterface{

	@Autowired 
	ArtikalRepository artikalRepository;
	
	@Override
	public List<Artikal> findAll() {
		return artikalRepository.findAll();
	}

	@Override
	public Artikal findOne(Long id) {
		return artikalRepository.getOne(id);
	}

	@Override
	public Artikal findById(Long artikalId) {
		return artikalRepository.findByIdArtikal(artikalId);
	}

	@Override
	public Artikal save(Artikal artikal) {
		return artikalRepository.save(artikal);
	}

	@Override
	public void remove(Long id) {
		artikalRepository.deleteById(id);
		
	}

	@Override
	public List<Artikal> findAllByProdavac(Prodavac prodavac) {
		return artikalRepository.findByProdavac(prodavac);
	}

}
