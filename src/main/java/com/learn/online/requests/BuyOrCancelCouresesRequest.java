package com.learn.online.requests;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BuyOrCancelCouresesRequest {

	@Email(message ="{email.is.not.valid}", regexp = ".+@.+\\.[a-z]+")
	@NotBlank(message = "{email.mandatory}")
	private String studentEmail;
	
	@NotEmpty(message = "{min.course.required}")
	private List<@Size(max=64, min=64, message = "{invalid.token.course.size}") 
				 @NotBlank(message = "{blank.course.token}")String> courseKeys;

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public List<String> getCourseKeys() {
		return courseKeys;
	}

	public void setCourseKeys(List<String> courseKeys) {
		this.courseKeys = courseKeys;
	}

}
