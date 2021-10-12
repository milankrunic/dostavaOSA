package ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface;

import java.util.List;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Porudzbina;

public interface PorudzbinaServiceInterface {

	public List<Porudzbina> findAll();
	public Porudzbina findOne(Long id);
	public Porudzbina findById(Long porudzbinaId);
	public Porudzbina save(Porudzbina porudzbina);
	public void remove(Long id);

}
