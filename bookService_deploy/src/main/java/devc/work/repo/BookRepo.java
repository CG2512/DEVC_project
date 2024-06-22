package devc.work.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import devc.work.entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {
	@Query("Select b FROM Book b WHERE b.name LIKE :x")
	List<Book> searchName(@Param("x") String name);
	//Page<Book> searchName(@Param("x") String name,Pageable pageable);
	//Page<Book> findByName(String s,Pageable pageable);
	List<Book> findAll();
}
