package telran.java48.book.service;

import telran.java48.book.dto.AuthorDto;
import telran.java48.book.dto.BookDto;

public interface BookService {
	boolean addBook(BookDto bookDto);

	BookDto findBookByIsbn(String isbn);

	BookDto remove(String isbn);

	BookDto updateBook(String isbn, String title);

	Iterable<BookDto> findBooksByAuthor(String authorName);

	Iterable<BookDto> findBooksByPablisher(String pablisherName);

	Iterable<AuthorDto> findBooksAuthor(String isbn);

	Iterable<String> findPablishersByAuthor(String authorName);

	AuthorDto removeAuthor(String authorName);
}
