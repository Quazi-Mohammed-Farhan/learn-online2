package com.learn.online.services;

/*******************************************************************************************************************
 * <h1>StudentService!</h1>																					   
 *       																										   	
 * This Student Service interface that is implemented by StudentServiceImpl. Student Service do student related 
 * CRUD operation as well as search operation. This StudentService interface extends Spring provided 
 * UserDetailService interface that is org.springframework.security.core.userdetails.UserDetailsService for 
 * Spring security.
 *                        
 * @author  Quazi Mohammed Farhan Ali.                                                                             
 * @version 1.0                        
 * @Purpose PIP Assignment to employee by Cognizant                                                                             
 * @since   2020-05-29                                                                                                                                                                                                                  
 *******************************************************************************************************************/

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.learn.online.dtos.StudentDto;

public interface StudentService extends UserDetailsService {
	
	public StudentDto signupStudent(StudentDto studentDto);
	public StudentDto updateStudent(StudentDto studentDto);
	
	public StudentDto findByEmail(String email);
	
	public StudentDto purchaseCourses(String email, List<String> courseKeys);
	public StudentDto cancellPurchasedCourses(String studentKey, List<String> courseKeys);
}
