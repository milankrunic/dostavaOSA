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

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto.ArtikalEsDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto.SimpleQueryES;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.mapper.ArtikalMapper;
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
	
//	iz nekaog razloga ne radi @Autowired pa mora ovako
	
	private final ArtikalEsRepository artikalEsRepository;
	private final ElasticsearchRestTemplate elasticsearchRestTemplate;
	
    public ArtikalEsService(ArtikalEsRepository artikalEsRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
    	this.artikalEsRepository = artikalEsRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }
	
	@Override
	public void index(ArtikalES artikalEs) {
		artikalEsRepository.save(artikalEs);
		
	}
	
	@Override
    public List<ArtikalES> getArtikalByNaziv(String naziv) {
        return artikalEsRepository.findAllByNaziv(naziv);
    }
	
	@Override
	public List<ArtikalES> getArtikalByOpis(String opis) {
		return artikalEsRepository.findAllByOpis(opis);
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
	
//	@Override
//	public void indexUploadFile(ArtikalES artikalES) throws IOException {
//		for (MultipartFile file : artikalES.getFile()) {
//			if(file.isEmpty()) {
//				continue;
//			}
//			
//			String fileName = saveUploadedFileInFolder(file);
//			if(fileName != null) {
//				ArtikalES artikalEsIndexUnit = getHandler(fileName).getIndexUnit(new File(fileName));
////				artikalEsIndexUnit.setIdArtikla(artikalES.getIdArtikla());
////				artikalEsIndexUnit.setNaziv(artikalES.getNaziv());
////				artikalEsIndexUnit.setCena(artikalES.getCena());
////				artikalEsIndexUnit.setOpis(artikalEsDTO.getOpis());
//
//				index(artikalEsIndexUnit);
//			}
//		}	
//	}

	@Override
	public void indexUploadFile(ArtikalEsDTO artikalEsDTO) throws IOException {
		for (MultipartFile file : artikalEsDTO.getFile()) {
			if(file.isEmpty()) {
				continue;
			}
			
			String fileName = saveUploadedFileInFolder(file);
			if(fileName != null) {
				ArtikalES artikalEsIndexUnit = getHandler(fileName).getIndexUnit(new File(fileName));
				artikalEsIndexUnit.setNaziv(artikalEsDTO.getNaziv());
				artikalEsIndexUnit.setCena(artikalEsDTO.getCena());
				artikalEsIndexUnit.setIdArtikla(artikalEsDTO.getId());
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

	@Override
	public List<ArtikalEsDTO> findByNaziv(String naziv) {
		QueryBuilder nazivQuery = SearchQueryGenerator.createMatchQueryBuilder(new SimpleQueryES("naziv", naziv));
		
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
				.must(nazivQuery);
		
		return ArtikalMapper.mapDtos(searchByBoolQuery(boolQuery));
	}
	
	@Override
	public List<ArtikalEsDTO> findByOpis(String opis) {
		QueryBuilder opisQuery = SearchQueryGenerator.createMatchQueryBuilder(new SimpleQueryES("opis", opis));
		
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
				.must(opisQuery);
		
		return ArtikalMapper.mapDtos(searchByBoolQuery(boolQuery));
	}

	@Override
	public List<ArtikalEsDTO> findByCena(double from, double to) {
		String range = from + "-" + to;
		
		QueryBuilder priceQuery = SearchQueryGenerator.createRangeQueryBuilder(new SimpleQueryES("cena", range));
		
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
				.must(priceQuery);
		
		return ArtikalMapper.mapDtos(searchByBoolQuery(boolQuery));
	}
	
    private SearchHits<ArtikalES> searchByBoolQuery(BoolQueryBuilder boolQuery) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .build();

        return elasticsearchRestTemplate.search(searchQuery, ArtikalES.class,  IndexCoordinates.of("artikli"));
    }

	@Override
	public List<ArtikalEsDTO> findByNazivAndCena(String naziv, double from, double to) {
		
		String cena = from + "-" + to;
		QueryBuilder nazivQuery = SearchQueryGenerator.createMatchQueryBuilder(new SimpleQueryES("naziv", naziv));
		QueryBuilder cenaQuery = SearchQueryGenerator.createRangeQueryBuilder(new SimpleQueryES("cena", cena));
		
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
				.must(nazivQuery)
				.must(cenaQuery);
		
		return ArtikalMapper.mapDtos(searchByBoolQuery(boolQuery));
		
	}

	@Override
	public List<ArtikalEsDTO> findByNazivOrCena(String naziv, double from, double to) {

		String cena = from + "-" + to;
		QueryBuilder nazivQuery = SearchQueryGenerator.createMatchQueryBuilder(new SimpleQueryES("naziv", naziv));
		QueryBuilder cenaQuery = SearchQueryGenerator.createRangeQueryBuilder(new SimpleQueryES("cena", cena));
		
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
				.should(nazivQuery)
				.should(cenaQuery);
		
		return ArtikalMapper.mapDtos(searchByBoolQuery(boolQuery));
		
	}

	@Override
	public ArtikalES findOne(Long id) {
		return artikalEsRepository.findByIdArtikla(id);
	}

	@Override
	public ArtikalES getOneByNaziv(String naziv) {
		return artikalEsRepository.findByNaziv(naziv);
	}

	@Override
	public void removeArtikalES(ArtikalES artikalES) {
		artikalEsRepository.delete(artikalES);		
	}

}
