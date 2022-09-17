package ftn.dostavaOSA2021.DostavaOSA2021.elastic.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Porudzbina;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "porudzbine")
@Setting(settingPath = "/analyzer/serbianAnalyzer.json")
public class PorudzbinaES {

    @Id
    private String id;
    
    private Long idPorudzbina;
    
    @Field(type = FieldType.Date)
	private Date datum;
	
	@Field(type = FieldType.Integer)
	private int ocena;
	
	@Field(type = FieldType.Text)
	private String komentar;
	
	@Field(type = FieldType.Boolean)
	private boolean anonimanKomentar;
	
	@Field(type = FieldType.Double)
	private double cena;
	
	//mapper
	public PorudzbinaES(Porudzbina porudzbina) {
		this.idPorudzbina = porudzbina.getIdPorudzbina();
		this.datum = porudzbina.getSatnica();
		this.ocena = porudzbina.getOcena();
		this.komentar = porudzbina.getKomentar();
		this.anonimanKomentar = porudzbina.isAnonimanKomentar();
		this.cena = porudzbina.getCena();
	}
	
}
