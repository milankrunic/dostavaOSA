package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Korisnik;

public class KorisnikDTO {
	
	private Long idKorisnik;
	private String ime;
	private String prezime;
	private String korIme;
	private String lozinka;
	private boolean blokiran;
	
	public KorisnikDTO() {
		super();
	}

	public KorisnikDTO(Long idKorisnik, String ime, String prezime, String korIme, String lozinka, boolean blokiran) {
		super();
		this.idKorisnik = idKorisnik;
		this.ime = ime;
		this.prezime = prezime;
		this.korIme = korIme;
		this.lozinka = lozinka;
		this.blokiran = blokiran;
	}
	
	public KorisnikDTO(Korisnik korisnik) {
		this(korisnik.getIdKorisnik(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getKorisnickoIme(), korisnik.getLozinka(), korisnik.isBlokiran());
	}

	public Long getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(Long idKorisnik) {
		this.idKorisnik = idKorisnik;
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
}
