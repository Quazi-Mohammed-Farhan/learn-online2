package com.learn.online.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.learn.online.custom.clients.StudentClient;
import com.learn.online.dtos.CourseDto;
import com.learn.online.requests.BuyOrCancelCouresesRequest;
import com.learn.online.requests.StudentLoginRequest;
import com.learn.online.requests.StudentSignupRequest;
import com.learn.online.requests.StudentUpdateRequest;
import com.learn.online.responses.LearnOnlineResponse;
import com.learn.online.securities.SecurityConstants;
import com.learn.online.securities.UserPrincipal;
import com.learn.online.utils.CustomUtils;
import com.learn.online.utils.URLConstants;

@Controller
public class StudentUIController {

	@Autowired
	StudentClient studentClient;
	
	@GetMapping(value = URLConstants.UI_SHOW_STUDENT_SIGNUP_SCREEN)
	public String showStudentSignupForm(Model model) {
		
		model.addAttribute("studentSignupRequest", new StudentSignupRequest());
		return "studentSignupForm";
	}
	
	@PostMapping(value = URLConstants.UI_DO_STUDENT_SIGNUP)
	public ModelAndView doStudentSignup(@Valid StudentSignupRequest studentSignupRequest, BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView();

		if(bindingResult.hasErrors()) {
			modelAndView.setViewName("studentSignupForm");
		} else {
			modelAndView.setViewName(URLConstants.UI_RESULT_TEMPATE_NAME);
			modelAndView.addObject(URLConstants.UI_RESPONSE_BODY_NAME, 
					studentClient.createStudent(studentSignupRequest));
		}
		
		return modelAndView;
	}
	
	@GetMapping(value = URLConstants.UI_SHOW_LOGIN_SCREEN)
	public String showStudentInputForm(Model model) {
		
		model.addAttribute("studentLoginRequest", new StudentLoginRequest());
		return "studentLoginForm";
		
	}
	
	@PostMapping(value = URLConstants.UI_DO_STUDENT_LOGIN)
	public String doStudentLoginInputForm(StudentLoginRequest studentLoginRequest, 
			HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult) {
		
		String retValue = "studentLoginForm";
		
		if(!bindingResult.hasErrors()) {
			HttpSession session = request.getSession(); 
			session.setAttribute(SecurityConstants.WEB_TOKEN, 
				studentClient.loginStudent(studentLoginRequest));
			session.setAttribute(SecurityConstants.EMAIL_ID, 
					studentLoginRequest.getEmail());
			retValue = "redirect:/showStudentProfile";
		}
		
		return retValue;
		
	}
	
	@GetMapping(value = "showStudentProfile") 
	public String showStudentProfile() {
		return "studentProfile";
	}
	
	@GetMapping(value = URLConstants.UI_SHOW_STUDENT_UPDATE_SCREEN)
	public String showStudentUpdateForm(Model model) {
		
		model.addAttribute("studentUpdateRequest", new StudentUpdateRequest());
		return "studentUpdateForm";
	}

	@PostMapping(value = URLConstants.UI_DO_STUDENT_UPDATE)
	public ModelAndView doStudentUpdate(@Valid StudentUpdateRequest studentUpdateRequest, 
			 BindingResult bindingResult, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			modelAndView.setViewName("studentUpdateForm");
		} else if(CustomUtils.verifyCrendentials(session)) {
			modelAndView.setViewName(URLConstants.UI_SHOW_LOGIN_SCREEN);
		} else {
			modelAndView.setViewName(URLConstants.UI_RESULT_TEMPATE_NAME);
			modelAndView.addObject(URLConstants.UI_RESPONSE_BODY_NAME, 
					studentClient.updateStudent(studentUpdateRequest, session));
		}
		
