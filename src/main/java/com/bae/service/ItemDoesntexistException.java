package com.bae.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ItemDoesntexistException extends Exception {

	@Override
	public String toString() {
		return "The item doesn't exist according this ID";
	}

}
