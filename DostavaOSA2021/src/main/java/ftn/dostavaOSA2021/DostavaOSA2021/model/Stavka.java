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
	@JoinColumn(name = "porudzbina", referencedColumnName = "idPorudzbina", nullable = false)
	private Porudzbina porudzbina;

	public Stavka() {
		super();
	}

	public Stavka(Long idStavka, int kolicina, Artikal artikal, Porudzbina porudzbina) {
		super();
		this.idStavka = idStavka;
		this.kolicina = kolicina;
		this.artikal = artikal;
		this.porudzbina = porudzbina;
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

	public Porudzbina getPorudzbina() {
		return porudzbina;
	}

	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}
}