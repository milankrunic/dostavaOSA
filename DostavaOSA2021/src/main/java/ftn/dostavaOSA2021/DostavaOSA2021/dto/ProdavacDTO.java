package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import java.util.Date;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Prodavac;
import ftn.dostavaOSA2021.DostavaOSA2021.model.TipKorisnika;

public class ProdavacDTO{
	
	private Long idProdavac;
	private String nazivProdavca;
	private String email;
	private String adresa;
	private Date poslujeOd;
	private String ime;
	private String prezime;
	private String korIme;
	private String lozinka;
	private boolean blokiran;
	private TipKorisnika tipKorisnika;
	
	public ProdavacDTO() {
		super();
	}

	public ProdavacDTO(Long idProdavac, String nazivProdavca, String email, String adresa, Date poslujeOd, String ime,
			String prezime, String korIme, String lozinka, boolean blokiran, TipKorisnika tipKorisnika) {
		super();
		this.idProdavac = idProdavac;
		this.nazivProdavca = nazivProdavca;
		this.email = email;
		this.adresa = adresa;
		this.poslujeOd = poslujeOd;
		this.ime = ime;
		this.prezime = prezime;
		this.korIme = korIme;
		this.lozinka = lozinka;
		this.blokiran = blokiran;
		this.tipKorisnika = tipKorisnika;
	}

	
	public ProdavacDTO(Prodavac p) {
		this(p.getIdKorisnik(), p.getNazivProdavca(), p.getEmail(), p.getAdresa(), p.getPoslujeOd(), p.getIme(), p.getPrezime(), p.getKorisnickoIme(), p.getLozinka(), p.isBlokiran(), p.getTipKorisnika());
	}

	public Long getIdProdavac() {
		return idProdavac;
	}

	public void setIdProdavac(Long idProdavac) {
		this.idProdavac = idProdavac;
	}

	public String getNazivProdavca() {
		return nazivProdavca;
	}

	public void setNazivProdavca(String nazivProdavca) {
		this.nazivProdavca = nazivProdavca;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Date getPoslujeOd() {
		return poslujeOd;
	}

	public void setPoslujeOd(Date poslujeOd) {
		this.poslujeOd = poslujeOd;
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
