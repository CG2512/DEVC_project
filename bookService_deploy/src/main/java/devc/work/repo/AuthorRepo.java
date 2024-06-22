package devc.work.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import devc.work.entity.Author;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
	Author findByName(String name);
	List<Author> findAll();
}
