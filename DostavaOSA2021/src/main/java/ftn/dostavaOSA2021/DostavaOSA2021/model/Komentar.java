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
@Table(name = "komentar")
public class Komentar {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idKomentar", nullable = false, unique = true)
	private Long idKomentar;
	
	@Column(name = "tekst", nullable = false)
	private String tekst;
	
	@Column(name = "ocena", nullable = false)
	private Integer ocena;
	
	@Column(name = "prihvacen", nullable = false)
	private boolean prihvacen;
	
	@ManyToOne
	@JoinColumn(name = "artikal", referencedColumnName = "idArtikal", nullable = false)
	private Artikal artikal;
	
	@ManyToOne
	@JoinColumn(name = "korisnik", referencedColumnName = "idKorisnik", nullable = false)
	private Kupac kupac;
	
	public Komentar() {
		super();
	}

	public Komentar(Long idKomentar, String tekst, Integer ocena, boolean prihvacen, Artikal artikal, Kupac kupac) {
		super();
		this.idKomentar = idKomentar;
		this.tekst = tekst;
		this.ocena = ocena;
		this.prihvacen = prihvacen;
		this.artikal = artikal;
		this.kupac = kupac;
	}

	public Long getIdKomentar() {
		return idKomentar;
	}

	public void setIdKomentar(Long idKomentar) {
		this.idKomentar = idKomentar;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Integer getOcena() {
		return ocena;
	}

	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}

	public boolean isPrihvacen() {
		return prihvacen;
	}

	public void setPrihvacen(boolean prihvacen) {
		this.prihvacen = prihvacen;
	}

	public Artikal getArtikal() {
		return artikal;
	}

	public void setArtikal(Artikal artikal) {
		this.artikal = artikal;
	}

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}
}
