package devc.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devc.work.dto.LoginDetailsDTO;
import devc.work.dto.LoginUserDTO;
import devc.work.dto.NewUserDTO;
import devc.work.dto.UserDTO;
import devc.work.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired // DI
	UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody LoginDetailsDTO loginDetailsDTO) {
		userService.register(loginDetailsDTO);
		return ResponseEntity.ok("User registered successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<LoginUserDTO> loginUser(@Valid @RequestBody LoginDetailsDTO loginDetailsDTO) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDetailsDTO.getUsername(), loginDetailsDTO.getPassword()));
		LoginUserDTO loginUserDTO = userService.loadUserByUsername(loginDetailsDTO.getUsername());
		return new ResponseEntity<>(loginUserDTO, HttpStatus.OK);
	}

	@PostMapping("/new")
	public ResponseEntity<?> create(@Valid @RequestBody NewUserDTO userDTO) {
		userService.create(userDTO);
		return ResponseEntity.ok("User Created!");
	}
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable("id") int id) {
		UserDTO userDTO=userService.getById(id);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
}
