package devc.work.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import devc.work.entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {
	@Query("Select b FROM Book b WHERE b.name LIKE :x")
	Page<Book> searchName(@Param("x") String name,Pageable pageable);
	//Page<Book> findByName(String s,Pageable pageable);
}
