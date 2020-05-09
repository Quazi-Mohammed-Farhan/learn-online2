package com.learn.online.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.learn.online.dtos.CourseDto;
import com.learn.online.dtos.StudentDto;
import com.learn.online.enums.ResponseMessages;
import com.learn.online.enums.ResponseStatus;
import com.learn.online.requests.BuyOrCancelCouresesRequest;
import com.learn.online.requests.StudentSignupRequest;
import com.learn.online.requests.StudentUpdateRequest;
import com.learn.online.responses.LearnOnlineResponse;
import com.learn.online.responses.StudentDetailResponse;
import com.learn.online.responses.StudentResponse;
import com.learn.online.responses.StudentSignupResponse;
import com.learn.online.services.StudentService;
import com.learn.online.utils.URLConstants;
import com.learn.online.services.CourseService;

/*
 * TODO: Logging part was completed
 * 1- I implemented logging in log4j2.
 * 2- I tested by its war on web server
 * 3- It works fine and looks good 
 * 
 */
@RestController
@Validated
public class StudentMgmtController {

	private static final Logger	LOGGER	= LogManager.getLogger(StudentMgmtController.class);
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	/*
	 *TODO: Completed.
	 * 1- Validation not required
	 * 2- Happy path and unit testing were completed.  
	 * 3- Once again it was tested for assurance. It works fine.
	 * 4- Unit testing done
	 */
	@GetMapping(value = URLConstants.STUDENT_WELCOME_URL)
	public LearnOnlineResponse<Map<String,Map<Double,List<CourseDto>>>> welcome() {
		
		LOGGER.info("StudentMgmtController::welcome() Started");
		
		LOGGER.info("Input: Empty argument.");
		
		LOGGER.info("All courses details are fetched succeefully. Student details are grouped by"
				+ " subject domain and rating");
		
		LOGGER.info("StudentMgmtController::welcome() Completed");
		
		return LearnOnlineResponse.build(courseService.findAllCoursesGroupByDomainAndRating(), 
				ResponseMessages.COURSES_SEARCH_BY_DOMAIN_RATING.getResponseMessage(), 
				ResponseStatus.SUCCESS.name());
	}
	
	/*
	 *TODO: Completed.
	 * 1- Validation part is over
	 * 2- Happy path and unit testing were completed.  
	 * 3- Once again it was tested for assurance. It works fine.
	 * 4- Email fail validation gives 404 error 
	 *    It hence along with validation error 
	 *    stack trace also visible. It has to 
	 *    be fixed
	 * 5- Unit testing done   
	 */
	@GetMapping(value = URLConstants.SARCH_STUDENT_BY_EMAIL)
	public LearnOnlineResponse<StudentDetailResponse> searchByEmail(
			@Email(message = "{email.mandatory}", regexp = ".+@.+\\.[a-z]+") 
			@NotBlank(message = "{email.is.not.valid}") @PathVariable  String email) {
		
		LOGGER.info("StudentMgmtController::searchByEmail() Started");
		
		LOGGER.info("Input: Email to search student details.");
		
		StudentDto studentDto = studentService.findByEmail(email);
		StudentDetailResponse studentDetailResponse = new StudentDetailResponse(studentDto);
		
		
		LOGGER.info("Student details retrieved successfully {} {}", 
					studentDetailResponse.getFirstName(), studentDetailResponse.getLastName());
		LOGGER.info("StudentMgmtController::searchByEmail() Completed");
		
		return LearnOnlineResponse.build(studentDetailResponse, 
				ResponseMessages.DATA_FOUND.getResponseMessage(), ResponseStatus.SUCCESS.name());
	}
	
