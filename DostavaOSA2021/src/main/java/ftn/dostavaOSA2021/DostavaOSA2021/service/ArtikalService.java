package ftn.dostavaOSA2021.DostavaOSA2021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;
import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;
import ftn.dostavaOSA2021.DostavaOSA2021.repository.ArtikalRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.serviceInterface.ArtikalServiceInterface;

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
	public Artikal save(Artikal artikal) {
		return artikalRepository.save(artikal);
	}

	@Override
	public void remove(Long id) {
		artikalRepository.deleteById(id);
		
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

}
