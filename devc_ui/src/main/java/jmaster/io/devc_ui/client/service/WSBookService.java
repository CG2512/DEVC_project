package jmaster.io.devc_ui.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jmaster.io.devc_ui.dto.BookDTO;
import jmaster.io.devc_ui.dto.PageDTO;
import jmaster.io.devc_ui.dto.SearchDTO;

@FeignClient(value="bookservice",url = "http://localhost:5511")
public interface WSBookService {
	@GetMapping("/book/all")
	public ResponseEntity<PageDTO<List<BookDTO>>> searchAll(@RequestBody SearchDTO searchDTO);
}
