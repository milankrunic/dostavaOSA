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
@Table(name = "artikal")
public class Artikal {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idArtikal", nullable = false, unique = true)
	private Long idArtikal;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@Column(name = "opis", nullable = false)
	private String opis;
	
	@Column(name = "cena", nullable = false)
	private Double cena;
	
	@Column(name = "putanja_slike", nullable = true)
	private String putanjaSlike;
	
	@ManyToOne
	@JoinColumn(name = "prodavac", referencedColumnName = "idProdavac", nullable = false)
	private Prodavac prodavac;
	
	@OneToMany(cascade = {ALL}, fetch=LAZY, mappedBy="artikal")
	private List<Komentar> komentari = new ArrayList<Komentar>();

}
