package jmaster.io.devc_ui.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jmaster.io.devc_ui.dto.PageDTO;
import jmaster.io.devc_ui.dto.RatingDTO;
import jmaster.io.devc_ui.dto.SearchRatingDTO;

@FeignClient(value="ratingservice",url = "http://localhost:5512")
public interface WSRatingService {
	@GetMapping("/rating/searchbybookid")
	public ResponseEntity<PageDTO<List<RatingDTO>>> searchByBookId(@RequestBody SearchRatingDTO searchDTO);
}
