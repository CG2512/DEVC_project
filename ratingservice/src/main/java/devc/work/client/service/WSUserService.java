package devc.work.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import devc.work.client.dto.UserDTO;

@FeignClient(value="userservice",url = "http://localhost:5513")
public interface WSUserService {
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable("id") int id);
}
