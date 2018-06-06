package com.cooksys.CustomExceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class NotFoundHandler {
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NotFoundException.class })
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
		 return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
}
