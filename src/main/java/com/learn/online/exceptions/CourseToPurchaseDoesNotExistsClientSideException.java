package com.learn.online.exceptions;

public class CourseToPurchaseDoesNotExistsClientSideException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CourseToPurchaseDoesNotExistsClientSideException(String message) {
		super(message);
	}
	
}
