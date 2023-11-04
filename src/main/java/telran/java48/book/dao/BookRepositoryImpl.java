package telran.java48.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import telran.java48.book.model.Book;
import telran.java48.book.model.Publisher;

@Repository
public class BookRepositoryImpl implements BookRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public Stream<Book> findByAuthorsName(String authorName) {
		TypedQuery<Book> query = em.createQuery("select b from Book b join b.authors where a.name=?1", Book.class);
		query.setParameter(1, authorName);
		return query.getResultStream();
	}

	@Override
	public Stream<Book> findByPublisherPublisherName(String publisherName) {
		TypedQuery<Book> query = em.createQuery("select p.books from Publisher p where p.publisherName=?1", Book.class)
				.setParameter(1, publisherName);
		return query.getResultStream();
	}

	@Override
	public Stream<Publisher> findPublishersByAuthorsName(String authorName) {
		TypedQuery<Publisher> query = em
				.createQuery("select b.publisher from Book b join b.authors a where a.name = ?1 group by b.publisher",
						Publisher.class)
				.setParameter(1, authorName);
		return query.getResultStream();
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
