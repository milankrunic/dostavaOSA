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
@Table(name = "stavka")
public class Stavka {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idStavka", nullable = false, unique = true)
	private Long idStavka;
	
	@Column(name = "kolicina", nullable = false)
	private int kolicina;
	
	@ManyToOne
	@JoinColumn(name = "artikal", referencedColumnName = "idArtikal", nullable = false)
	private Artikal artikal;
	
	@ManyToOne
	@JoinColumn(name = "korisnik", referencedColumnName = "idKorisnik", nullable = false)
	private Kupac kupac;

	public Stavka() {
		super();
	}

	public Stavka(Long idStavka, int kolicina, Artikal artikal, Kupac kupac) {
		super();
		this.idStavka = idStavka;
		this.kolicina = kolicina;
		this.artikal = artikal;
		this.kupac = kupac;
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