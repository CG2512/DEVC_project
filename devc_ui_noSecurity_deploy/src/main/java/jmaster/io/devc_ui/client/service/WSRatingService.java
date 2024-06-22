package jmaster.io.devc_ui.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jmaster.io.devc_ui.dto.RatingDTO;

@FeignClient(value="ratingservice",url = "http://172.31.32.83:5512")

public interface WSRatingService {
	@GetMapping("/rating/searchbybookid/{id}")
	public ResponseEntity<List<RatingDTO>> searchByBookId(@PathVariable("id") int id);
	@PostMapping("/rating/")
	public ResponseEntity<Void> create(RatingDTO ratingDTO);
}
