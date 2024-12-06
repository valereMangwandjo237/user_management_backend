package tech.chillo.sa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExpiredTokenException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	public ExpiredTokenException (String message){
		super(message);

	}
}
