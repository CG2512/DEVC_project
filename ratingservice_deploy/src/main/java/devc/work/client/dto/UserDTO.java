package devc.work.client.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
	private Integer id;
	private String username;
	private List<RoleDTO> roles;
}
