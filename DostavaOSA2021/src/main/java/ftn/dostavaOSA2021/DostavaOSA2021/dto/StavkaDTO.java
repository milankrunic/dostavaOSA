package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Stavka;

public class StavkaDTO {
	
	private Long idStavka;
	private int kolicina;
	private Long idArtikal;
	private String artikal;
	private Long idPorudzbina;
	
	public StavkaDTO() {
		super();
	}

	public StavkaDTO(Long idStavka, int kolicina, Long idArtikal, String artikal, Long idPorudzbina) {
		super();
		this.idStavka = idStavka;
		this.kolicina = kolicina;
		this.idArtikal = idArtikal;
		this.artikal = artikal;
		this.idPorudzbina = idPorudzbina;
	}
	
	public StavkaDTO(Stavka stavka) {
		this(stavka.getIdStavka(), stavka.getKolicina(), stavka.getArtikal().getIdArtikal(), stavka.getArtikal().getNaziv(), stavka.getPorudzbina().getIdPorudzbina());
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

	public Long getIdPorudzbina() {
		return idPorudzbina;
	}

	public void setIdPorudzbina(Long idPorudzbina) {
		this.idPorudzbina = idPorudzbina;
	}
}
