package ftn.dostavaOSA2021.DostavaOSA2021.dto;


import ftn.dostavaOSA2021.DostavaOSA2021.model.Korisnik;
import ftn.dostavaOSA2021.DostavaOSA2021.model.TipKorisnika;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KorisnikDTO {
	
	private Long idKorisnik;
	private String ime;
	private String prezime;
	private String korIme;
	private String lozinka;
	private boolean blokiran;
	private TipKorisnika tipKorisnika;

	public KorisnikDTO(Korisnik korisnik) {
		this(korisnik.getIdKorisnik(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getKorisnickoIme(), korisnik.getLozinka(), korisnik.isBlokiran(),
			korisnik.getTipKorisnika());
	}

}
