package com.learn.online.services;

import java.util.List;

import com.learn.online.dtos.StudentDto;

public interface StudentService  {
	
	public StudentDto signupStudent(StudentDto studentDto);
	public StudentDto updateStudent(StudentDto studentDto);
	
	public StudentDto findByEmail(String email);
	
	public StudentDto purchaseCourses(String email, List<String> courseKeys);
	public StudentDto cancellPurchasedCourses(String studentKey, List<String> courseKeys);
}
