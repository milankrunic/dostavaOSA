package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Administrator;
import ftn.dostavaOSA2021.DostavaOSA2021.model.TipKorisnika;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdministratorDTO {
	
	private Long idAdmin;
	private String ime;
	private String prezime;
	private String korIme;
	private String lozinka;
	private boolean blokiran;
	private TipKorisnika tipKorisnika;
	
	public AdministratorDTO(Administrator a) {
		this(a.getKorisnik().getIdKorisnik(), a.getKorisnik().getIme(), a.getKorisnik().getPrezime(), a.getKorisnik().getKorisnickoIme(),
				a.getKorisnik().getLozinka(), a.getKorisnik().isBlokiran(), a.getKorisnik().getTipKorisnika());
	}

}