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
import com.learn.online.responses.StudentResponse;

public interface StudentClient {
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity<LearnOnlineResponse> createStudent(
			StudentSignupRequest studentSignupRequest, HttpSession session);
	
	public String loginStudent(StudentLoginRequest studentLoginRequest);
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity<LearnOnlineResponse> updateStudent(
			StudentUpdateRequest studentUpdateRequest, HttpSession session);
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity<LearnOnlineResponse> buyCourse(
			BuyOrCancelCouresesRequest buyOrCancelCouresesRequest, HttpSession session);
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity<LearnOnlineResponse> deleteCourses(
			BuyOrCancelCouresesRequest buyOrCancelCouresesRequest, HttpSession session);
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity<LearnOnlineResponse> getAllCourses();

	public LearnOnlineResponse<Map<String,Map<Double,List<CourseDto>>>> welcome();
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity<LearnOnlineResponse> searchByEmail(String email, 
			HttpSession session);
	
	public LearnOnlineResponse<Map<String,Map<Double,List<CourseDto>>>> 
	searchCoursesByDomainAndRating();
	
	public LearnOnlineResponse<Map<String, List<CourseDto>>> searchCoursesByDomain(); 
	
}
