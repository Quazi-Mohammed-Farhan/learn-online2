package com.learn.online.responses;

import java.io.Serializable;

public class StudentResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String studentKey;
	

	public String getStudentKey() {
		return studentKey;
	}

	public void setStudentKey(String studentKey) {
		this.studentKey = studentKey;
	}

	@Override
	public String toString() {
		return "StudentSignupResponse [studentKey=" + studentKey + "]";
	}
}
