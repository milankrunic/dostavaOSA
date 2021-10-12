package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;

public class ArtikalDTO {
	
	private Long idArtikla;
	private String naziv;
	private String opis;
	private Double cena;
	private String putanjaSlike;
	private Long idProdavac;
	private String prodavac;
	
	public ArtikalDTO() {
		super();
	}

	public ArtikalDTO(Long idArtikla, String naziv, String opis, Double cena, String putanjaSlike, Long idProdavac, String prodavac) {
		super();
		this.idArtikla = idArtikla;
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
		this.putanjaSlike = putanjaSlike;
		this.idProdavac = idProdavac;
		this.prodavac = prodavac;
	}
	
	public ArtikalDTO(Artikal artikal) {
		this(artikal.getIdArtikal(), artikal.getNaziv(), artikal.getOpis(), artikal.getCena(), artikal.getPutanjaSlike(), artikal.getProdavac().getIdProdavac(), artikal.getProdavac().getIme());
	}

	public Long getIdArtikla() {
		return idArtikla;
	}

	public void setIdArtikla(Long idArtikla) {
		this.idArtikla = idArtikla;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public String getPutanjaSlike() {
		return putanjaSlike;
	}

	public void setPutanjaSlike(String putanjaSlike) {
		this.putanjaSlike = putanjaSlike;
	}

	public Long getIdProdavac() {
		return idProdavac;
	}

	public void setIdProdavac(Long idProdavac) {
		this.idProdavac = idProdavac;
	}

	public String getProdavac() {
		return prodavac;
	}

	public void setProdavac(String prodavac) {
		this.prodavac = prodavac;
	}
}
