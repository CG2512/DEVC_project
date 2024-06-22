package jmaster.io.devc_ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import jmaster.io.devc_ui.client.service.WSBookService;
import jmaster.io.devc_ui.client.service.WSUserService;
import jmaster.io.devc_ui.dto.BookDTO;
import jmaster.io.devc_ui.dto.UserDTO;

@Controller

public class BookController {
	@Autowired
	WSBookService wsBookService;
	@Autowired
	WSUserService wsUserService;
	
	@GetMapping("/books")
	public String listBooks(Model model,HttpSession session) {
		//Get the current user from cookie
		UserDTO userDTO=(UserDTO) session.getAttribute("user");
		model.addAttribute("user",userDTO);
		//Get all books in the database
		List<BookDTO> books=wsBookService.getAll().getBody();
		//add data to frontend
		model.addAttribute("books", books);
		/*
		 * model.addAttribute("totalPage",pageBook.getTotalPages());
		 * model.addAttribute("totalElements", pageBook.getTotalElements());
		 * model.addAttribute("searchDTO", searchDTO);
		 */
		return "book/books.html";
	}
}
