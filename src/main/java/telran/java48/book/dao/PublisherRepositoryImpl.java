package telran.java48.book.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import telran.java48.book.model.Publisher;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {
	
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findByPublishersByAuthor(String authorName) {
		return em.createQuery("select b.publisher.publisherName from Book b join b.authors a where a.name = ?1 group by b.publisher")
				.setParameter(1, authorName)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Stream<Publisher> findDistinctByBooksAuthorsName(String authorName) {
		return em.createQuery("select distinct p from Book b join b.authors a join b.publisher p where a.name=?1")
				.setParameter(1, authorName)
				.getResultStream();
	}

	@Override
	public Optional<Publisher> findById(String publisher) {
		return Optional.ofNullable(em.find(Publisher.class, publisher));
	}

	@Override
	public Publisher save(Publisher publisher) {
		em.persist(publisher);
//		em.merge(publisher);
		return publisher;
	}

}
