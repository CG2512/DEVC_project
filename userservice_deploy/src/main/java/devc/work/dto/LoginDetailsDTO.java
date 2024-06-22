package devc.work.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDetailsDTO {

	@NotBlank(message = "{not.blank}")
    @Size(min = 3, max = 50)
    private String username;

	@NotBlank(message = "{not.blank}")
    @Size(min = 6)
    private String password;

   
}
