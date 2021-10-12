package ftn.dostavaOSA2021.DostavaOSA2021.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "artikal")
public class Artikal {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idArtikal", nullable = false, unique = true)
	private Long idArtikal;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@Column(name = "opis", nullable = false)
	private String opis;
	
	@Column(name = "cena", nullable = false)
	private Double cena;
	
	@Column(name = "putanja_slike", nullable = false)
	private String putanjaSlike;
	
	@OneToMany(cascade = {ALL}, fetch=LAZY, mappedBy="artikal")
	private List<ArtikalAkcija> artikalAkcija = new ArrayList<ArtikalAkcija>();
	
	@ManyToOne
	@JoinColumn(name = "prodavac", referencedColumnName = "idProdavac", nullable = false)
	private Prodavac prodavac;
	
	@OneToMany(cascade = {ALL}, fetch=LAZY, mappedBy="artikal")
	private List<Stavka> stavke = new ArrayList<Stavka>();
	
	public Artikal() {
		super();
	}

	public Artikal(Long idArtikal, String naziv, String opis, Double cena, String putanjaSlike, List<ArtikalAkcija> artikalAkcija,
			Prodavac prodavac, List<Stavka> stavke) {
		super();
		this.idArtikal = idArtikal;
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
		this.putanjaSlike = putanjaSlike;
		this.artikalAkcija = artikalAkcija;
		this.prodavac = prodavac;
		this.stavke = stavke;
	}

	public Long getIdArtikal() {
		return idArtikal;
	}

	public void setIdArtikal(Long idArtikal) {
		this.idArtikal = idArtikal;
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

	public List<ArtikalAkcija> getArtikalAkcija() {
		return artikalAkcija;
	}

	public void setArtikalAkcija(List<ArtikalAkcija> artikalAkcija) {
		this.artikalAkcija = artikalAkcija;
	}

	public Prodavac getProdavac() {
		return prodavac;
	}

	public void setProdavac(Prodavac prodavac) {
		this.prodavac = prodavac;
	}

	public List<Stavka> getStavke() {
		return stavke;
	}

	public void setStavke(List<Stavka> stavke) {
		this.stavke = stavke;
	}

}
