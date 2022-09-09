package ftn.dostavaOSA2021.DostavaOSA2021.elastic.mapper;

import ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto.ArtikalEsDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto.ArtikalResponseDTO;
import ftn.dostavaOSA2021.DostavaOSA2021.elastic.model.ArtikalES;

public class ArtikalEsMapper {
	
	public static ArtikalES mapModel(ArtikalEsDTO artikalEsDTO) {
		return ArtikalES.builder()
				.naziv(artikalEsDTO.getNaziv())
				.opis(artikalEsDTO.getOpis())
				.cena(artikalEsDTO.getCena())
				.build();
	}
	
	public static ArtikalResponseDTO mapResponseDTO(ArtikalES artikalEs) {
		return ArtikalResponseDTO.builder()
				.id(artikalEs.getId())
				.naziv(artikalEs.getNaziv())
				.opis(artikalEs.getOpis())
				.cena(artikalEs.getCena())
				.build();			
	}

}
