package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import ftn.dostavaOSA2021.DostavaOSA2021.model.ArtikalAkcija;

public class ArtikalAkcijaDTO {

	private Long idArtikalAkcija;
	private Long idArtikal;
	private Long idAkcija;
	private String artikal;
	
	public ArtikalAkcijaDTO() {
		super();
	}

	public ArtikalAkcijaDTO(Long idArtikalAkcija, Long idArtikal, Long idAkcija, String artikal) {
		super();
		this.idArtikalAkcija = idArtikalAkcija;
		this.idArtikal = idArtikal;
		this.idAkcija = idAkcija;
		this.artikal = artikal;
	}
	
	public ArtikalAkcijaDTO(ArtikalAkcija aa) {
		this(aa.getIdArtikalAkcija(), aa.getArtikal().getIdArtikal(), aa.getAkcija().getIdAkcija(), aa.getArtikal().getNaziv());
	}

	public Long getIdArtikalAkcija() {
		return idArtikalAkcija;
	}

	public void setIdArtikalAkcija(Long idArtikalAkcija) {
		this.idArtikalAkcija = idArtikalAkcija;
	}

	public Long getIdArtikal() {
		return idArtikal;
	}

	public void setIdArtikal(Long idArtikal) {
		this.idArtikal = idArtikal;
	}

	public Long getIdAkcija() {
		return idAkcija;
	}

	public void setIdAkcija(Long idAkcija) {
		this.idAkcija = idAkcija;
	}

	public String getArtikal() {
		return artikal;
	}

	public void setArtikal(String artikal) {
		this.artikal = artikal;
	}
}