		return modelAndView;
	}
	
	
	@GetMapping(value = URLConstants.UI_SEARCH_BY_EMAIL)
	public ModelAndView searchByEmail(@PathVariable("email") String email, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("searchedByEmailingResponse");
		modelAndView.addObject("courseSearchedByEmailResponse", studentClient.searchByEmail(email, session));
		return modelAndView;
	}	
	
	@GetMapping(value = URLConstants.UI_STUDENT_UI_LOGOUT_URL)
	public String logout(HttpSession session) {
		session.setAttribute(SecurityConstants.EMAIL_ID, SecurityConstants.DUMMY_EMAIL);
		session.setAttribute(SecurityConstants.WEB_TOKEN, SecurityConstants.DUMMY_WEB_TOKEN);
		return URLConstants.UI_LOGIN_REDIRECT;
	}
	
	
	@GetMapping(value = URLConstants.UI_SHOW_PURCHASE_SCREEN)
	public String showStudentPurchaseCourseForm(Model model, HttpSession session) {
		
		if(session.getAttribute("allCourses") == null) {
			session.setAttribute("allCourses", studentClient
				.getAllCourses().getBody().getResponseDetail());
		}
		
		model.addAttribute("allCourses", session.getAttribute("allCourses"));

		model.addAttribute("buyOrCancelCouresesRequest", new BuyOrCancelCouresesRequest());
		return "purchaseCourseForm";
	}
	
	@PostMapping(value = URLConstants.UI_DO_PURCHASE)
	public ModelAndView doStudentPurchaseCourse(@Valid BuyOrCancelCouresesRequest buyOrCancelCouresesRequest,
			BindingResult bindingResult, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			
			if(session.getAttribute("allCourses") == null) {
				session.setAttribute("allCourses", studentClient
					.getAllCourses().getBody().getResponseDetail());
			}
			
			modelAndView.setViewName("purchaseCourseForm");
			modelAndView.addObject("allCourses", session.getAttribute("allCourses"));
		} else if(CustomUtils.verifyCrendentials(session)) {
				modelAndView.setViewName(URLConstants.UI_SHOW_LOGIN_SCREEN);
	
		} else {
			modelAndView.setViewName(URLConstants.UI_RESULT_TEMPATE_NAME);
			modelAndView.addObject(URLConstants.UI_RESPONSE_BODY_NAME, 
				studentClient.buyCourse(buyOrCancelCouresesRequest, session));

		}
				
		return modelAndView;
	}
	
	@GetMapping(value = "cancelPurchasedCourse")
	public String showDeletePurchasedCourseForm(Model model) {
		
		model.addAttribute("buyOrCancelCouresesRequest", new BuyOrCancelCouresesRequest());
		return "cancelPurchasedCourseForm";
	}
	
	@PostMapping(value = "doCancelPurchasedCourse")
	public ModelAndView doDeleteCourses(BuyOrCancelCouresesRequest buyOrCancelCouresesRequest,
			BindingResult bindingResult, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(bindingResult.hasErrors()) {

			modelAndView.setViewName("cancelPurchasedCourseForm");
		} else if(CustomUtils.verifyCrendentials(session)) {
			modelAndView.setViewName(URLConstants.UI_SHOW_LOGIN_SCREEN);
		} else {
			modelAndView.addObject("cancelledPurchaseResponsed", 
				studentClient.buyCourse(buyOrCancelCouresesRequest, session));
			
			modelAndView.setViewName(URLConstants.UI_RESULT_TEMPATE_NAME);

		}
				
		return modelAndView;
	}
	
	@GetMapping(value = {URLConstants.UI_GET_ALL_COURSES})
	public ModelAndView getAllCoures() {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("avaiableCourses");
		modelAndView.addObject("allCourses", studentClient.getAllCourses());
		return modelAndView;
	}
	
	@GetMapping(value = {"/", "welcome", "showHome"})
	public ModelAndView welcome() {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("welcome");
		modelAndView.addObject("coursesByDomainAndRatingResponse", studentClient.welcome());
		return modelAndView;
	}
	
	@GetMapping(value = "searchCourseByDomainAndRating")
	public ModelAndView searchCoursesByDomainAndRating() {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("welcome");
		modelAndView.addObject("coursesByDomainAndRatingResponse", studentClient.welcome());
		return modelAndView;
	}
	
	@GetMapping(value = "searchCourseByDomain")
	public ModelAndView searchCoursesByDomain() {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("welcome");
		modelAndView.addObject("coursesByDomainResponse", studentClient.searchCoursesByDomain());
		return modelAndView;
	}
	
}