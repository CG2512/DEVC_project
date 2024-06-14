package devc.work.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import devc.work.dto.BookDTO;

@FeignClient(value="bookservice",url = "http://localhost:5511")
public interface WSBookService {
	@GetMapping("/book/{id}")
	public ResponseEntity<BookDTO> getById(@PathVariable("id") Integer id);
}
