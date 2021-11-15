package ftn.dostavaOSA2021.DostavaOSA2021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Korisnik;
import ftn.dostavaOSA2021.DostavaOSA2021.repository.KorisnikRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KorisnikServiceInterface;

@Service
public class KorisnikService implements KorisnikServiceInterface{

	@Autowired
	KorisnikRepository korisnikRepository;

	@Override
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}

	@Override
	public Korisnik findOne(Long id) {
		return korisnikRepository.getOne(id);
	}

	@Override
	public Korisnik findByKorImeAndLozinka(String korIme, String loz) {
		return korisnikRepository.findByKorisnickoImeAndLozinka(korIme, loz);
	}

	@Override
	public Korisnik save(Korisnik korisnik) {
		return korisnikRepository.save(korisnik);
	}

	@Override
	public void remove(Long id) {
		korisnikRepository.deleteById(id);
		
	}

	@Override
	public Korisnik findById(Long korisnikId) {
		return korisnikRepository.findByIdKorisnik(korisnikId);
	}
	
}
