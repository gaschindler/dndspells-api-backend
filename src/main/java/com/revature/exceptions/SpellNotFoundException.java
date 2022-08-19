package com.revature.exceptions;

public class SpellNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SpellNotFoundException(String message) {
		super(message);
	}
	
}
