package jmaster.io.devc_ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jmaster.io.devc_ui.client.service.WSBookService;
import jmaster.io.devc_ui.client.service.WSRatingService;
import jmaster.io.devc_ui.dto.BookDTO;
import jmaster.io.devc_ui.dto.RatingDTO;
import jmaster.io.devc_ui.dto.SearchRatingDTO;
import jmaster.io.devc_ui.dto.UserDTO;

@Controller
@RequestMapping("/rating")
public class RatingController {
	@Autowired
	WSRatingService wsRatingService;
	@Autowired
	WSBookService wsBookService;

	@GetMapping("/ratings/{bookid}")
	public String listBooks(Model model, @ModelAttribute("searchDTO") SearchRatingDTO searchDTO,
			BindingResult bindingResult, @PathVariable("bookid") int bookid, HttpSession session) {
		// Get the current user from cookie
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		model.addAttribute("user", userDTO);
		// get the current book Id,call API to get the BookDTO object
		// searchDTO.setBookId(bookid);
		BookDTO bookDTO = wsBookService.getById(bookid).getBody();
		// get rating list
		List<RatingDTO> ratings = wsRatingService.searchByBookId(bookid).getBody();
		// add data to front end
		model.addAttribute("book", bookDTO);
		model.addAttribute("ratings", ratings);
		return "rating/ratings.html";
	}

	@GetMapping("/new/{id}") // ?id=1000
	public String newRating(@PathVariable("id") int id, Model model, HttpSession session) {
		// Display book
		BookDTO bookDTO = wsBookService.getById(id).getBody();
		// Get current user
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		// Make new ratingDTO
		//add to view
		model.addAttribute("book", bookDTO);
		model.addAttribute("user", userDTO);
		// create new RatingDTO,push to view
		return "rating/new-rating.html";
	}

	@PostMapping("/new/{id}")
	public String newRating(Model model, @RequestParam("review") @Valid String review,@PathVariable("id") int id,HttpSession session) {
		
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		BookDTO bookDTO = wsBookService.getById(id).getBody();
		RatingDTO rating=new RatingDTO();
		rating.setUser(userDTO);
		rating.setBook(bookDTO);
		rating.setReview(review);
		wsRatingService.create(rating);
		return "redirect:/rating/ratings/" + rating.getBook().getId();
	}
}
