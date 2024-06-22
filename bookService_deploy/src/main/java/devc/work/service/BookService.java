package devc.work.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devc.work.dto.AuthorDTO;
import devc.work.dto.BookDTO;
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

	List<BookDTO> getAll();

	
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
	public List<BookDTO> getAll() {
		// TODO Auto-generated method stub
		List<Book> books=bookRepo.findAll();
		List<BookDTO> bookDTOs=books.stream().map(u -> convert(u)).collect(Collectors.toList());
		return bookDTOs;
	}



	private BookDTO convert(Book book) {

		return new ModelMapper().map(book, BookDTO.class);
	}

	@Override
	public void updateName(BookDTO bookDTO) {
		// TODO Auto-generated method stub

	}

}