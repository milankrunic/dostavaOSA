package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Kupac;
import ftn.dostavaOSA2021.DostavaOSA2021.model.TipKorisnika;

public class KupacDTO {

	private Long idKupac;
	private String adresa;
	private String ime;
	private String prezime;
	private String korIme;
	private String lozinka;
	private boolean blokiran;
	private TipKorisnika tipKorisnika;
	
	public KupacDTO() {
		super();
	}

	public KupacDTO(Long idKupac, String adresa, String ime, String prezime, String korIme, String lozinka,
			boolean blokiran, TipKorisnika tipKorisnika) {
		super();
		this.idKupac = idKupac;
		this.adresa = adresa;
		this.ime = ime;
		this.prezime = prezime;
		this.korIme = korIme;
		this.lozinka = lozinka;
		this.blokiran = blokiran;
		this.tipKorisnika = tipKorisnika;
	}
	
	public KupacDTO(Kupac kupac) {
		this(kupac.getIdKorisnik(), kupac.getAdresa(), kupac.getIme(), kupac.getPrezime(), kupac.getKorisnickoIme(), kupac.getLozinka(), kupac.isBlokiran(), kupac.getTipKorisnika());
	}

	public Long getIdKupac() {
		return idKupac;
	}

	public void setIdKupac(Long idKupac) {
		this.idKupac = idKupac;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getKorIme() {
		return korIme;
	}

	public void setKorIme(String korIme) {
		this.korIme = korIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public boolean isBlokiran() {
		return blokiran;
	}

	public void setBlokiran(boolean blokiran) {
		this.blokiran = blokiran;
	}

	public TipKorisnika getTipKorisnika() {
		return tipKorisnika;
	}

	public void setTipKorisnika(TipKorisnika tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}
}
