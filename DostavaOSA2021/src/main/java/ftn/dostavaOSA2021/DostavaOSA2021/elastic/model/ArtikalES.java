package ftn.dostavaOSA2021.DostavaOSA2021.elastic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import ftn.dostavaOSA2021.DostavaOSA2021.model.Artikal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "artikli")
@Setting(settingPath = "/analyzer/serbianAnalyzer.json")
public class ArtikalES {
    
//	SA OVA DVA ISPOD NE RADI IZMENA I BRISANJE
	@Id
	private String id;
	
//	SA OVIM @Id NE RADI PRETRAGA
//	@Id
    private Long idArtikla;

    @Field(type = FieldType.Text)
    private String naziv;

//    @Field(type = FieldType.Text)
//    private String pdfFile;
    
    @Field(type = FieldType.Text)
    private String opis;

    @Field(type = FieldType.Double)
    private Double cena;
    
    //zbog Loader klase
    //mapper
    public ArtikalES(Artikal artikal){
        this.idArtikla = artikal.getIdArtikal();
        this.naziv = artikal.getNaziv();
        this.cena = artikal.getCena();
        this.opis = artikal.getOpis();
    }

}
