package ftn.dostavaOSA2021.DostavaOSA2021.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "porudzbina")
public class Porudzbina {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPorudzbina", nullable = false, unique = true)
	private Long idPorudzbina;
	
	@Column(name = "satnica", nullable = false)
	private Date satnica;
	
	@Column(name = "ocena", nullable = false)
	private int ocena;
	
	@Column(name = "komentar", nullable = false)
	private String komentar;
	
	@Column(name = "cena", nullable = false)
	private double cena;
	
	@Column(name = "dostavljeno", nullable = false)
	private boolean dostavljeno;
	
	@Column(name = "anonimanKomentar", nullable = false)
	private boolean anonimanKomentar;
	
	@Column(name = "arhiviranKomentar", nullable = false)
	private boolean arhiviranKomentar;
	
	@ManyToOne
	@JoinColumn(name = "kupac", referencedColumnName = "idKupac", nullable = false)
	private Kupac kupac;

}
