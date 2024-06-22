package devc.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devc.work.dto.RatingDTO;
import devc.work.service.RatingService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rating")
public class RatingController {
	@Autowired
	RatingService ratingService;
	
	@PostMapping("/")
	public ResponseEntity<Void> create(@RequestBody @Valid RatingDTO ratingDTO) {
		ratingService.create(ratingDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@PutMapping("/")
	public ResponseEntity<Void> update(@RequestBody @Valid RatingDTO ratingDTO) {
		ratingService.update(ratingDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		// Simulate deleting the user from a database
		ratingService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@GetMapping("/{id}")
	public ResponseEntity<RatingDTO> getById(@PathVariable("id") int id) {
		RatingDTO rating=ratingService.getById(id);
		return new ResponseEntity<>(rating, HttpStatus.OK);
	}
	@GetMapping("/searchbybookid/{id}")
	public ResponseEntity<List<RatingDTO>> searchByBookId(@PathVariable("id") int id) {
		List<RatingDTO> ratings=ratingService.searchByBookId(id);
		return new ResponseEntity<>(ratings, HttpStatus.OK);
	}
}
