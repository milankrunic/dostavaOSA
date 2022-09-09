package ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtikalEsDTO {

    private String naziv;

    @Field(type = FieldType.Text)
    private String opis;

    @Field(type = FieldType.Double)
    private Double cena;
	
}
