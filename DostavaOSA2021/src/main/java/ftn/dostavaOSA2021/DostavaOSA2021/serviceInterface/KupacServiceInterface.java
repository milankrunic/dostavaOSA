package ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface;

import java.util.List;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Kupac;

public interface KupacServiceInterface {
	
	public List<Kupac> findAll();
	public Kupac findOne(Long id);
	public Kupac findById(Long kupacId);
	public Kupac save(Kupac kupac);
	public void remove(Long id);

}
