package jmaster.io.devc_ui.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import jmaster.io.devc_ui.dto.LoginDetailsDTO;
import jmaster.io.devc_ui.dto.UserDTO;

@FeignClient(value="userservice",url = "http://localhost:5513")
public interface WSUserService {
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable("id") int id);
	@PostMapping("/login")
	public ResponseEntity<UserDTO> loginUser(@Valid @RequestBody LoginDetailsDTO loginDetailsDTO);
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody LoginDetailsDTO loginDetailsDTO);
}
