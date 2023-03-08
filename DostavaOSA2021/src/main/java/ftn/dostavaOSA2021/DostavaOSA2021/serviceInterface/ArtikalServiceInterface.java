package ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface;

import java.util.List;

import org.springframework.core.io.Resource;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;

public interface ArtikalServiceInterface {

	public List<Artikal> findAll();
	public Artikal findOne(Long id);
	public Artikal findById(Long artikalId);
	public Artikal save(Artikal artikal);
	public void remove(Long id);
	public List<Artikal> findAllByProdavac(Prodavac prodavac);
	public Resource loadFileAsResource(String fileName) throws Exception;
	
}
