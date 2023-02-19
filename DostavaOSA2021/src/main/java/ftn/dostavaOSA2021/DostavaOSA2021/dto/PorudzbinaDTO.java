package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import java.util.Date;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Porudzbina;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PorudzbinaDTO {
	
	private Long idPorudzbina;
	private Date satnica;
	private int ocena;
	private String komentar;
	private double cena;
	private boolean dostavljeno;
	private boolean anonimanKomentar;
	private boolean arhiviranKomentar;
	private Long idKupac;
	private String kupac;

	public PorudzbinaDTO(Porudzbina p) {
		this(p.getIdPorudzbina(), p.getSatnica(), p.getOcena(), p.getKomentar(), p.getCena(), p.isDostavljeno(), p.isAnonimanKomentar(),
				p.isArhiviranKomentar(), p.getKupac().getIdKupac(), p.getKupac().getKorisnik().getKorisnickoIme());
	}

}
