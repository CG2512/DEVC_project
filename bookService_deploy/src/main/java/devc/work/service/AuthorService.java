package devc.work.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devc.work.dto.AuthorDTO;
import devc.work.dto.PageDTO;
import devc.work.dto.SearchDTO;
import devc.work.entity.Author;
import devc.work.repo.AuthorRepo;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

public interface AuthorService {
	void create(AuthorDTO authorDTO);

	void update(AuthorDTO authorDTO);

	void delete(int id);

	AuthorDTO getById(int id);

	List<AuthorDTO> getAll();

	PageDTO<List<AuthorDTO>> search(SearchDTO searchDTO);
}

@Service
class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorRepo authorRepo;

	@Transactional
	@Override
	public void create(AuthorDTO authorDTO) {
		// TODO Auto-generated method stub
		Author author = new ModelMapper().map(authorDTO, Author.class);
		authorRepo.save(author);
		authorDTO.setId(author.getId());
	}

	@Transactional
	@Override
	public void update(AuthorDTO authorDTO) {
		// TODO Auto-generated method stub
		Author author = authorRepo.findById(authorDTO.getId()).orElseThrow(NoResultException::new);
		author.setName(authorDTO.getName());
		authorRepo.save(author);
	}

	@Transactional
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		authorRepo.deleteById(id);
	}

	@Override
	public AuthorDTO getById(int id) {
		// TODO Auto-generated method stub
		Author author = authorRepo.findById(id).orElseThrow(NoResultException::new);
		return convert(author);
	}

	@Override
	public List<AuthorDTO> getAll() {
		// TODO Auto-generated method stub
		List<Author> authors=authorRepo.findAll();
		List<AuthorDTO> authorDTOs = authors.stream().map(u -> convert(u)).collect(Collectors.toList());
		return authorDTOs;
	}

	@Override
	public PageDTO<List<AuthorDTO>> search(SearchDTO searchDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	private AuthorDTO convert(Author author) {

		return new ModelMapper().map(author, AuthorDTO.class);
	}

}