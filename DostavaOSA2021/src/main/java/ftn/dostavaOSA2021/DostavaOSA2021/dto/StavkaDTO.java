package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Stavka;

public class StavkaDTO {
	
	private Long idStavka;
	private int kolicina;
//	private Long artikal;
//	private Long porudzbina;
	
	public StavkaDTO() {
		super();
	}

	public StavkaDTO(Long idStavka, int kolicina) {
		super();
		this.idStavka = idStavka;
		this.kolicina = kolicina;
//		this.artikal = artikal;
//		this.porudzbina = porudzbina;
	}
	
	public StavkaDTO(Stavka stavka) {
		this(stavka.getIdStavka(), stavka.getKolicina());
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

//	public Long getArtikal() {
//		return artikal;
//	}
//
//	public void setArtikal(Long artikal) {
//		this.artikal = artikal;
//	}
//
//	public Long getPorudzbina() {
//		return porudzbina;
//	}
//
//	public void setPorudzbina(Long porudzbina) {
//		this.porudzbina = porudzbina;
//	}
}
