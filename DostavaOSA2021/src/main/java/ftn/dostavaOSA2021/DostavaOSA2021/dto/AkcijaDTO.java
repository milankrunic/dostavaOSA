package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import java.util.Date;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Akcija;

public class AkcijaDTO {
	
	private Long idAkcija;
	private String tekst;
	private Integer procenat;
	private Date odKad;
	private Date doKad;
//	private Long prodavac;
	
	public AkcijaDTO() {
		super();
	}

	public AkcijaDTO(Long idAkcija, String tekst, Integer procenat, Date odKad, Date doKad) {
		super();
		this.idAkcija = idAkcija;
		this.tekst = tekst;
		this.procenat = procenat;
		this.odKad = odKad;
		this.doKad = doKad;
//		this.prodavac = prodavac;
	}
	
	public AkcijaDTO(Akcija akcija) {
		this(akcija.getIdAkcija(), akcija.getTekst(), akcija.getProcenat(), akcija.getOdKad(), akcija.getDoKad());
	}

	public Long getIdAkcija() {
		return idAkcija;
	}

	public void setIdAkcija(Long idAkcija) {
		this.idAkcija = idAkcija;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Integer getProcenat() {
		return procenat;
	}

	public void setProcenat(Integer procenat) {
		this.procenat = procenat;
	}

	public Date getOdKad() {
		return odKad;
	}

	public void setOdKad(Date odKad) {
		this.odKad = odKad;
	}

	public Date getDoKad() {
		return doKad;
	}

	public void setDoKad(Date doKad) {
		this.doKad = doKad;
	}

//	public Long getProdavac() {
//		return prodavac;
//	}
//
//	public void setProdavac(Long prodavac) {
//		this.prodavac = prodavac;
//	}
}
