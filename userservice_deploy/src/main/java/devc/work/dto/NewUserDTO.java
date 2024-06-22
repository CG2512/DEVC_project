package devc.work.dto;

import java.util.List;

import lombok.Data;

@Data
public class NewUserDTO {
	private String username;
	private String password;
	private List<RoleDTO> roles;
}
