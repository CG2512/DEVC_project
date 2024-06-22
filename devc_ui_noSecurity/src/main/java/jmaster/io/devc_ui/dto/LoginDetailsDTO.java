package jmaster.io.devc_ui.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDetailsDTO {

//	@NotBlank(message = "{not.blank}")
    @Size(min = 3, max = 50)
    private String username;

//	@NotBlank(message = "{not.blank}")
    @Size(min = 6)
    private String password;

   
}
