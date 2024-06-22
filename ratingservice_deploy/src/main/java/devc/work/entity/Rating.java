package devc.work.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Rating extends TimeAuditable{
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/*
	 * @ManyToOne private Book book;
	 */
	private Integer bookId;
	private Integer userId; 
	private String review;
	//Add user entity/DTO later
	//private User user;
}
