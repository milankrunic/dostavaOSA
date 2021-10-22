package ftn.dostavaOSA2021.DostavaOSA2021.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Prodavac extends Korisnik{
	
	@Column(name = "naziv_prodavca", nullable = true)
	private String nazivProdavca;
	
	@Column(name = "email", nullable = true)
	private String email;
	
	@Column(name = "adresa", nullable = true)
	private String adresa;
	
	@Column(name = "poslujeOd", nullable = true)
	private Date poslujeOd;
	 
	
	@OneToMany(mappedBy="prodavac", cascade = {ALL}, fetch=LAZY)
	private List<Artikal> artikli = new ArrayList<Artikal>();
	
	@OneToMany(mappedBy="prodavac", cascade = {ALL}, fetch=LAZY)
	private List<Akcija> akcije = new ArrayList<Akcija>();
	
	public Prodavac() {
		super();
	}

	public Prodavac(Long idKorisnik, String ime, String prezime, String korisnickoIme, String lozinka, boolean blokiran,
			TipKorisnika tipKorisnika, String nazivProdavca, String email, String adresa, Date poslujeOd,
			List<Artikal> artikli, List<Akcija> akcije) {
		super(idKorisnik, ime, prezime, korisnickoIme, lozinka, blokiran, tipKorisnika);
		this.nazivProdavca = nazivProdavca;
		this.email = email;
		this.adresa = adresa;
		this.poslujeOd = poslujeOd;
		this.artikli = artikli;
		this.akcije = akcije;
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
