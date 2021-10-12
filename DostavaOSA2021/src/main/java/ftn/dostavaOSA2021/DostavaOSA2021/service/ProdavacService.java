package ftn.dostavaOSA2021.DostavaOSA2021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;
import ftn.dostavaOSA2021.DostavaOSA2021.repository.ProdavacRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ProdavacServiceInterface;

@Service
public class ProdavacService implements ProdavacServiceInterface{

	@Autowired
	ProdavacRepository prodavacRepository;
	
	@Override
	public List<Prodavac> findAll() {
		return prodavacRepository.findAll();
	}

	@Override
	public Prodavac findOne(Long id) {
		return prodavacRepository.getOne(id);
	}

	@Override
	public Prodavac findById(Long prodavacId) {
		return prodavacRepository.findByIdProdavac(prodavacId);
	}

	@Override
	public Prodavac save(Prodavac prodavac) {
		return prodavacRepository.save(prodavac);
	}

	@Override
	public void remove(Long id) {
		prodavacRepository.deleteById(id);
		
	}

}
