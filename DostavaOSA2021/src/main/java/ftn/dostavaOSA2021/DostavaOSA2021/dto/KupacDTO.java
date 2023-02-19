package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Kupac;
import ftn.dostavaOSA2021.DostavaOSA2021.model.TipKorisnika;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KupacDTO {

	private Long idKupac;
	private String ime;
	private String prezime;
	private String korIme;
	private String lozinka;
	private boolean blokiran;
	private TipKorisnika tipKorisnika;
	private String adresaKupca;

	public KupacDTO(Kupac kupac) {
		this(kupac.getKorisnik().getIdKorisnik(), kupac.getKorisnik().getIme(), kupac.getKorisnik().getPrezime(), kupac.getKorisnik().getKorisnickoIme(),
				kupac.getKorisnik().getLozinka(), kupac.getKorisnik().isBlokiran(), kupac.getKorisnik().getTipKorisnika(), kupac.getAdresa());
	}

}
