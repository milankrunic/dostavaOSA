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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "kupac")
public class Kupac{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idKupac", nullable = false, unique = true)
	private Long idKupac;
	
	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "prezime", nullable = false)
	private String prezime;
	
	@Column(name = "korisnickoIme", nullable = false)
	private String korisnickoIme;
	
	@Column(name = "lozinka", nullable = false)
	private String lozinka;
	
	@Column(name = "blokiran", nullable = false)
	private boolean blokiran;
	
	@Column(name = "adresa", nullable = false)
	private String adresa;
	
	@OneToMany(cascade = {ALL}, fetch=LAZY, mappedBy="kupac")
	private List<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
	
	public Kupac() {
		super();
	}

	public Kupac(Long idKupac, String ime, String prezime, String korisnickoIme, String lozinka, boolean blokiran,
			String adresa, List<Porudzbina> porudzbine) {
		super();
		this.idKupac = idKupac;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.blokiran = blokiran;
		this.adresa = adresa;
		this.porudzbine = porudzbine;
	}

	public Long getIdKupac() {
		return idKupac;
	}

	public void setIdKupac(Long idKupac) {
		this.idKupac = idKupac;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public boolean isBlokiran() {
		return blokiran;
	}

	public void setBlokiran(boolean blokiran) {
		this.blokiran = blokiran;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public List<Porudzbina> getPorudzbine() {
		return porudzbine;
	}

	public void setPorudzbine(List<Porudzbina> porudzbine) {
		this.porudzbine = porudzbine;
	}
}
