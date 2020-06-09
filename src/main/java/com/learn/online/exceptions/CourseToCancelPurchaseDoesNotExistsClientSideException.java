package com.learn.online.exceptions;

public class CourseToCancelPurchaseDoesNotExistsClientSideException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CourseToCancelPurchaseDoesNotExistsClientSideException(String message) {
		super(message);
	}
	
}
