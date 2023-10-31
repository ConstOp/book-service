package telran.java48.book.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "isbn")
@Entity
@Table(name = "BOOK")
public class Book implements Serializable {

	private static final long serialVersionUID = 7072174049171144815L;

	@Id
	String isbn;
	@Column(name = "TITLE")
	String title;
//	@ManyToMany(cascade = CascadeType.ALL)
	@ManyToMany
	@JoinTable(
			name = "BOOK_AUTHORS", 
			joinColumns = @JoinColumn(name = "BOOK_ISBN"), 
			inverseJoinColumns = @JoinColumn(name = "AUTHORS_NAME")
	)
	Set<Author> authors;
	@ManyToOne
	Publisher publisher;

	public boolean removeAuthor(Author author) {
		authors.remove(author);
		return true;
	}
}
