package devc.work.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import devc.work.dto.AuthorDTO;
import devc.work.dto.BookDTO;
import devc.work.dto.PageDTO;
import devc.work.dto.SearchDTO;
import devc.work.entity.Author;
import devc.work.entity.Book;
import devc.work.repo.BookRepo;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

public interface BookService {
	void create(BookDTO bookDTO);

	void updateName(BookDTO bookDTO);

	void update(BookDTO bookDTO);

	void delete(int id);

	BookDTO getById(int id);

	PageDTO<List<BookDTO>> getAll();

	PageDTO<List<BookDTO>> search(SearchDTO searchDTO);
}

@Service
class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private AuthorService authorService;

	@Transactional
	@Override
	public void create(BookDTO bookDTO) {
		// TODO Auto-generated method stub
		Book book = new ModelMapper().map(bookDTO, Book.class);
		bookRepo.save(book);
		bookDTO.setId(book.getId());
	}

	@Transactional
	@Override
	public void update(BookDTO bookDTO) {
		Book book = bookRepo.findById(bookDTO.getId()).orElseThrow(NoResultException::new);
		// Update book name,author,date published
		book.setName(bookDTO.getName());
		AuthorDTO authorDTO = authorService.getById(bookDTO.getAuthor().getId());
		Author author = new ModelMapper().map(authorDTO, Author.class);
		book.setAuthor(author);
		book.setDatePublished(bookDTO.getDatePublished());
		bookRepo.save(book);
	}

	@Transactional
	@Override
	public void delete(int id) {
		bookRepo.deleteById(null);
	}

	@Override
	public BookDTO getById(int id) {
		// TODO Auto-generated method stub
		Book book = bookRepo.findById(id).orElseThrow(NoResultException::new);
		return convert(book);
	}

	@Override
	public PageDTO<List<BookDTO>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

//Usable for both searching books based on specific attributes and get all
	@Override
	public PageDTO<List<BookDTO>> search(SearchDTO searchDTO) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Sort sortBy = Sort.by("name").ascending();
		// sap xep du lieu trong page theo thu tu thuoc tinh

		if (StringUtils.hasText(searchDTO.getSortedField())) {
			sortBy = Sort.by(searchDTO.getSortedField()).ascending();
		}

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
		Page<Book> page = bookRepo.searchName("%" + searchDTO.getKeyword() + "%", pageRequest);

		PageDTO<List<BookDTO>> pageDTO = new PageDTO();
		pageDTO.setTotalPages(page.getTotalPages());
		pageDTO.setTotalElements(page.getTotalElements());

		List<BookDTO> bookDTOs = page.get().map(u -> convert(u)).collect(Collectors.toList());
		// T: List<UserDTO>
		pageDTO.setData(bookDTOs);
		return pageDTO;
	}
	private BookDTO convert(Book book) {

		return new ModelMapper().map(book, BookDTO.class);
	}
	@Override
	public void updateName(BookDTO bookDTO) {
		// TODO Auto-generated method stub

	}

}