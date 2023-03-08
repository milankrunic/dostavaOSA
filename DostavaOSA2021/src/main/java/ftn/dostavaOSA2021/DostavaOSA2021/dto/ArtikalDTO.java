package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtikalDTO {
	
	private Long idArtikla;
	private String naziv;
	private String opis;
	private Double cena;
	private String nazivFajla;	
	private String putanjaFajla;
	private Long idProdavac;
	private String prodavac;
	
	public ArtikalDTO(Artikal artikal) {
		this(artikal.getIdArtikal(), artikal.getNaziv(), artikal.getOpis(), artikal.getCena(),artikal.getNazivFajla(), artikal.getPutanjaFajla(),
				artikal.getProdavac().getKorisnik().getIdKorisnik(), artikal.getProdavac().getNazivProdavca());
	}

}
