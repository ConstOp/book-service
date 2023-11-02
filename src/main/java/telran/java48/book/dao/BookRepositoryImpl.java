package telran.java48.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import telran.java48.book.model.Book;
import telran.java48.book.model.Publisher;

@Repository
public class BookRepositoryImpl implements BookRepository {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public Stream<Book> findByAuthorsName(String authorName) {
		return em.createQuery("select a.books from Author a where a.name=?1")
				.setParameter(1, authorName)
				.getResultStream();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Stream<Book> findByPublisherPublisherName(String publisherName) {
		return em.createQuery("select p.books from Publisher p where p.publisherName=?1")
				.setParameter(1, publisherName)
				.getResultStream();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Stream<Publisher> findPublishersByAuthorsName(String authorName) {
		return em.createQuery("select b.publisher from Book b join b.authors a where a.name = ?1 group by b.publisher")
		.setParameter(1, authorName)
		.getResultStream();
	}

	@Override
	public boolean existsById(String isbn) {
		return em.find(Book.class, isbn) != null;
	}

	@Override
//	@Transactional
	public Book save(Book book) {
		em.persist(book);
		return book;
	}

	@Override
	public Optional<Book> findById(String isbn) {
		return Optional.ofNullable(em.find(Book.class, isbn));
	}

	@Override
	public void deleteById(String isbn) {
		em.remove(em.find(Book.class, isbn));
	}

}
