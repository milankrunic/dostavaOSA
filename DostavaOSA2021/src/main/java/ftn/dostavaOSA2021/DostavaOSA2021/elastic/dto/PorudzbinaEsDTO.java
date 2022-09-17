package ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto;

import java.util.Date;

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
public class PorudzbinaEsDTO {

	private Date datum;
	
	private int ocena;
	
	private String komentar;
	
	private boolean anonimanKomentar;
	
}
