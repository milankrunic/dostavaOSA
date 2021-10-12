package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Administrator;

public class AdministratorDTO {
	
	private Long idAdministrator;
	private String ime;
	private String prezime;
	private String korIme; 
	private String lozinka;
	private boolean blokiran;
	
	public AdministratorDTO() {
		super();
	}

	public AdministratorDTO(Long idAdministrator, String ime, String prezime, String korIme, String lozinka,
			boolean blokiran) {
		super();
		this.idAdministrator = idAdministrator;
		this.ime = ime;
		this.prezime = prezime;
		this.korIme = korIme;
		this.lozinka = lozinka;
		this.blokiran = blokiran;
	}
	
	public AdministratorDTO(Administrator admin) {
		this(admin.getIdAdministrator(), admin.getIme(), admin.getPrezime(), admin.getKorisnickoIme(), admin.getLozinka(), admin.isBlokiran());
	}

	public Long getIdAdministrator() {
		return idAdministrator;
	}

	public void setIdAdministrator(Long idAdministrator) {
		this.idAdministrator = idAdministrator;
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