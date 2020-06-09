package com.learn.online.exceptions;

public class InvalidCourseOrderKeyClientSideException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidCourseOrderKeyClientSideException(String message) {
		super(message);
	}
	
}
