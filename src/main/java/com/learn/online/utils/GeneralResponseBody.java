package com.learn.online.utils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class GeneralResponseBody implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date localDate;
	private String message;
	
	public Date getLocalDate() {
		return localDate;
	}
	public void setLocalDate(Date localDate) {
		this.localDate = localDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "GeneralResponseBody [localDate=" + localDate + ", message=" + message + "]";
	}
	
}
