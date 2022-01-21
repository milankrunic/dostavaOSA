package ftn.dostavaOSA2021.DostavaOSA2021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Komentar;
import ftn.dostavaOSA2021.DostavaOSA2021.repository.KomentarRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KomentarServiceInterface;

@Service
public class KomentarService implements KomentarServiceInterface{

	@Autowired
	KomentarRepository komentarRepository;
	
	@Override
	public List<Komentar> findAll() {
		return komentarRepository.findAll();
	}

	@Override
	public Komentar findOne(Long id) {
		return komentarRepository.getOne(id);
	}

	@Override
	public Komentar findById(Long komentarId) {
		return komentarRepository.findByIdKomentar(komentarId);
	}

	@Override
	public Komentar save(Komentar komentar) {
		return komentarRepository.save(komentar);
	}

	@Override
	public void remove(Long id) {
		komentarRepository.deleteById(id);
		
	}

	@Override
	public List<Komentar> findAllByArtikal(Artikal artikal) {
		return komentarRepository.findByArtikal(artikal);
	}

}
