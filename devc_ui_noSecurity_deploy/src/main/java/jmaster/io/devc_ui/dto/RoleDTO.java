package jmaster.io.devc_ui.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
	private Integer id;
	@NotBlank
	@Size(min = 0, max = 20)
	private String name;
	
	public RoleDTO(String name) {
		this.name=name;
	}
}
