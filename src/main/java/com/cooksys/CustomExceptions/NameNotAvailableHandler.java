package com.cooksys.CustomExceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class NameNotAvailableHandler {
	@ExceptionHandler({ NameNotAvailableException.class })
	public ResponseEntity<Object> handleNameNotAvailableException(NameNotAvailableException ex, WebRequest request) {
		 return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
		
	}
}
