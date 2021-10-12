package ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface;

import java.util.List;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;

public interface ArtikalServiceInterface {

	public List<Artikal> findAll();
	public Artikal findOne(Long id);
	public Artikal findById(Long artikalId);
	public Artikal save(Artikal artikal);
	public void remove(Long id);
	
}
