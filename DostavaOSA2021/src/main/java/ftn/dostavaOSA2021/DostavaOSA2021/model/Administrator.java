package ftn.dostavaOSA2021.DostavaOSA2021.model;

import javax.persistence.Entity;

@Entity
public class Administrator extends Korisnik{
	
	public Administrator() {
		super();
	}
	
	public Administrator(Long idKorisnik, String ime, String prezime, String korisnickoIme, String lozinka, boolean blokiran, TipKorisnika tipKorisnika) {
		super(idKorisnik, ime, prezime, korisnickoIme, lozinka, blokiran, tipKorisnika);
	}
	
	
}
