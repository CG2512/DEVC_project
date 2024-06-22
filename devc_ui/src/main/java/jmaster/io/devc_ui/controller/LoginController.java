package jmaster.io.devc_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jmaster.io.devc_ui.client.service.WSUserService;
import jmaster.io.devc_ui.dto.LoginDetailsDTO;
import jmaster.io.devc_ui.dto.LoginUserDTO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	@Autowired
	private WSUserService wsUserService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String login() {
		return "login.html";
	}
	@PostMapping("/login")
	public String login(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		/*
		 * if (bindingResult.hasErrors()) { return "login.html"; }
		 */
		
		LoginDetailsDTO loginDTO=new LoginDetailsDTO(username,password);
		log.info(loginDTO.getUsername());
		log.info(loginDTO.getPassword());
		LoginUserDTO loginUserDTO=wsUserService.loginUser(loginDTO).getBody();
		model.addAttribute("user",loginUserDTO);
		return "redirect:/book/books";
	}
}
