package jmaster.io.devc_ui.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jmaster.io.devc_ui.dto.BookDTO;

@FeignClient(value="bookservice",url = "http://172.31.45.234:5511")
public interface WSBookService {
	@GetMapping("/book/all")
	public ResponseEntity<List<BookDTO>> getAll();
	@GetMapping("/book/{id}")
	public ResponseEntity<BookDTO> getById(@PathVariable("id") int id);
}
