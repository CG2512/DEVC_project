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

import devc.work.dto.BookDTO;
import devc.work.service.AuthorService;
import devc.work.service.BookService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	AuthorService authorService;
	@Autowired
	BookService bookService;
	@PostMapping("/")
	public ResponseEntity<Void> create(@RequestBody @Valid BookDTO bookDTO) {
		bookService.create(bookDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/")
	public ResponseEntity<Void> update(@RequestBody @Valid BookDTO bookDTO) {
		bookService.update(bookDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		// Simulate deleting the user from a database
		bookService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> getById(@PathVariable("id") int id) {
		BookDTO book=bookService.getById(id);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<List<BookDTO>> getAll() {
		List<BookDTO> books=bookService.getAll();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
}
