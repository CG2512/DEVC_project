package jmaster.io.devc_ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {
//	@NotBlank(message="{not.blank}")
//	@Size(min = 1,max = 20,message = "{size.msg}")
	private String keyword;
	
	private Integer currentPage;
	private Integer size;
	private String sortedField;
	
	
	
	
}
