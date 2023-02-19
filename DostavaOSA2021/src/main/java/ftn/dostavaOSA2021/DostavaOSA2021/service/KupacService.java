package ftn.dostavaOSA2021.DostavaOSA2021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Kupac;
import ftn.dostavaOSA2021.DostavaOSA2021.repository.KupacRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KupacServiceInterface;

@Service
public class KupacService implements KupacServiceInterface{

	@Autowired
	KupacRepository kupacRepository;
	
	@Override
	public List<Kupac> findAll() {
		return kupacRepository.findAll();
	}

	@Override
	public Kupac findOne(Long id) {
		return kupacRepository.getOne(id);
	}

	@Override
	public Kupac save(Kupac kupac) {
		return kupacRepository.save(kupac);
	}

	@Override
	public void remove(Long id) {
		kupacRepository.deleteById(id);		
	}

	@Override
	public Kupac findByKorisnickoIme(String korIme) {
		return kupacRepository.findByKorisnik_korisnickoIme(korIme);
	}

}
