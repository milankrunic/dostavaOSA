package ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface;

import java.util.List;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Administrator;

public interface AdministratorServiceInterface {

	public List<Administrator> findAll();
	public Administrator findOne(Long id);
	public Administrator findById(Long administratorId);
	public Administrator save(Administrator administrator);
	public void remove(Long id);
	
}
