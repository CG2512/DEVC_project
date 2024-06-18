package devc.work.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {
	public Role(String role) {
		this.name=role;
	}
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto genererate ID,tang dan
	private Integer id;
	@Column(unique = true)
	private String name;
}
