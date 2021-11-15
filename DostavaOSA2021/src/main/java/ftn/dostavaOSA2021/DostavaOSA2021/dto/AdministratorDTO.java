package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Administrator;
import ftn.dostavaOSA2021.DostavaOSA2021.model.TipKorisnika;

public class AdministratorDTO {
	
	private Long idAdmin;
	private String imeAdmina;
	private String prezimeAdmina;
	private String korImeAdmina; 
	private String lozinkaAdmina;
	private boolean blokiran;
	private TipKorisnika tipKorisnika;
	
	public AdministratorDTO() {
		super();
	}

	public AdministratorDTO(Long idAdmin, String imeAdmina, String prezimeAdmina, String korImeAdmina, String lozinkaAdmina,
			boolean blokiran, TipKorisnika tipKorisnika) {
		super();
		this.idAdmin = idAdmin;
		this.imeAdmina = imeAdmina;
		this.prezimeAdmina = prezimeAdmina;
		this.korImeAdmina = korImeAdmina;
		this.lozinkaAdmina = lozinkaAdmina;
		this.blokiran = blokiran;
		this.tipKorisnika = tipKorisnika;
	}
	
	public AdministratorDTO(Administrator a) {
		this(a.getIdKorisnik(), a.getIme(), a.getPrezime(), a.getKorisnickoIme(), a.getLozinka(), a.isBlokiran(), a.getTipKorisnika());
	}

	public Long getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(Long idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getImeAdmina() {
		return imeAdmina;
	}

	public void setImeAdmina(String imeAdmina) {
		this.imeAdmina = imeAdmina;
	}

	public String getPrezimeAdmina() {
		return prezimeAdmina;
	}

	public void setPrezimeAdmina(String prezimeAdmina) {
		this.prezimeAdmina = prezimeAdmina;
	}

	public String getKorImeAdmina() {
		return korImeAdmina;
	}

	public void setKorImeAdmina(String korImeAdmina) {
		this.korImeAdmina = korImeAdmina;
	}

	public String getLozinkaAdmina() {
		return lozinkaAdmina;
	}

	public void setLozinkaAdmina(String lozinkaAdmina) {
		this.lozinkaAdmina = lozinkaAdmina;
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