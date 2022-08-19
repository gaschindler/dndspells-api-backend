package com.revature.exceptions;

public class SchoolNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SchoolNotFoundException(String message) {
		super(message);
	}
	
}
