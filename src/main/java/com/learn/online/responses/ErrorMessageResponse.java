package com.learn.online.responses;

import java.time.LocalDate;

public class ErrorMessageResponse {

	private LocalDate localDate;
	private String message;

	
	public ErrorMessageResponse() {
		
	}
	
	public ErrorMessageResponse(LocalDate localDate, String message) {
		this.localDate = localDate;
		this.message = message;
	}
	
	/**
	 * @return the localDate
	 */
	public LocalDate getLocalDate() {
		return localDate;
	}

	/**
	 * @param localDate the localDate to set
	 */
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorMessageResponse [localDate=" + localDate + ", message=" + message + "]";
	}

}
