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


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "artikli")
@Setting(settingPath = "/analyzer/serbianAnalyzer.json")
public class ArtikalES {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String naziv;

    @Field(type = FieldType.Text)
    private String opis;

    @Field(type = FieldType.Double)
    private Double cena;

}
