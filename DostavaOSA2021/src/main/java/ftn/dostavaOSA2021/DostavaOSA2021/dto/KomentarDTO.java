package ftn.dostavaOSA2021.DostavaOSA2021.dto;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Komentar;

public class KomentarDTO {
	
	private Long idKomentar;
	private String tekst;
	private Integer ocena;
	private boolean prihvacen;
	private Long idArtikla;
	private String artikal;
	private Long idKupac;
	private String kupac;
	
	public KomentarDTO() {
		super();
	}
	
	public KomentarDTO(Long idKomentar, String tekst, Integer ocena, boolean prihvacen, Long idArtikla, String artikal,
			Long idKupac, String kupac) {
		super();
		this.idKomentar = idKomentar;
		this.tekst = tekst;
		this.ocena = ocena;
		this.prihvacen = prihvacen;
		this.idArtikla = idArtikla;
		this.artikal = artikal;
		this.idKupac = idKupac;
		this.kupac = kupac;
	}

	public KomentarDTO(Komentar komentar) {
//		this(komentar.getIdKomentar(), komentar.getTekst(), komentar.getOcena(), komentar.isPrihvacen(), komentar.getArtikal().getIdArtikal(), komentar.getArtikal().getNaziv(), komentar.getKupac().getIdKorisnik(), komentar.getKupac().getKorisnickoIme());
//		this(komentar.getIdKomentar(), komentar.getTekst(), komentar.getOcena(), komentar.isPrihvacen(), null, komentar.getArtikal().getNaziv(), null, komentar.getKupac().getKorisnickoIme());
		this(komentar.getIdKomentar(), komentar.getTekst(), komentar.getOcena(), komentar.isPrihvacen(), null, null, null, null);
	}

	public Long getIdKomentar() {
		return idKomentar;
	}

	public void setIdKomentar(Long idKomentar) {
		this.idKomentar = idKomentar;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Integer getOcena() {
		return ocena;
	}

	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}

	public boolean isPrihvacen() {
		return prihvacen;
	}

	public void setPrihvacen(boolean prihvacen) {
		this.prihvacen = prihvacen;
	}

	public Long getIdArtikla() {
		return idArtikla;
	}

	public void setIdArtikla(Long idArtikla) {
		this.idArtikla = idArtikla;
	}

	public String getArtikal() {
		return artikal;
	}

	public void setArtikal(String artikal) {
		this.artikal = artikal;
	}

	public Long getIdKupac() {
		return idKupac;
	}

	public void setIdKupac(Long idKupac) {
		this.idKupac = idKupac;
	}

	public String getKupac() {
		return kupac;
	}

	public void setKupac(String kupac) {
		this.kupac = kupac;
	}

}
