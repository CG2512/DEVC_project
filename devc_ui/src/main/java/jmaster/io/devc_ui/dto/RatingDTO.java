package jmaster.io.devc_ui.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RatingDTO {
	private Integer id;
	@NotBlank
	private String review;
	private BookDTO book;
	private UserDTO user;
	@JsonFormat(pattern = "dd/MM/YYYY", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	@JsonFormat(pattern = "dd/MM/YYYY", timezone = "Asia/Ho_Chi_Minh")
	private Date updatedAt;
}
