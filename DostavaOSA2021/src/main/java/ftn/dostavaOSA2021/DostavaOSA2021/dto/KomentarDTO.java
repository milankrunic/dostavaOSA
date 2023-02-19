package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Komentar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KomentarDTO {
	
	private Long idKomentar;
	private String tekst;
	private Integer ocena;
	private boolean prihvacen;
	private boolean arhiviran;
	private String idArtikla;
	private String artikal;
	private String idKupac;
	private String kupac;

	public KomentarDTO(Komentar komentar) {
		this(komentar.getIdKomentar(), komentar.getTekst(), komentar.getOcena(), komentar.isPrihvacen(), komentar.isArhiviran(),
				komentar.getArtikal().getIdArtikal().toString(), komentar.getArtikal().getNaziv(), komentar.getKupac().getIdKupac().toString(),
				komentar.getKupac().getKorisnik().getKorisnickoIme());
	}

}
