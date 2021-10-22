package ftn.dostavaOSA2021.DostavaOSA2021.model;

import javax.persistence.Entity;

@Entity
public class Administrator extends Korisnik{
	
	public Administrator() {
		super();
	}
	
	public Administrator(Long id, String ime, String prezime, String korisnickoIme, String lozinka, boolean blokiran, TipKorisnika tipKorisnika) {
		super(id, ime, prezime, korisnickoIme, lozinka, blokiran, tipKorisnika);
	}
	
	
}
