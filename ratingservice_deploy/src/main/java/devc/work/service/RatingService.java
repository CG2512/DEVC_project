package devc.work.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devc.work.client.dto.BookDTO;
import devc.work.client.dto.UserDTO;
import devc.work.client.service.WSBookService;
import devc.work.client.service.WSUserService;
import devc.work.dto.RatingDTO;
import devc.work.entity.Rating;
import devc.work.repository.RatingRepo;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

public interface RatingService {
	void create(RatingDTO ratingDTO);

	void update(RatingDTO ratingDTO);

	void delete(int id);

	RatingDTO getById(int id);

	List<RatingDTO> searchByBookId(int id);
}

@Service
class RatingServiceImpl implements RatingService {
	@Autowired
	private RatingRepo ratingRepo;
	@Autowired
	private WSBookService wsBookService;
	@Autowired
	private WSUserService wsUserService;

	@Transactional
	@Override
	public void create(RatingDTO ratingDTO) {
		// ModelMapper configuration
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		// Check if the book exists or not
		BookDTO bookDTO = wsBookService.getById(ratingDTO.getBook().getId()).getBody();
		// Check if user exists or not
		UserDTO userDTO = wsUserService.getById(ratingDTO.getUser().getId()).getBody();

		// Convert DTO to entity and save it
		Rating rating = modelMapper.map(ratingDTO, Rating.class);
		rating.setBookId(bookDTO.getId());
		rating.setUserId(userDTO.getId());
		ratingRepo.save(rating);
		ratingDTO.setId(rating.getId());
	}

	@Transactional
	@Override
	public void update(RatingDTO ratingDTO) {
		// TODO Auto-generated method stub
		Rating rating = ratingRepo.findById(ratingDTO.getId()).orElseThrow(NoResultException::new);
		rating.setReview(ratingDTO.getReview());
		ratingRepo.save(rating);
	}

	@Transactional
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		ratingRepo.deleteById(id);
	}

	@Override
	public RatingDTO getById(int id) {
		// TODO Auto-generated method stub
		Rating rating = ratingRepo.findById(id).orElseThrow(NoResultException::new);
		return convert(rating);
	}

	@Override
	public List<RatingDTO> searchByBookId(int id) {

		List<Rating> ratings = ratingRepo.searchByBookId(id);
		List<RatingDTO> ratingDTOs = ratings.stream().map(u -> convert(u)).collect(Collectors.toList());
		// T: List<UserDTO>

		return ratingDTOs;
	}

	private RatingDTO convert(Rating rating) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		// Check if book exist or not
		/*
		 * Integer bookId=rating.getBookId(); BookDTO
		 * bookDTO=wsBookService.getById(bookId).getBody(); /*
		 * ratingDTO.setBook(bookDTO);
		 */
		// Get username from user
		UserDTO userDTO = wsUserService.getById(rating.getUserId()).getBody();
		RatingDTO ratingDTO = modelMapper.map(rating, RatingDTO.class);
		ratingDTO.setUser(userDTO);
		return ratingDTO;

	}
}
