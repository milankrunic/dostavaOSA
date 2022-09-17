package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import java.util.Date;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Porudzbina;

public class PorudzbinaDTO {
	
	private Long idPorudzbina;
	private Date satnica;
	private int ocena;
	private String komentar;
	private double cena;
	private boolean dostavljeno;
	private boolean anonimanKomentar;
	private boolean arhiviranKomentar;
	private Long idKupac;
	private String kupac;
	
	public PorudzbinaDTO() {
		super();
	}

	public PorudzbinaDTO(Long idPorudzbina, Date satnica, int ocena, String komentar, double cena, boolean dostavljeno,
			boolean anonimanKomentar, boolean arhiviranKomentar, Long idKupac, String kupac) {
		super();
		this.idPorudzbina = idPorudzbina;
		this.satnica = satnica;
		this.ocena = ocena;
		this.komentar = komentar;
		this.cena = cena;
		this.dostavljeno = dostavljeno;
		this.anonimanKomentar = anonimanKomentar;
		this.arhiviranKomentar = arhiviranKomentar;
		this.idKupac = idKupac;
		this.kupac = kupac;
	}

	public PorudzbinaDTO(Porudzbina p) {
		this(p.getIdPorudzbina(), p.getSatnica(), p.getOcena(), p.getKomentar(), p.getCena(), p.isDostavljeno(), p.isAnonimanKomentar(), p.isArhiviranKomentar(), p.getKupac().getIdKorisnik(), p.getKupac().getKorisnickoIme());
	}

	public Long getIdPorudzbina() {
		return idPorudzbina;
	}

	public void setIdPorudzbina(Long idPorudzbina) {
		this.idPorudzbina = idPorudzbina;
	}

	public Date getSatnica() {
		return satnica;
	}

	public void setSatnica(Date satnica) {
		this.satnica = satnica;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}
	
	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public boolean isDostavljeno() {
		return dostavljeno;
	}

	public void setDostavljeno(boolean dostavljeno) {
		this.dostavljeno = dostavljeno;
	}

	public boolean isAnonimanKomentar() {
		return anonimanKomentar;
	}

	public void setAnonimanKomentar(boolean anonimanKomentar) {
		this.anonimanKomentar = anonimanKomentar;
	}

	public boolean isArhiviranKomentar() {
		return arhiviranKomentar;
	}

	public void setArhiviranKomentar(boolean arhiviranKomentar) {
		this.arhiviranKomentar = arhiviranKomentar;
	}

	public Long getIdKupac() {
		return idKupac;
	}

	public void setIdKupac(Long idKupac) {
		this.idKupac = idKupac;
	}

	public String getKupac() {
		return kupac;
	}

	public void setKupac(String kupac) {
		this.kupac = kupac;
	}

}
