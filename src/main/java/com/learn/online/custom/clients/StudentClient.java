package com.learn.online.custom.clients;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;

import com.learn.online.dtos.CourseDto;
import com.learn.online.requests.BuyOrCancelCouresesRequest;
import com.learn.online.requests.StudentLoginRequest;
import com.learn.online.requests.StudentSignupRequest;
import com.learn.online.requests.StudentUpdateRequest;
import com.learn.online.responses.LearnOnlineResponse;
import com.learn.online.responses.StudentDetailResponse;
import com.learn.online.responses.StudentResponse;
import com.learn.online.responses.StudentSignupResponse;

public interface StudentClient {
	
	public ResponseEntity<LearnOnlineResponse> createStudent(StudentSignupRequest studentSignupRequest);
	public String loginStudent(StudentLoginRequest studentLoginRequest);
	
	public ResponseEntity<LearnOnlineResponse> updateStudent(StudentUpdateRequest studentUpdateRequest, HttpSession session);
	
	public ResponseEntity<LearnOnlineResponse> buyCourse(
			BuyOrCancelCouresesRequest buyOrCancelCouresesRequest, HttpSession session);
	
	public LearnOnlineResponse<StudentResponse> deleteCourses(BuyOrCancelCouresesRequest buyOrCancelCouresesRequest);
	///learn/logout//public String logout(HttpSession session);
	
	public ResponseEntity<LearnOnlineResponse> getAllCourses();
	public LearnOnlineResponse<Map<String,Map<Double,List<CourseDto>>>> welcome();
	public ResponseEntity<LearnOnlineResponse> searchByEmail(String email, HttpSession session);
	public LearnOnlineResponse<Map<String,Map<Double,List<CourseDto>>>> searchCoursesByDomainAndRating();
	public LearnOnlineResponse<Map<String, List<CourseDto>>> searchCoursesByDomain(); 
	
	///learn/logout
	
}
