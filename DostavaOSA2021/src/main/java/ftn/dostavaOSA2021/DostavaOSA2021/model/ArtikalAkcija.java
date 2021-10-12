package ftn.dostavaOSA2021.DostavaOSA2021.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "artikal_akcija")
public class ArtikalAkcija {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idArtikalAkcija", nullable = false, unique = true)
	private Long idArtikalAkcija;
	
	@ManyToOne
	@JoinColumn(name = "akcija", referencedColumnName = "idAkcija", nullable = false)
	private Akcija akcija;
	
	@ManyToOne
	@JoinColumn(name = "artikal", referencedColumnName = "idArtikal", nullable = false)
	private Artikal artikal;
	
	public ArtikalAkcija() {
		super();
	}

	public ArtikalAkcija(Long idArtikalAkcija, Akcija akcija, Artikal artikal) {
		super();
		this.idArtikalAkcija = idArtikalAkcija;
		this.akcija = akcija;
		this.artikal = artikal;
	}

	public Long getIdArtikalAkcija() {
		return idArtikalAkcija;
	}

	public void setIdArtikalAkcija(Long idArtikalAkcija) {
		this.idArtikalAkcija = idArtikalAkcija;
	}

	public Akcija getAkcija() {
		return akcija;
	}

	public void setAkcija(Akcija akcija) {
		this.akcija = akcija;
	}

	public Artikal getArtikal() {
		return artikal;
	}

	public void setArtikal(Artikal artikal) {
		this.artikal = artikal;
	}
}
