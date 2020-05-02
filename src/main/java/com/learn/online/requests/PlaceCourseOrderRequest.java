package com.learn.online.requests;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PlaceCourseOrderRequest {
	
	@NotBlank(message = "{student.key.mandatory}")
	private String studentKey;
	
	@NotEmpty(message = "{student.course.keys.mandatory}")
	private List<@Size(max=64, min=64, message = "{invalid.token.course.size}") 
	    		 @NotBlank(message = "{blank.course.token}") String> courseKeys;

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
