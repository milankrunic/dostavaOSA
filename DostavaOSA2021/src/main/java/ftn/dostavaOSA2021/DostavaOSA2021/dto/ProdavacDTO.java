package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import java.util.Date;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;
import ftn.dostavaOSA2021.DostavaOSA2021.model.TipKorisnika;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdavacDTO{
	
	private Long idProdavac;
	private String ime;
	private String prezime;
	private String korIme;
	private String lozinka;
	private boolean blokiran;
	private TipKorisnika tipKorisnika;
	private String adresa;	
	private String nazivProdavca;
	private String email;
	private Date poslujeOd;
	
	public ProdavacDTO(Prodavac p) {
		this(p.getKorisnik().getIdKorisnik(), p.getKorisnik().getIme(), p.getKorisnik().getPrezime(), p.getKorisnik().getKorisnickoIme(),
				p.getKorisnik().getLozinka(), p.getKorisnik().isBlokiran(), p.getKorisnik().getTipKorisnika(), p.getAdresa(), p.getNazivProdavca(),
				p.getEmail(), p.getPoslujeOd());
	}

}
