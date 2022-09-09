package ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtikalResponseDTO {
	
    private String id;

    private String naziv;

    private String opis;

    private Double cena;

}
