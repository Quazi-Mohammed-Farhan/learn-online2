package com.learn.online.responses;

import java.io.Serializable;

public class LearnOnlineResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private T responseDetail;
	private String message;
	private String status;

	/**
	 * @return the responseDetail
	 */
	public T getResponseDetail() {
		return responseDetail;
	}

	/**
	 * @param responseDetail the responseDetail to set
	 */
	public void setResponseDetail(T responseDetail) {
		this.responseDetail = responseDetail;
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

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public static <T> LearnOnlineResponse<T> build() {
		LearnOnlineResponse<T> instance = new LearnOnlineResponse<T>();
		return instance;
	}
	
	public static <T> LearnOnlineResponse<T> build(T t) {
		LearnOnlineResponse<T> instance = new LearnOnlineResponse<T>();
		instance.setResponseDetail(t);
		return instance;
	}
	
	public static <T> LearnOnlineResponse<T> build(T t, String message, String status) {
		LearnOnlineResponse<T> instance = new LearnOnlineResponse<T>();
		instance.setResponseDetail(t);
		instance.setMessage(message);
		instance.setStatus(status);
		return instance;
	}

}
