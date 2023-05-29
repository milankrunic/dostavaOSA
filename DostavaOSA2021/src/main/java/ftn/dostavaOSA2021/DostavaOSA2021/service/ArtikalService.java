package ftn.dostavaOSA2021.DostavaOSA2021.service;

import java.util.List;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import ftn.dostavaOSA2021.DostavaOSA2021.dto.ArtikalDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.ArtikalES;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceInterface.ArtikalEsServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Korisnik;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;
import ftn.dostavaOSA2021.DostavaOSA2021.repository.ArtikalRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ArtikalServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.KorisnikServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ProdavacServiceInterface;

@Service
public class ArtikalService implements ArtikalServiceInterface{

	@Value("${files.path}")
	private String dataFilesPath;
	
	@Value("${app.upload.dir:${user.home}}")
	public String uploadDir;
	
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/files";
	private final Path fileStorageLocation= Paths.get(uploadDirectory)
			.toAbsolutePath().normalize();
	
	@Autowired 
	ArtikalRepository artikalRepository;
	
	@Autowired
	ProdavacServiceInterface prodavacServiceInterface;
	
	@Autowired
	KorisnikServiceInterface korisnikServiceInterface;
	
	@Autowired
	ArtikalEsServiceInterface artikalEsServiceInterface;
	
	@Override
	public List<Artikal> findAll() {
		return artikalRepository.findAll();
	}

	@Override
	public Artikal findOne(Long id) {
		return artikalRepository.getOne(id);
	}

	@Override
	public Artikal findById(Long artikalId) {
		return artikalRepository.findByIdArtikal(artikalId);
	}

	@Override
	public ArtikalDTO save(ArtikalDTO artikalDTO, MultipartFile file) throws Exception {

		String fileName = file.getOriginalFilename();
		String filePath = Paths.get(uploadDirectory, fileName).toString();

		// Cuvanje fajla lokalno
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(filePath));
		stream.write(file.getBytes());
		stream.close();
		
		Korisnik korisnik = korisnikServiceInterface.findOne(artikalDTO.getIdProdavac());
		Prodavac prodavac = prodavacServiceInterface.findByKorisnickoIme(korisnik.getKorisnickoIme());
		
		Artikal a = new Artikal();
		a.setNaziv(artikalDTO.getNaziv());
		a.setOpis(artikalDTO.getOpis());
		a.setCena(artikalDTO.getCena());
		a.setNazivFajla(fileName);
		a.setPutanjaFajla(filePath);
		a.setProdavac(prodavac);

		a = artikalRepository.save(a);

		return new ArtikalDTO(a);
	}

	@Override
	public List<Artikal> findAllByProdavac(Prodavac prodavac) {
		return artikalRepository.findByProdavac(prodavac);
	}

	@Override
	public Resource loadFileAsResource(String fileName) throws Exception {
		try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            System.out.println(filePath+" "+resource);
            if(resource.exists()) {
                return resource;
            } else {
                throw new Exception("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new Exception("File not found " + fileName, ex);
        }
	}

	@Override
	public void remove(Long id) {
		Artikal artikal = findOne(id);
		String file = artikal.getNazivFajla();
		try {
			if (id != 0 && file != null) {
				artikalRepository.deleteById(id);
				String path = uploadDirectory+"/"+file;
				File fileToDelete = new File(path);
				fileToDelete.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@Override
	public ArtikalDTO update(Long id, ArtikalDTO artikalDTO) {
		
		Artikal artikal = artikalRepository.findByIdArtikal(id);
		Korisnik korisnik = korisnikServiceInterface.findOne(artikalDTO.getIdProdavac());
		Prodavac prodavac = prodavacServiceInterface.findByKorisnickoIme(korisnik.getKorisnickoIme());
		
		if(artikal == null) {
			return null;
		}
		
		ArtikalES artikalES = artikalEsServiceInterface.getOneByNaziv(artikal.getNaziv());
		artikal.setNaziv(artikalDTO.getNaziv());
		artikal.setOpis(artikalDTO.getOpis());
		artikal.setCena(artikalDTO.getCena());
		artikal.setProdavac(prodavac);
		artikalES.setNaziv(artikalDTO.getNaziv());
		artikalES.setOpis(artikalDTO.getOpis());
		artikalES.setCena(artikalDTO.getCena());

		artikal = artikalRepository.save(artikal);
		artikalEsServiceInterface.index(artikalES);
		return new ArtikalDTO(artikal);
		
	}

}
