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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kupac")
public class Kupac{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idKupac", nullable = false, unique = true)
	private Long idKupac;
	
	@Column(name = "adresa", nullable = false)
	private String adresa;
	
	@ManyToOne
	@JoinColumn(name = "korisnik_id", referencedColumnName = "idKorisnik", nullable = false)
	private Korisnik korisnik;
	
	@OneToMany(cascade = {ALL}, fetch=LAZY, mappedBy="kupac")
	private List<Komentar> komentari = new ArrayList<Komentar>();
	
	@OneToMany(cascade = {ALL}, fetch=LAZY, mappedBy="kupac")
	private List<Porudzbina> porudzbine = new ArrayList<Porudzbina>();

}
