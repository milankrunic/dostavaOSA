package ftn.dostavaOSA2021.DostavaOSA2021.elastic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleQueryES {
	
    private String field;
    
    private String value;
    
}
