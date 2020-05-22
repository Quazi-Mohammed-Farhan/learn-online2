package com.learn.online.enums;

public enum ErrorMessagesEnum {
	
	REQUESTED_COURSES_NOT_FOUND("Requested courses are not found. Go to our App portal and see the available courses."),
	REQUESTED_STUDENT_NOT_FOUND("Requested Student is not found. Please make sure you entered your registered primary email properly."),
	All_REQUESTED_STUDENT_NOT_FOUND("All Requested Courses are not found. Which are {}."),
	REQUESTED_COURSES_NOT_FOUND_FOR_PURCHASE("Requested courses are not found For Purchase. Go to our App portal and see the available courses."),
	REQUESTED_COURSES_NOT_FOUND_FOR_DELETE("Requested courses are not found For Deletion. Please contact to call center."),
	INVALID_COURSES_ORDERS_KEYS_FOR_DELETION("Courses Cancellation request not allowed because courses keys %s are Invalid."),
	COURSES_EXCEED_30DAYS_CAN_NOT_BE_DELETED("Courses Cancellation request not allowed because courses keys %s exceeds 30 days limits."),
	EMPTY_COURSES_LIST("You did not purchase any course then how can you cancel purcahsed courses"),
	BUYING_DUPLICATE_COURSES("Your are purchasing same courses again %s. Please remove duplicate course entries"),
	DUPLICATE_STUDENT_ENTRY("You are already registered student. Student is identified by email id"),
	STUDENT_LOGIN_FAILED("Student login failed. Please provide valid credentials.");
	
	
	private String message;
	
	
	ErrorMessagesEnum(String message) {
		this.message=message;
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
	
	
}
