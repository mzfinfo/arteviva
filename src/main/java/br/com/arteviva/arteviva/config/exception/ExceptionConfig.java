package br.com.arteviva.arteviva.config.exception;

import java.io.Serializable;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.arteviva.arteviva.exception.ObjectNotFoundException;

// Esta anotação faz com que essa classe passe a interceptar
// eventos de REST e inclusive a exceção. 
@RestControllerAdvice
public class ExceptionConfig  {

	/**
	 * Este método fará o tratamento para os casos em que ocorrer de não identificar
	 * um recurso na base de dados.
	 * 
	 * @param ex
	 * @return
	 */
	// Mapeia para as exceções que indicam ausência de recurso na base de dados.
	@ExceptionHandler({ EmptyResultDataAccessException.class, ObjectNotFoundException.class })
	public ResponseEntity<?> errorNotFound(Exception ex) {

		return ResponseEntity.notFound().build();

	}

	// Mapeia para as exceções que indicam problemas com a camada de negócio como,
	// por exemplo, não enviar os campos corretos do payload, quando deseja-se criar
	// um recurso.
	@ExceptionHandler({ IllegalArgumentException.class, MethodArgumentNotValidException.class })
	public ResponseEntity<?> errorBadRequest(Exception ex) {

		return ResponseEntity.badRequest().build();

	}

	// Mapeia para as exceções que indicam uso incorreto dos métodos  HTTP.
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	public ResponseEntity<?> errorMethodNotAllowed(Exception ex) {

		return new ResponseEntity<>(new ExceptionError("Esta operação não é permitida para este método HTTP."),
				HttpStatus.METHOD_NOT_ALLOWED);

	}
	

}

class ExceptionError implements Serializable {

	private static final long serialVersionUID = -7257069311757837875L;

	private String mensagem;

	public ExceptionError(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

}
