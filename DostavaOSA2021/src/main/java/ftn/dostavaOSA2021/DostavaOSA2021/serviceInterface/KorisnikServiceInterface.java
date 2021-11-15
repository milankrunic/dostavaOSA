package ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface;

import java.util.List;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Korisnik;

public interface KorisnikServiceInterface {

	public List<Korisnik> findAll();
	public Korisnik findOne(Long id);
	public Korisnik findById(Long korisnikId);
	public Korisnik findByKorImeAndLozinka(String korIme, String loz);
	public Korisnik save(Korisnik korisnik);
	public void remove(Long id);
	
}
