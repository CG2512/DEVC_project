package jmaster.io.devc_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jmaster.io.devc_ui.client.service.WSUserService;
import jmaster.io.devc_ui.dto.LoginDetailsDTO;
import jmaster.io.devc_ui.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	@Autowired
	private WSUserService wsUserService;

	@GetMapping("/")
	public String mainPage() {
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		return "login.html";
	}

	@PostMapping("/login")
	public String login(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {
		/*
		 * if (bindingResult.hasErrors()) { return "login.html"; }
		 */
		try {
			LoginDetailsDTO loginDTO = new LoginDetailsDTO(username, password);
			log.info(loginDTO.getUsername());
			log.info(loginDTO.getPassword());
			UserDTO userDTO = wsUserService.loginUser(loginDTO).getBody();
			session.setAttribute("user", userDTO);
			return "redirect:/books";
		} catch (Exception e) {
			return "index.html";
		}
	}
}
