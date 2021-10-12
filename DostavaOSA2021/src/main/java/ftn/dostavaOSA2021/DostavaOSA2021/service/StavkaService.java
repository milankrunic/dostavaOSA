package ftn.dostavaOSA2021.DostavaOSA2021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Stavka;
import ftn.dostavaOSA2021.DostavaOSA2021.repository.StavkaRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.StavkaServiceInterface;

@Service
public class StavkaService implements StavkaServiceInterface{

	@Autowired
	StavkaRepository stavkaRepository;
	
	@Override
	public List<Stavka> findAll() {
		return stavkaRepository.findAll();
	}

	@Override
	public Stavka findOne(Long id) {
		return stavkaRepository.getOne(id);
	}

	@Override
	public Stavka findById(Long stavkaId) {
		return stavkaRepository.findByIdStavka(stavkaId);
	}

	@Override
	public Stavka save(Stavka stavka) {
		return stavkaRepository.save(stavka);
	}

	@Override
	public void remove(Long id) {
		stavkaRepository.deleteById(id);
		
	}

}
