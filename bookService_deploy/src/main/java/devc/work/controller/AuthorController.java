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

import devc.work.dto.AuthorDTO;
import devc.work.service.AuthorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/author")
public class AuthorController {
	@Autowired
	AuthorService authorService;

	@PostMapping("/")
	public ResponseEntity<Void> create(@RequestBody @Valid AuthorDTO authorDTO) {
		authorService.create(authorDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/")
	public ResponseEntity<Void> update(@RequestBody @Valid AuthorDTO authorDTO) {
		authorService.update(authorDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		// Simulate deleting the user from a database
		authorService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("id") int id) {
		AuthorDTO author=authorService.getById(id);
		return new ResponseEntity<>(author, HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<List<AuthorDTO>> getAll() {
		List<AuthorDTO> authors=authorService.getAll();
		return new ResponseEntity<>(authors, HttpStatus.OK);
	}
}
