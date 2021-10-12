package ftn.dostavaOSA2021.DostavaOSA2021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Administrator;
import ftn.dostavaOSA2021.DostavaOSA2021.repository.AdministratorRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.AdministratorServiceInterface;

@Service
public class AdministratorService implements AdministratorServiceInterface{

	@Autowired
	AdministratorRepository administratorRepository;
	
	@Override
	public List<Administrator> findAll() {
		return administratorRepository.findAll();
	}

	@Override
	public Administrator findOne(Long id) {
		return administratorRepository.getOne(id);
	}

	@Override
	public Administrator findById(Long administratorId) {
		return administratorRepository.findByIdAdministrator(administratorId);
	}

	@Override
	public Administrator save(Administrator administrator) {
		return administratorRepository.save(administrator);
	}

	@Override
	public void remove(Long id) {
		administratorRepository.deleteById(id);
		
	}

}
