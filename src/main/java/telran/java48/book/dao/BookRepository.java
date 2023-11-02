package telran.java48.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;

import telran.java48.book.model.Author;
import telran.java48.book.model.Book;
import telran.java48.book.model.Publisher;

public interface BookRepository {
	Stream<Book> findByAuthorsName(String authorName);

	Stream<Book> findByPublisherPublisherName(String publisherName);

//	@Query("select b.publisher from Book b join b.authors a where a.name = ?1 group by b.publisher")
	Stream<Publisher> findPublishersByAuthorsName(String authorName);

	boolean existsById(String isbn);

	Book save(Book book);

	Optional<Book> findById(String isbn);

	void deleteById(String isbn);

}
