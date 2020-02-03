package com.bae.service;

public class ItemIsNotInFreezerException extends Exception {

	@Override
	public String toString() {
		return "Item is not in this freezer";
	}

}
