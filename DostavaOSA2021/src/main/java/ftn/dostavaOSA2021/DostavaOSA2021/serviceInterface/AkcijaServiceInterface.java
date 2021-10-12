package ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface;

import java.util.List;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Akcija;

public interface AkcijaServiceInterface {

	public List<Akcija> findAll();
	public Akcija findOne(Long id);
	public Akcija findById(Long akcijaId);
	public Akcija save(Akcija akcija);
	public void remove(Long id);
	
}
