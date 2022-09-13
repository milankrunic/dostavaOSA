package ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto;

import org.springframework.web.multipart.MultipartFile;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.ArtikalES;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtikalEsDTO {

    private String naziv;

    private Double cena;
    
    private MultipartFile[] opisFile;
    
	public ArtikalEsDTO(String naziv, Double cena) {
		super();
		this.naziv = naziv;
		this.cena = cena;
	}
    
    //mapper
    public ArtikalEsDTO(ArtikalES artikalEs) {
    	this(artikalEs.getNaziv(), artikalEs.getCena());
    }
	
}
