package ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface;

import java.util.List;

import ftn.dostavaOSA2021.DostavaOSA2021.model.ArtikalAkcija;

public interface ArtikalAkcijaServiceInterface {

	public List<ArtikalAkcija> findAll();
	public ArtikalAkcija findOne(Long id);
	public ArtikalAkcija findById(Long artikalAkcijaId);
	public ArtikalAkcija save(ArtikalAkcija artikalAkcija);
	public void remove(Long id);
	
}
