package com.learn.online.enums;

public enum ResponseMessages {
	DATA_FOUND("Data Found"),
	DATA_NOT_FOUND("Data not found"),
	COURSES_BY_TECHNOLOGY_FOUND("Required courses are available"),
	COURSES_BY_TECHNOLOGY_NOT_FOUND("Required courses are not available"),
	STUDENT_ADD_OPERATION_SUCCESSFUL("Student added/signup is successfully"),
	STUDENT_ADD_OPERATION_FAILED("Student add/signup is unsuccessfull"),
	STUDENT_UPDATE_OPERATION_SUCCESSFUL("Student update is successfull"),
	STUDENT_DELETE_OPERATION_FAILED("Student update is unsuccessfull"),
	COURSES_DELETE_OPERATION_SUCCESS("Courses canncellation request proccessed sucessfully"),
	COURSES_BUY_OPERATION_SUCCESS("Your courses purchased order have been placed successfully"),
	COURSES_SEARCH_BY_DOMAIN_RATING("Courses search by domain and rating is successful");
	
	
	
	private String responseMessage;
	
	private ResponseMessages(String responseMessage) {
		this.responseMessage = responseMessage; 
	}

	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @param responseMessage the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	
	
}
