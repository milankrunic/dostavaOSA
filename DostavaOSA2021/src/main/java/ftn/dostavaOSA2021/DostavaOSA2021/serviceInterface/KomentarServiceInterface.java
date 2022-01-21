package ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface;

import java.util.List;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Komentar;

public interface KomentarServiceInterface {

	public List<Komentar> findAll();
	public Komentar findOne(Long id);
	public Komentar findById(Long komentarId);
	public Komentar save(Komentar komentar);
	public void remove(Long id);
	public List<Komentar> findAllByArtikal(Artikal artikal);
	
}
