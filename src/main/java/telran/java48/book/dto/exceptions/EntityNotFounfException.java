package telran.java48.book.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFounfException extends RuntimeException {

	private static final long serialVersionUID = -7818776579345158639L;

}
