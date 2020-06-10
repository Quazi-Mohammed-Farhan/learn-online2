package com.learn.online.custom.clients.impls;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learn.online.custom.clients.StudentClient;
import com.learn.online.dtos.CourseDto;
import com.learn.online.requests.BuyOrCancelCouresesRequest;
import com.learn.online.requests.StudentLoginRequest;
import com.learn.online.requests.StudentSignupRequest;
import com.learn.online.requests.StudentUpdateRequest;
import com.learn.online.responses.LearnOnlineResponse;
import com.learn.online.responses.StudentResponse;
import com.learn.online.securities.SecurityConstants;
import com.learn.online.utils.CustomUtils;
import com.learn.online.utils.URLConstants;

@Service
public class StudentClientImpl implements StudentClient {
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	@SuppressWarnings("rawtypes")
	public ResponseEntity<LearnOnlineResponse> createStudent(
			StudentSignupRequest studentSignupRequest, HttpSession session) {
		
		 HttpHeaders httpHeaders = new HttpHeaders();
		 httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		 httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		 
		 CustomUtils.configureRequestHeader(httpHeaders, session, true);
		 
		 return restTemplate.postForEntity(URLConstants.BASE_URL 
				 + URLConstants.STUDENT_SINGN_UP_URL, 
							studentSignupRequest, LearnOnlineResponse.class);
	
	}
	
	@Override
	public String loginStudent(StudentLoginRequest studentLoginRequest) {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<StudentLoginRequest> studentLoginRequestEntity = 
				new HttpEntity<>(studentLoginRequest, httpHeaders);
		
		List<String> authorizationToken = null;
			
		authorizationToken = restTemplate.postForEntity(
			URLConstants.BASE_URL + URLConstants.STUDENT_LOGIN_URL, 
			studentLoginRequestEntity, Void.class)
			.getHeaders().get(SecurityConstants.HEADER_STRING);
		
		return authorizationToken.get(0);
		
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public ResponseEntity<LearnOnlineResponse> updateStudent(
			StudentUpdateRequest studentUpdateRequest, HttpSession session) {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		
		CustomUtils.configureRequestHeader(httpHeaders, session, true);
		
		HttpEntity<StudentUpdateRequest> requestEntity = 
				new HttpEntity<>(studentUpdateRequest, httpHeaders);
		
		return restTemplate.exchange(URLConstants.BASE_URL 
				+ URLConstants.STUDENT_UPDATE_URL, HttpMethod.PUT, 
				requestEntity, LearnOnlineResponse.class);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public ResponseEntity<LearnOnlineResponse> buyCourse(
			BuyOrCancelCouresesRequest buyOrCancelCouresesRequest, HttpSession session) {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		
		CustomUtils.configureRequestHeader(httpHeaders, session, true);
		
		HttpEntity<BuyOrCancelCouresesRequest> httpEntity = 
				new HttpEntity<>(buyOrCancelCouresesRequest, httpHeaders);
		
		return restTemplate.postForEntity(URLConstants.BASE_URL 
				+ URLConstants.STUDENT_PURCHASE_COURSES_URL, 
				httpEntity, LearnOnlineResponse.class);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public ResponseEntity<LearnOnlineResponse> deleteCourses(
			BuyOrCancelCouresesRequest buyOrCancelCouresesRequest, HttpSession session) {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		CustomUtils.configureRequestHeader(httpHeaders, session, true);
		
		HttpEntity<BuyOrCancelCouresesRequest> httpReqEntity = 
				new HttpEntity<>(buyOrCancelCouresesRequest, httpHeaders);

		
		return restTemplate.exchange(URLConstants.BASE_URL
				+ URLConstants.STUDENT_CANCEL_PURCHASED_COURSES_URL,
				HttpMethod.DELETE, httpReqEntity, LearnOnlineResponse.class);
		
	}

	@Override
	public LearnOnlineResponse<Map<String, Map<Double, List<CourseDto>>>> welcome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public ResponseEntity<LearnOnlineResponse> searchByEmail(
				String email, HttpSession session) {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.set(SecurityConstants.HEADER_STRING, 
				session.getAttribute("webToken").toString());
		
		HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
		
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("email", email);
		
		ResponseEntity<LearnOnlineResponse> responseEntity = restTemplate.exchange(
				URLConstants.BASE_URL +  URLConstants.SEARCH_STUDENT_BY_EMAIL, 
				HttpMethod.GET, requestEntity, 
				LearnOnlineResponse.class, pathVariables);
		
		/*
		 * Setting collection of courses student purchased. Remember this
		 * attribute has to be updated after student purchase and cancel 
		 * purchased courses for api call byCourses() and cancelPurchasedCourse
		 */
		session.setAttribute("studentProfile", 
				responseEntity.getBody().getResponseDetail());
		
		return responseEntity;
	}

	@Override 
	@SuppressWarnings("rawtypes")
	public ResponseEntity<LearnOnlineResponse> getAllCourses() {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		return restTemplate.getForEntity(URLConstants.BASE_URL +  
				URLConstants.SEARCH_ALL_COURSES, LearnOnlineResponse.class);
		
	}
	
	@Override
	public LearnOnlineResponse<Map<String, Map<Double, List<CourseDto>>>> 
		searchCoursesByDomainAndRating() {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LearnOnlineResponse<Map<String, List<CourseDto>>> searchCoursesByDomain() {
		// TODO Auto-generated method stub
		return null;
	}

}
