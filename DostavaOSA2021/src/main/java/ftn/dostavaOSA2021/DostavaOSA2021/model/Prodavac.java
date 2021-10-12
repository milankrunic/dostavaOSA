package ftn.dostavaOSA2021.DostavaOSA2021.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prodavac")
public class Prodavac{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProdavac", nullable = false, unique = true)
	private Long idProdavac;
	
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
	
	@Column(name = "naziv_prodavca", nullable = false)
	private String nazivProdavca;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "adresa", nullable = false)
	private String adresa;
	
	@Column(name = "poslujeOd", nullable = false)
	private Date poslujeOd;
	
	@OneToMany(mappedBy="prodavac", cascade = {ALL}, fetch=LAZY)
	private List<Artikal> artikli = new ArrayList<Artikal>();
	
	@OneToMany(mappedBy="prodavac", cascade = {ALL}, fetch=LAZY)
	private List<Akcija> akcije = new ArrayList<Akcija>();
	
	public Prodavac() {
		super();
	}

	public Prodavac(Long idProdavac, String ime, String prezime, String korisnickoIme, String lozinka, boolean blokiran,
			String nazivProdavca, String email, String adresa, Date poslujeOd, List<Artikal> artikli,
			List<Akcija> akcije) {
		super();
		this.idProdavac = idProdavac;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.blokiran = blokiran;
		this.nazivProdavca = nazivProdavca;
		this.email = email;
		this.adresa = adresa;
		this.poslujeOd = poslujeOd;
		this.artikli = artikli;
		this.akcije = akcije;
	}

	public Long getIdProdavac() {
		return idProdavac;
	}

	public void setIdProdavac(Long idProdavac) {
		this.idProdavac = idProdavac;
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

	public String getNazivProdavca() {
		return nazivProdavca;
	}

	public void setNazivProdavca(String nazivProdavca) {
		this.nazivProdavca = nazivProdavca;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Date getPoslujeOd() {
		return poslujeOd;
	}

	public void setPoslujeOd(Date poslujeOd) {
		this.poslujeOd = poslujeOd;
	}

	public List<Artikal> getArtikli() {
		return artikli;
	}

	public void setArtikli(List<Artikal> artikli) {
		this.artikli = artikli;
	}

	public List<Akcija> getAkcije() {
		return akcije;
	}

	public void setAkcije(List<Akcija> akcije) {
		this.akcije = akcije;
	}

}
