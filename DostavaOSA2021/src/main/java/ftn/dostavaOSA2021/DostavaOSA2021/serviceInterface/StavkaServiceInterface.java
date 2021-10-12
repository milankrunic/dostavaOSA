package ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface;

import java.util.List;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Stavka;

public interface StavkaServiceInterface {

	public List<Stavka> findAll();
	public Stavka findOne(Long id);
	public Stavka findById(Long stavkaId);
	public Stavka save(Stavka stavka);
	public void remove(Long id);
	
}
