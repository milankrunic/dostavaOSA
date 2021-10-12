package ftn.dostavaOSA2021.DostavaOSA2021.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;


@MappedSuperclass
@Table(name = "korisnik")
public abstract class Korisnik {
	
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
	
	public Korisnik() {
		super();
	}

	public Korisnik(Long idKorisnik, String ime, String prezime, String korisnickoIme, String lozinka,
			boolean blokiran) {
		super();
		this.idKorisnik = idKorisnik;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.blokiran = blokiran;
	}

	public Long getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(Long idKorisnik) {
		this.idKorisnik = idKorisnik;
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
}