package com.learn.online.exceptions;

public class CoursePurchaseCancellationClientSideException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CoursePurchaseCancellationClientSideException(String message) {
		super(message);
	}
	
}
