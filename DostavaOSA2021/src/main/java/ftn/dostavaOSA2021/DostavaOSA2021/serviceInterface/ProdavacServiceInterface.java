package ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface;

import java.util.List;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;

public interface ProdavacServiceInterface {

	public List<Prodavac> findAll();
	public Prodavac findOne(Long id);
	public Prodavac findById(Long korisnikId);
	public Prodavac findByKorImeAndLozinka(String korIme, String loz);
	public Prodavac save(Prodavac prodavac);
	public void remove(Long id);
	
}
