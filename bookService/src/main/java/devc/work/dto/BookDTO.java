package devc.work.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BookDTO {
	
	private Integer id;
	private String name;
	private AuthorDTO author;
	@JsonFormat(pattern="dd/MM/YYYY",timezone="Asia/Ho_Chi_Minh")
	private Date datePublished;
	@JsonFormat(pattern="dd/MM/YYYY",timezone="Asia/Ho_Chi_Minh")
	private Date createdAt;
	@JsonFormat(pattern="dd/MM/YYYY",timezone="Asia/Ho_Chi_Minh")
	private Date updatedAt;
}
