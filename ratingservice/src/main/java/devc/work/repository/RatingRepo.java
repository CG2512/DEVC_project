package devc.work.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import devc.work.entity.Rating;

public interface RatingRepo extends JpaRepository<Rating, Integer>{
	@Query("Select r FROM Rating r WHERE r.bookId=:bid")
	Page<Rating> searchByBookId(@Param("bid") Integer id,Pageable pageable);
}