	/*
	 *TODO: Completed.
	 * 1- Validation part is over
	 * 2- Happy path and unit testing were completed.  
	 * 3- Once again it was tested for assurance. It works fine.
	 * 4- Unit testing pending
	 */	
	@PostMapping(value = URLConstants.STUDENT_SINGN_UP_URL, 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public LearnOnlineResponse<StudentSignupResponse> createStudent(
			@Valid @RequestBody StudentSignupRequest studentSignupRequest) {
		
		LOGGER.info("StudentMgmtController::createStudent() Started");
		LOGGER.info("Input. Student name: {} {} " , 
					studentSignupRequest.getFirstName(), studentSignupRequest.getLastName());
		
		StudentDto studentDto = new StudentDto();
		BeanUtils.copyProperties(studentSignupRequest, studentDto);
		studentDto.setEncryptedPassword(studentSignupRequest.getPassword());
		studentDto = studentService.signupStudent(studentDto);

		LOGGER.info("Student detail saved successfully. Student name: {} {} " , 
				studentSignupRequest.getFirstName(), studentSignupRequest.getLastName());
		
		StudentSignupResponse studentSignupResponse = new StudentSignupResponse();
		studentSignupResponse.setStudentKey(studentDto.getStudentKey());
		
		LOGGER.info("StudentMgmtController::createStudent() Completed");
		
		return LearnOnlineResponse.build(studentSignupResponse, 
					ResponseMessages.STUDENT_ADD_OPERATION_SUCCESSFUL.getResponseMessage(), 
					ResponseStatus.SUCCESS.name());
	}
	
	/*
	 *TODO: Completed.
	 * 1- Validation part is over
	 * 2- Happy path and unit testing were completed.  
	 * 3- Once again it was tested for assurance. It works fine.
	 * 4- Unit testing pending
	 * 5- PROBLEM: Password confirmation is not working
	 */
	@PutMapping(value = URLConstants.STUDENT_UPDATE_URL, 
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public LearnOnlineResponse<StudentResponse> updateStudent(@Valid
				@RequestBody StudentUpdateRequest studentUpdateRequest) {

		LOGGER.info("StudentMgmtController::updateStudent() Started");
		LOGGER.info("Input: Student detail for update. {}, {}", 
					studentUpdateRequest.getFirstName(), studentUpdateRequest.getLastName());
		
		StudentDto studentDto = new StudentDto();
		BeanUtils.copyProperties(studentUpdateRequest, studentDto);
		studentDto.setEncryptedPassword(studentUpdateRequest.getPassword());

		LocalDate currentDate = LocalDate.now();
		studentDto.setLastUpdateDate(currentDate);
		studentDto = studentService.updateStudent(studentDto);
		
		LOGGER.info("Student detail for updated successfully. {}, {}", 
				studentDto.getFirstName(), studentDto.getLastName());
		
		StudentResponse studentUpdateResponse = new StudentResponse();

		studentUpdateResponse.setStudentKey(studentDto.getStudentKey());
		
		LOGGER.info("StudentMgmtController::updateStudent() Completed");
		
		return LearnOnlineResponse.build(studentUpdateResponse, 
				ResponseMessages.STUDENT_UPDATE_OPERATION_SUCCESSFUL.getResponseMessage(), 
				ResponseStatus.SUCCESS.name());
	}
	
	/*
	 *TODO: Completed.
	 * 1- Validation part is over
	 * 2- Happy path and unit testing were completed.  
	 * 3- Once again it was tested for assurance. It works fine.
	 * 4- Unit testing pending
	 */
	@PostMapping(value = URLConstants. STUDENT_PURCHASE_COURSES_URL, 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LearnOnlineResponse<StudentResponse> buyCourse(
				@Valid @RequestBody BuyOrCancelCouresesRequest buyOrCancelCouresesRequest) {

		LOGGER.info("StudentMgmtController::buyCourse() Started");
		
		LOGGER.info("Input: Total number of courses to buy {} " 
						+ buyOrCancelCouresesRequest.getCourseKeys().size());
		
		StudentDto studentDto = studentService.purchaseCourses(buyOrCancelCouresesRequest.getStudentEmail(),
					buyOrCancelCouresesRequest.getCourseKeys());
		
		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setStudentKey(studentDto.getStudentKey());
		
		LOGGER.info("Student purchased {} courses successfully. Student name {} {}"
				+ studentDto.getFirstName(), studentDto.getLastName(), 
					studentDto.getCourseOrders().size());
		
		LOGGER.info("StudentMgmtController::StudentMgmtControllerbuyCourse() Completed");
		
		return LearnOnlineResponse.build(studentResponse, 
				ResponseMessages.COURSES_BUY_OPERATION_SUCCESS.getResponseMessage(), 
				ResponseStatus.SUCCESS.name());
	}
	
	/*
	 *TODO: Completed.
	 * 1- Validation part is over
	 * 2- Happy path and unit testing were completed.  
	 * 3- Once again it was tested for assurance. It works fine.
	 * 4- Unit testing pending
	 */
	@DeleteMapping(value = URLConstants.STUDENT_CANCEL_PURCHASED_COURSES_URL, 
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public LearnOnlineResponse<StudentResponse> deleteCourses(
			@RequestBody @Valid BuyOrCancelCouresesRequest buyOrCancelCouresesRequest) {

		LOGGER.info("StudentMgmtController::deleteCourses() Started");
		
		LOGGER.info("Input: Student email. Total number courses to buy {}", 
				buyOrCancelCouresesRequest.getCourseKeys().size());
		
		LOGGER.info("Take student email and courses keys to cancel the courses.");
		
		StudentDto studentDto = studentService.cancellPurchasedCourses(
				buyOrCancelCouresesRequest.getStudentEmail(), buyOrCancelCouresesRequest.getCourseKeys());
		
		LOGGER.info("Student canceled courses successfully. Student name {} {}" 
				+ " Total number of courses to cancel {}", studentDto.getFirstName(),
				studentDto.getLastName(), studentDto.getCourseOrders().size());
		
		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setStudentKey(studentDto.getStudentKey());
		
		LOGGER.info("StudentMgmtController::deleteCourses() Completed");
		
		return LearnOnlineResponse.build(studentResponse, 
				ResponseMessages.COURSES_DELETE_OPERATION_SUCCESS.getResponseMessage(), 
				ResponseStatus.SUCCESS.name());
	}
	
	/*
	 *TODO: Completed.
	 * 1- Validation not required
	 * 2- Happy path and unit testing were completed.  
	 * 3- Once again it was tested for assurance. It works fine.
	 * 4- Unit testing done
	 */
	@GetMapping(value = URLConstants.SEARCH_COURSES_BY_DOMAIN_AND_RATING)
	public LearnOnlineResponse<Map<String,Map<Double,List<CourseDto>>>> searchCoursesByDomainAndRating() {
		
		LOGGER.info("StudentMgmtController::searchCoursesByDomainAndRating() Started");
		
		LOGGER.info("Input: Empty argument.");
		
		LOGGER.info("All courses details are fetched succeefully. Student details are grouped by"
				+ " subject domain and rating");
		
		LOGGER.info("StudentMgmtController::searchCoursesByDomainAndRating() Completed");
		
		return LearnOnlineResponse.build(courseService.findAllCoursesGroupByDomainAndRating(), 
				ResponseMessages.COURSES_SEARCH_BY_DOMAIN_RATING.getResponseMessage(), 
				ResponseStatus.SUCCESS.name());
	}
	
	/*
	 *TODO: Completed.
	 * 1- Validation not required
	 * 2- Happy path and unit testing were completed.  
	 * 3- Once again it was tested for assurance. It works fine.
	 * 4- Unit testing done
	 */
	@GetMapping(value = URLConstants.SEARCH_COURSES_BY_DOMAIN)
	public LearnOnlineResponse<Map<String, List<CourseDto>>> searchCoursesByDomain() {		
		
		LOGGER.info("StudentMgmtController::searchCoursesByDomain() Started");
		
		LOGGER.info("Input: Empty argument.");
		
		LOGGER.info("All courses details are fetched succeefully. Student details are grouped by"
				+ " subject domain and rating");
		
		LOGGER.info("StudentMgmtController::searchCoursesByDomainAndRating() Completed");
		
		return LearnOnlineResponse.build(courseService.findAllCoursesGroupByDomain(), 
				ResponseMessages.COURSES_SEARCH_BY_DOMAIN_RATING.getResponseMessage(), ResponseStatus.SUCCESS.name());
	
	}	
}
