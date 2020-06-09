package com.learn.online.exceptions;

public class CourseToCancellationExpiredClientSideException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CourseToCancellationExpiredClientSideException(String message) {
		super(message);
	}
	
}
