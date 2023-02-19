package ftn.dostavaOSA2021.DostavaOSA2021.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "korisnik")
public class Korisnik {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idKorisnik", nullable = false, unique = true)
	private Long idKorisnik;
	
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
	
	@Column(name = "tip_korisnika", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipKorisnika tipKorisnika;
	
	@OneToMany(mappedBy="korisnik", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<Administrator> administrator = new ArrayList<Administrator>();
	
	@OneToMany(mappedBy="korisnik", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<Prodavac> prodavac = new ArrayList<Prodavac>();
	
	@OneToMany(mappedBy="korisnik", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<Kupac> kupac = new ArrayList<Kupac>();

	public Korisnik(String korisnickoIme, String lozinka, TipKorisnika tipKorisnika) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.tipKorisnika = tipKorisnika;
	}
	
}