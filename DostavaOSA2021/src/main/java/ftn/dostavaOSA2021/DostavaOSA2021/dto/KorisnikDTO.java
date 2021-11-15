package ftn.dostavaOSA2021.DostavaOSA2021.dto;


import ftn.dostavaOSA2021.DostavaOSA2021.model.Korisnik;
import ftn.dostavaOSA2021.DostavaOSA2021.model.TipKorisnika;

public class KorisnikDTO {
	
	private Long idKorisnik;
	private String ime;
	private String prezime;
	private String korIme;
	private String lozinka;
//	private String adresa;
//	private String email;
//	private String nazivProdavca;
//	private Date poslujeOd;
	private TipKorisnika tipKorisnika;
	private boolean blokiran;
	
	public KorisnikDTO() {
		super();
	}
	
	public KorisnikDTO(String korIme, String lozinka, TipKorisnika tipKorisnika) {
	super();
	this.korIme = korIme;
	this.lozinka = lozinka;
	this.tipKorisnika = tipKorisnika;
}

	public KorisnikDTO(Long idKorisnik, String ime, String prezime, String korIme, String lozinka, TipKorisnika tipKorisnika) {
		super();
		this.idKorisnik = idKorisnik;
		this.ime = ime;
		this.prezime = prezime;
		this.korIme = korIme;
		this.lozinka = lozinka;
//		this.adresa = adresa;
//		this.email = email;
//		this.nazivProdavca = nazivProdavca;
//		this.poslujeOd = poslujeOd;
		this.tipKorisnika = tipKorisnika;
//		this.blokiran = blokiran;
	}



	public KorisnikDTO(Korisnik korisnik) {
		this(korisnik.getIdKorisnik(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getKorisnickoIme(), korisnik.getLozinka(),
			korisnik.getTipKorisnika());
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

//	public String getAdresa() {
//		return adresa;
//	}
//
//	public void setAdresa(String adresa) {
//		this.adresa = adresa;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getNazivProdavca() {
//		return nazivProdavca;
//	}
//
//	public void setNazivProdavca(String nazivProdavca) {
//		this.nazivProdavca = nazivProdavca;
//	}
//
//	public Date getPoslujeOd() {
//		return poslujeOd;
//	}
//
//	public void setPoslujeOd(Date poslujeOd) {
//		this.poslujeOd = poslujeOd;
//	}

	public TipKorisnika getTipKorisnika() {
		return tipKorisnika;
	}

	public void setTipKorisnika(TipKorisnika tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}

	public boolean isBlokiran() {
		return blokiran;
	}

	public void setBlokiran(boolean blokiran) {
		this.blokiran = blokiran;
	}

}
