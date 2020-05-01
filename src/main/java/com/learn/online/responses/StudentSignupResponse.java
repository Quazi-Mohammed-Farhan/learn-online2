package com.learn.online.responses;

public class StudentSignupResponse {

	private String studentKey;

	/**
	 * @return the studentKey
	 */
	public String getStudentKey() {
		return studentKey;
	}

	/**
	 * @param studentKey the studentKey to set
	 */
	public void setStudentKey(String studentKey) {
		this.studentKey = studentKey;
	}

	@Override
	public String toString() {
		return "StudentSignupResponse [studentKey=" + studentKey + "]";
	}

}
