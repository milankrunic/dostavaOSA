package ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto;

import org.springframework.web.multipart.MultipartFile;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.ArtikalES;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtikalEsDTO {

	private Long id;
	
    private String naziv;
    
    private String opis;
    
//    private String pdfFile;

    private Double cena;
    
    private MultipartFile[] file;
    
	public ArtikalEsDTO(Long id, String naziv, String opis, Double cena) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
//		this.pdfFile = pdfFile;
		this.cena = cena;
	}
    
    //mapper
    public ArtikalEsDTO(ArtikalES artikalEs) {
    	this(artikalEs.getIdArtikla(), artikalEs.getNaziv(), artikalEs.getOpis(), artikalEs.getCena());
    }
	
}
