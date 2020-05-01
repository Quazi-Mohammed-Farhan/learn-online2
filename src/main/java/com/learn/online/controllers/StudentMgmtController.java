package com.learn.online.controllers;


import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.online.dtos.StudentDto;
import com.learn.online.enums.ResponseMessages;
import com.learn.online.enums.ResponseStatus;
import com.learn.online.requests.BuyOrCancelCouresesRequest;
import com.learn.online.requests.StudentSignupRequest;
import com.learn.online.requests.StudentUpdateRequest;
import com.learn.online.responses.CoursesResponse;
import com.learn.online.responses.LearnOnlineResponse;
import com.learn.online.responses.StudentDetailResponse;
import com.learn.online.responses.StudentResponse;
import com.learn.online.responses.StudentSignupResponse;
import com.learn.online.services.StudentService;
import com.learn.online.utils.CustomUtils;


/*
 * TODO: Logging part is remaining
 */

@RestController
@Validated
public class StudentMgmtController {

	@Autowired
	private StudentService studentService;

	/*
	 * TODO: Not completed in reality. It is just Sample code that 
	 * fetches hard-code available courses for time being
	 */
	@GetMapping(value = "/learn")
	public LearnOnlineResponse<List<CoursesResponse>> welcome() {
		return LearnOnlineResponse.build(CustomUtils.generateWelcomeResponse(), "Available Courses", "SUCCESS");
	}
	
	
	/*
	 *TODO: Completed.
	 * 1- Validation part is over
	 * 2- Happy path and unit testing are done.  
	 * 3- Once again it will be tested for assurance.
	 *  
	 */
	
	@GetMapping(value = "/learn/search/{email}")
	public LearnOnlineResponse<StudentDetailResponse> searchByEmail(
				@PathVariable @Email(message = "{email.mandatory}") String email) {
		
		StudentDto studentDto = studentService.findByEmail(email);
		StudentDetailResponse studentDetailResponse = new StudentDetailResponse(studentDto);
		
		return LearnOnlineResponse.build(studentDetailResponse, 
				ResponseMessages.DATA_FOUND.getResponseMessage(), ResponseStatus.SUCCESS.name());
	}
	
	/*
	 *TODO: Completed.
	 * 1- Validation part is over
	 * 2- Happy path and unit testing are done.  
	 * 3- Once again it will be tested for assurance.
	 */
	@PostMapping(value = "/learn", 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public LearnOnlineResponse<StudentSignupResponse> createStudent(
			@Valid @RequestBody StudentSignupRequest studentSignupRequest) {
		
		StudentDto studentDto = new StudentDto();
		BeanUtils.copyProperties(studentSignupRequest, studentDto);
		studentDto.setEncryptedPassword(studentSignupRequest.getPassword());
		studentDto = studentService.signupStudent(studentDto);

		StudentSignupResponse studentSignupResponse = new StudentSignupResponse();
		studentSignupResponse.setStudentKey(studentDto.getStudentKey());

		return LearnOnlineResponse.build(studentSignupResponse, 
					ResponseMessages.STUDENT_ADD_OPERATION_SUCCESSFUL.getResponseMessage(), 
					ResponseStatus.SUCCESS.name());
	}
	
	/*
	 *TODO: Completed.
	 * 1- Validation part is over
	 * 2- Happy path and unit testing are done.  
	 * 3- Once again it will be tested for assurance.
	 */
	@PutMapping(value = "/learn", 
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public LearnOnlineResponse<StudentResponse> updateStudent(@Valid
				@RequestBody StudentUpdateRequest studentUpdateRequest) {

		StudentDto studentDto = new StudentDto();
		BeanUtils.copyProperties(studentUpdateRequest, studentDto);
		studentDto.setEncryptedPassword(studentUpdateRequest.getPassword());
		studentDto.setLastUpdateDate(LocalDate.now());

		LocalDate currentDate = LocalDate.now();
		studentDto.setLastUpdateDate(currentDate);
		studentService.updateStudent(studentDto);
		StudentResponse studentUpdateResponse = new StudentResponse();

		studentUpdateResponse.setStudentKey(studentDto.getStudentKey());
		
		return LearnOnlineResponse.build(studentUpdateResponse, 
				ResponseMessages.STUDENT_UPDATE_OPERATION_SUCCESSFUL.getResponseMessage(), 
				ResponseStatus.SUCCESS.name());
	}
	
	/*
	 *TODO: Completed.
	 * 1- Validation part is over
	 * 2- Happy path and unit testing are done.  
	 * 3- Once again it will be tested for assurance.
	 */
	@PostMapping(value = "/learn/buy", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LearnOnlineResponse<StudentResponse> buyCourse(
				@Valid @RequestBody BuyOrCancelCouresesRequest buyOrCancelCouresesRequest) {

		StudentDto studentDto = studentService.purchaseCourses(
					buyOrCancelCouresesRequest.getStudentEmail(), buyOrCancelCouresesRequest.getCourseKeys());
		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setStudentKey(studentDto.getStudentKey());
		
		return LearnOnlineResponse.build(studentResponse, 
				ResponseMessages.COURSES_BUY_OPERATION_SUCCESS.getResponseMessage(), 
				ResponseStatus.SUCCESS.name());
	}
	
	/*
	 *TODO: Completed.
	 * 1- Validation part is over
	 * 2- Happy path and unit testing are done.  
	 * 3- Once again it will be tested for assurance.
	 */
	@DeleteMapping(value = "/learn/cancel", 
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LearnOnlineResponse<StudentResponse> deleteCourse(
			@RequestBody @Valid BuyOrCancelCouresesRequest buyOrCancelCouresesRequest) {

		StudentDto studentDto = studentService.cancellPurchasedCourses(buyOrCancelCouresesRequest.getStudentEmail(), buyOrCancelCouresesRequest.getCourseKeys());
		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setStudentKey(studentDto.getStudentKey());
		
		return LearnOnlineResponse.build(studentResponse, 
				ResponseMessages.COURSES_DELETE_OPERATION_SUCCESS.getResponseMessage(), 
				ResponseStatus.SUCCESS.name());
	}
	
}
