package com.bae.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FreezerDoesntexistException extends Exception {

	@Override
	public String toString() {
		return "The freezer doesn't exist according this ID";
	}

}
