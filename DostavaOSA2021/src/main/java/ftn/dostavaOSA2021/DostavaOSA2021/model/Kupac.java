package ftn.dostavaOSA2021.DostavaOSA2021.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Kupac extends Korisnik{

	
	@Column(name = "adresa", nullable = true)
	private String adresa;
	
	@OneToMany(cascade = {ALL}, fetch=LAZY, mappedBy="kupac")
	private List<Stavka> stavke = new ArrayList<Stavka>();
	
	@OneToMany(cascade = {ALL}, fetch=LAZY, mappedBy="kupac")
	private List<Komentar> komentari = new ArrayList<Komentar>();
	
	public Kupac() {
		super();
	}


	public Kupac(Long idKorisnik, String ime, String prezime, String korisnickoIme, String lozinka, boolean blokiran,
			TipKorisnika tipKorisnika, String adresa, List<Stavka> stavke) {
		super(idKorisnik, ime, prezime, korisnickoIme, lozinka, blokiran, tipKorisnika);
		this.adresa = adresa;
		this.stavke = stavke;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public List<Stavka> getStavke() {
		return stavke;
	}


	public void setStavke(List<Stavka> stavke) {
		this.stavke = stavke;
	}

}
