package ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface;

import java.util.List;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;

public interface ProdavacServiceInterface {

	public List<Prodavac> findAll();
	public Prodavac findOne(Long id);
	public Prodavac findByKorisnickoIme(String korIme);
	public Prodavac save(Prodavac prodavac);
	public void remove(Long id);
	
}
