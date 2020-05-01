package com.learn.online.requests;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class PlaceCourseOrderRequest {
	
	@NotBlank(message = "{student.key.mandatory}")
	private String studentKey;
	
	@NotBlank(message = "{student.course.keys.mandatory}")
	private List<String> courseKeys;

	public String getStudentKey() {
		return studentKey;
	}

	public void setStudentKey(String studentKey) {
		this.studentKey = studentKey;
	}

	public List<String> getCourseKeys() {
		return courseKeys;
	}

	public void setCourseKeys(List<String> courseKeys) {
		this.courseKeys = courseKeys;
	}

}
