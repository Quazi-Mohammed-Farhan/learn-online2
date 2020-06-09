package com.learn.online.exceptions;

public class InvalidCourseKeyClientSideException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidCourseKeyClientSideException(String message) {
		super(message);
	}
	
}
