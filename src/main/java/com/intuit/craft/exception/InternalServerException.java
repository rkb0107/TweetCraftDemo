package com.intuit.craft.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InternalServerException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InternalServerException(String message) {
		super(message);
	}
	
	public InternalServerException(String message, Throwable t) {
		super(message, t);
	}
}
