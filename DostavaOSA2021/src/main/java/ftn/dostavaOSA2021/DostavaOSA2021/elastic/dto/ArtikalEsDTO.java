package ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto;

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

    private String opis;

    private Double cena;
    
    //mapper
    public ArtikalEsDTO(ArtikalES artikalEs) {
    	this(artikalEs.getNaziv(), artikalEs.getOpis(), artikalEs.getCena());
    }
	
}
