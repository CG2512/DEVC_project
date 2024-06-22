package jmaster.io.devc_ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.validation.Valid;
import jmaster.io.devc_ui.client.service.WSBookService;
import jmaster.io.devc_ui.dto.BookDTO;
import jmaster.io.devc_ui.dto.PageDTO;
import jmaster.io.devc_ui.dto.SearchDTO;

@Controller
public class BookController {
	@Autowired
	WSBookService wsBookService;

	@GetMapping("/books")
	public String listBooks(Model model, @ModelAttribute @Valid SearchDTO searchDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			PageDTO<List<BookDTO>> books = wsBookService.searchAll(searchDTO).getBody();
			model.addAttribute("books", books);
			return "book/books.html"; // khi co loi thi tra view(se bi mat du lieu)
		}
		PageDTO<List<BookDTO>> books = wsBookService.searchAll(searchDTO).getBody();
		model.addAttribute("books", books);
		return "book/books.html";
	}
}
