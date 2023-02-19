package ftn.dostavaOSA2021.DostavaOSA2021.model;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "prodavac")
public class Prodavac{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProdavac", nullable = false, unique = true)
	private Long idProdavac;
	
	@Column(name = "adresa", nullable = false)
	private String adresa;
	
	@Column(name = "naziv_prodavca", nullable = false)
	private String nazivProdavca;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "poslujeOd", nullable = false)
	private Date poslujeOd;
	 
	@ManyToOne
	@JoinColumn(name = "korisnik_id", referencedColumnName = "idKorisnik", nullable = false)
	private Korisnik korisnik;
	
	@OneToMany(mappedBy="prodavac", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<Artikal> artikli = new ArrayList<Artikal>();

}
