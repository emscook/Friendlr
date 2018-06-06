package com.cooksys.CustomExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class NotFoundException  extends Exception {
	private static final long serialVersionUID = 257483839L;
	public NotFoundException() { }
	@Override
	public String getMessage() {
		return "Resource not found.";
	}
}
