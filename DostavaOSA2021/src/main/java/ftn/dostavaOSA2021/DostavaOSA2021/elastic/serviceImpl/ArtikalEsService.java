package ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto.ArtikalEsDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.ArtikalES;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.repository.ArtikalEsRepository;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceInterface.ArtikalEsServiceInterface;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.handlers.DocumentHandler;
import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.handlers.PDFHandler;

@Service
public class ArtikalEsService implements ArtikalEsServiceInterface{

	//putanja iz application.properties
	@Value("${files.path}")
	private String dataFilesPath;
	
	@Value("${app.upload.dir:${user.home}}")
	public String uploadDir;
	
	@Autowired
	ArtikalEsRepository artikalEsRepository;
	
	@Override
	public void index(ArtikalES artikalEs) {
		artikalEsRepository.save(artikalEs);
		
	}
	
	@Override
    public List<ArtikalES> getArtikalByNaziv(String naziv) {
        return artikalEsRepository.findAllByNaziv(naziv);
    }

	@Override
	public void reindex() {
		File resourseFile = getResourseFilePath(dataFilesPath);
		indexUnitFromFile(resourseFile);
		
	}
	
	//tri metode ispod su vezane za reindex
	
	private File getResourseFilePath(String path) {
		URL url = this.getClass().getClassLoader().getResource(path);
		File file = null;
		
		try {
			if(url != null) {
				file = new File(url.toURI());
			}
		} catch (URISyntaxException e) {
			file = new File(url.getPath());
		}

		return file;
	}
	
	private int indexUnitFromFile(File file) {
		DocumentHandler handler;
		String fileName;		
		int retVal = 0;
		try {
			File[] files;
			if(file.isDirectory()) {
				files = file.listFiles();
			}else {
				files = new File[1];
				files[0] = file;
			}
			assert files != null;
			for (File newFile : files) {
				if(newFile.isFile()) {
					fileName = newFile.getName();
					handler = getHandler(fileName);
					if(handler == null) {
						System.out.println("Nije moguce indeksirati dokument sa nazivom "+fileName);
						continue;
					}
					index(handler.getIndexUnit(newFile));
					retVal++;
				}else if(newFile.isDirectory()) {
					retVal += indexUnitFromFile(newFile);
				}
			}
			System.out.println("Indexing done");
		} catch (Exception e) {
			System.out.println("Indexing NOT done");
		}
		return retVal;
		
	}
	
	private DocumentHandler getHandler(String fileName) {
		if(fileName.endsWith(".pdf")) {
			return new PDFHandler();
		}else {
			return new PDFHandler();
		}
		
	}

	@Override
	public void indexUploadFile(ArtikalEsDTO artikalEsDTO) throws IOException {
		for (MultipartFile file : artikalEsDTO.getOpisFile()) {
			if(file.isEmpty()) {
				continue;
			}
			
			String fileName = saveUploadedFileInFolder(file);
			if(fileName != null) {
				ArtikalES artikalEsIndexUnit = getHandler(fileName).getIndexUnit(new File(fileName));
				artikalEsIndexUnit.setNaziv(artikalEsDTO.getNaziv());
				artikalEsIndexUnit.setCena(artikalEsDTO.getCena());
//				artikalEsIndexUnit.setOpis(artikalEsDTO.getOpisFile());
				index(artikalEsIndexUnit);
			}
		}	
	}
	
	private String saveUploadedFileInFolder(MultipartFile file) throws IOException{
		String retVal = null;
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(getResourseFilePath(dataFilesPath).getAbsolutePath() + File.separator + file.getOriginalFilename());
			Files.write(path, bytes);
			Path filepath = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
			Files.write(filepath, bytes);
			retVal = path.toString();
		}
		return retVal;
	}

}
