package devc.work.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import devc.work.client.service.WSBookService;
import devc.work.dto.BookDTO;
import devc.work.dto.PageDTO;
import devc.work.dto.RatingDTO;
import devc.work.dto.SearchRatingDTO;
import devc.work.entity.Rating;
import devc.work.repository.RatingRepo;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

public interface RatingService {
	void create(RatingDTO ratingDTO);

	void update(RatingDTO ratingDTO);

	void delete(int id);

	RatingDTO getById(int id);

	PageDTO<List<RatingDTO>> searchByBookId(SearchRatingDTO searchRatingDTO);
}

@Service
class RatingServiceImpl implements RatingService{
	@Autowired
	private RatingRepo ratingRepo;
	@Autowired
	private WSBookService wsBookService;
	
	@Transactional
	@Override
	public void create(RatingDTO ratingDTO) {
		//ModelMapper configuration
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		// Check if the book exists or not
		BookDTO bookDTO=wsBookService.getById(ratingDTO.getBook().getId()).getBody();
		//Convert DTO to entity and save it
		Rating rating = modelMapper.map(ratingDTO, Rating.class);
		rating.setBookId(bookDTO.getId());
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
		ratingRepo.deleteById(null);
	}

	@Override
	public RatingDTO getById(int id) {
		// TODO Auto-generated method stub
		Rating rating = ratingRepo.findById(id).orElseThrow(NoResultException::new);
		return convert(rating);
	}

	@Override
	public PageDTO<List<RatingDTO>> searchByBookId(SearchRatingDTO searchDTO) {
		Sort sortBy = Sort.by("review").descending();
		// sap xep du lieu trong page theo thu tu thuoc tinh

		if (searchDTO.getCurrentPage() == null) {
			searchDTO.setCurrentPage(0);
		}

		if (searchDTO.getSize() == null) {
			searchDTO.setSize(10);
		}

		if (searchDTO.getKeyword() == null) {
			searchDTO.setKeyword("");
		}

		PageRequest pageRequest = PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getSize(), sortBy);
		Page<Rating> page = ratingRepo.searchByBookId(searchDTO.getBookId(), pageRequest);

		PageDTO<List<RatingDTO>> pageDTO = new PageDTO();
		pageDTO.setTotalPages(page.getTotalPages());
		pageDTO.setTotalElements(page.getTotalElements());

		List<RatingDTO> ratingDTOs = page.get().map(u -> convert(u)).collect(Collectors.toList());
		// T: List<UserDTO>
		pageDTO.setData(ratingDTOs);
		return pageDTO;
	}
	private RatingDTO convert(Rating rating) {
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		//Check if book exist or not
		/*
		 * Integer bookId=rating.getBookId(); BookDTO
		 * bookDTO=wsBookService.getById(bookId).getBody();
		 */
		RatingDTO ratingDTO=modelMapper.map(rating,RatingDTO.class);
		/* ratingDTO.setBook(bookDTO); */
		return ratingDTO;
		
	}
}
