package ftn.dostavaOSA2021.DostavaOSA2021.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

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
@Table(name = "akcija")
public class Akcija {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAkcija", nullable = false, unique = true)
	private Long idAkcija;
	
	@Column(name = "tekst", nullable = false)
	private String tekst;
	
	@Column(name = "procenat", nullable = false)
	private Integer procenat;
	
	@Column(name = "odKad", nullable = false)
	private Date odKad;
	
	@Column(name = "doKad", nullable = false)
	private Date doKad;
	
	@ManyToOne
	@JoinColumn(name = "prodavac", referencedColumnName = "idProdavac", nullable = false)
	private Prodavac prodavac;
	
	@OneToMany(cascade = {ALL}, fetch=LAZY, mappedBy="akcija")
	private List<ArtikalAkcija> artikalAkcija = new ArrayList<ArtikalAkcija>();
	
	public Akcija() {
		super();
	}

	public Akcija(Long idAkcija, String tekst, Integer procenat, Date odKad, Date doKad, Prodavac prodavac,
			List<ArtikalAkcija> artikalAkcija) {
		super();
		this.idAkcija = idAkcija;
		this.tekst = tekst;
		this.procenat = procenat;
		this.odKad = odKad;
		this.doKad = doKad;
		this.prodavac = prodavac;
		this.artikalAkcija = artikalAkcija;
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

	public Prodavac getProdavac() {
		return prodavac;
	}

	public void setProdavac(Prodavac prodavac) {
		this.prodavac = prodavac;
	}

	public List<ArtikalAkcija> getArtikalAkcija() {
		return artikalAkcija;
	}

	public void setArtikalAkcija(List<ArtikalAkcija> artikalAkcija) {
		this.artikalAkcija = artikalAkcija;
	}
}
