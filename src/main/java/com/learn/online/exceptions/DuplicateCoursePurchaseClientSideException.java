package com.learn.online.exceptions;

public class DuplicateCoursePurchaseClientSideException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DuplicateCoursePurchaseClientSideException(String message) {
		super(message);
	}
	
}
