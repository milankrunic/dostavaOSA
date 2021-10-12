package ftn.dostavaOSA2021.DostavaOSA2021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Porudzbina;
import ftn.dostavaOSA2021.DostavaOSA2021.repository.PorudzbinaRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.PorudzbinaServiceInterface;

@Service
public class PorudzbinaService implements PorudzbinaServiceInterface{

	@Autowired
	PorudzbinaRepository porudzbinaRepository;
	
	@Override
	public List<Porudzbina> findAll() {
		return porudzbinaRepository.findAll();
	}

	@Override
	public Porudzbina findOne(Long id) {
		return porudzbinaRepository.getOne(id);
	}

	@Override
	public Porudzbina findById(Long porudzbinaId) {
		return porudzbinaRepository.findByIdPorudzbina(porudzbinaId);
	}

	@Override
	public Porudzbina save(Porudzbina porudzbina) {
		return porudzbinaRepository.save(porudzbina);
	}

	@Override
	public void remove(Long id) {
		porudzbinaRepository.deleteById(id);
		
	}

}
