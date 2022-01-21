package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Stavka;

public class StavkaDTO {
	
	private Long idStavka;
	private int kolicina;
	private Long idArtikal;
	private String artikal;
	private Long idKupca;
	private String kupac;
	
	public StavkaDTO() {
		super();
	}

	public StavkaDTO(Long idStavka, int kolicina, Long idArtikal, String artikal, Long idKupca, String kupac) {
		super();
		this.idStavka = idStavka;
		this.kolicina = kolicina;
		this.idArtikal = idArtikal;
		this.artikal = artikal;
		this.idKupca = idKupca;
		this.kupac = kupac;
	}
	
	public StavkaDTO(Stavka stavka) {
		this(stavka.getIdStavka(), stavka.getKolicina(), stavka.getArtikal().getIdArtikal(), stavka.getArtikal().getNaziv(), stavka.getKupac().getIdKorisnik(), stavka.getKupac().getIme());
	}

	public Long getIdStavka() {
		return idStavka;
	}

	public void setIdStavka(Long idStavka) {
		this.idStavka = idStavka;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	
	public Long getIdArtikal() {
		return idArtikal;
	}

	public void setIdArtikal(Long idArtikal) {
		this.idArtikal = idArtikal;
	}

	public String getArtikal() {
		return artikal;
	}

	public void setArtikal(String artikal) {
		this.artikal = artikal;
	}

	public Long getIdKupca() {
		return idKupca;
	}

	public void setIdKupca(Long idKupca) {
		this.idKupca = idKupca;
	}

	public String getKupac() {
		return kupac;
	}

	public void setKupac(String kupac) {
		this.kupac = kupac;
	}

}
