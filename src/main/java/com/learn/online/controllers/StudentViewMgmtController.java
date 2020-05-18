package com.learn.online.controllers;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.learn.online.requests.StudentSignupRequest;
import com.learn.online.requests.StudentUpdateRequest;
import com.learn.online.responses.LearnOnlineResponse;
import com.learn.online.responses.StudentResponse;
import com.learn.online.responses.StudentSignupResponse;
import  com.learn.online.utils.URLConstants;


@Controller
public class StudentViewMgmtController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(URLConstants.STUDENT_WELCOME_VIEW_URL)
	public ModelAndView welcome() {
		
		ModelAndView modelAndView = new ModelAndView("welcome-view");
		
		modelAndView.addObject("allCourses",restTemplate
		 .getForObject(URLConstants.BASE_URL + URLConstants.SEARCH_COURSES_BY_DOMAIN, Object.class));
		
		 return modelAndView;	
	}
	
	@GetMapping(URLConstants.STUDENT_SHOW_SIGNUP_VIEW_URL)
	public ModelAndView showSignup() {
		
		ModelAndView modelAndView = new ModelAndView("student-signup");
		
		modelAndView.addObject("student", new StudentSignupRequest());
		
		return modelAndView;	
	}
	
	@PostMapping(URLConstants.STUDENT_SIGNUP_VIEW_URL)
	public ModelAndView signup(@ModelAttribute("student") StudentSignupRequest studentSignupRequest) {
		
		ModelAndView modelAndView = new ModelAndView("student-signup-complete");
		
		
		LearnOnlineResponse<StudentSignupResponse> studentSignupResp = restTemplate
				 .postForObject(URLConstants.BASE_URL + URLConstants.STUDENT_SINGN_UP_URL, 
						 studentSignupRequest, LearnOnlineResponse.class);
				 
		modelAndView.addObject("studentSignupResp",studentSignupResp.getResponseDetail());
		
		 return modelAndView;	
	}
	
	@GetMapping(URLConstants.STUDENT_SHOW_UPDATE_VIEW)
	public ModelAndView showUpdate() {
		
		ModelAndView modelAndView = new ModelAndView("student-update");
		
		modelAndView.addObject("student", new StudentUpdateRequest());
		
		return modelAndView;	
	}
	
	@PostMapping(URLConstants.STUDENT_UPDATE_VIEW)
	public ModelAndView update(@ModelAttribute("student") StudentUpdateRequest studentUpdateRequest) {
		
		ModelAndView modelAndView = new ModelAndView("student-update-complete");
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<StudentUpdateRequest> studentUpdateReqEntity = 
				new HttpEntity<StudentUpdateRequest>(studentUpdateRequest, httpHeaders);
		
		ResponseEntity<LearnOnlineResponse> studentUpdateResponseEntity = restTemplate
				 .exchange(URLConstants.BASE_URL + URLConstants.STUDENT_UPDATE_URL, 
						 HttpMethod.PUT, studentUpdateReqEntity, 
						 LearnOnlineResponse.class);
				 
		modelAndView.addObject("studentUpdateResp", studentUpdateResponseEntity
					.getBody().getResponseDetail());
		
		 return modelAndView;	
	}
}
