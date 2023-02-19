package ftn.dostavaOSA2021.DostavaOSA2021.model;

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
@Table(name = "komentar")
public class Komentar {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idKomentar", nullable = false, unique = true)
	private Long idKomentar;
	
	@Column(name = "tekst", nullable = false)
	private String tekst;
	
	@Column(name = "ocena", nullable = false)
	private Integer ocena;
	
	@Column(name = "prihvacen", nullable = false)
	private boolean prihvacen;
	
	@Column(name = "arhiviran", nullable = false)
	private boolean arhiviran;
	
	@ManyToOne
	@JoinColumn(name = "artikal", referencedColumnName = "idArtikal", nullable = true)
	private Artikal artikal;
	
	@ManyToOne
	@JoinColumn(name = "kupac", referencedColumnName = "idKupac", nullable = true)
	private Kupac kupac;
	
}
