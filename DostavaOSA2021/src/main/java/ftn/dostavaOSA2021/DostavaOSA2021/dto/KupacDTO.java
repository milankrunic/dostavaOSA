package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Kupac;
import ftn.dostavaOSA2021.DostavaOSA2021.model.TipKorisnika;

public class KupacDTO {

	private Long idKupac;
	private String adresaKupca;
	private String imeKupca;
	private String prezimeKupca;
	private String korImeKupca;
	private String lozinkaKupca;
	private boolean blokiran;
	private TipKorisnika tipKorisnika;
	
	public KupacDTO() {
		super();
	}

	
	
	public KupacDTO(Long idKupac, String adresaKupca, String imeKupca, String prezimeKupca, String korImeKupca,
			String lozinkaKupca, boolean blokiran, TipKorisnika tipKorisnika) {
		super();
		this.idKupac = idKupac;
		this.adresaKupca = adresaKupca;
		this.imeKupca = imeKupca;
		this.prezimeKupca = prezimeKupca;
		this.korImeKupca = korImeKupca;
		this.lozinkaKupca = lozinkaKupca;
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



	public String getAdresaKupca() {
		return adresaKupca;
	}



	public void setAdresaKupca(String adresaKupca) {
		this.adresaKupca = adresaKupca;
	}



	public String getImeKupca() {
		return imeKupca;
	}



	public void setImeKupca(String imeKupca) {
		this.imeKupca = imeKupca;
	}



	public String getPrezimeKupca() {
		return prezimeKupca;
	}



	public void setPrezimeKupca(String prezimeKupca) {
		this.prezimeKupca = prezimeKupca;
	}



	public String getKorImeKupca() {
		return korImeKupca;
	}



	public void setKorImeKupca(String korImeKupca) {
		this.korImeKupca = korImeKupca;
	}



	public String getLozinkaKupca() {
		return lozinkaKupca;
	}



	public void setLozinkaKupca(String lozinkaKupca) {
		this.lozinkaKupca = lozinkaKupca;
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
