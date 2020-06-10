package com.learn.online.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.ModelAndView;

import com.learn.online.custom.clients.StudentClient;
import com.learn.online.requests.BuyOrCancelCouresesRequest;
import com.learn.online.requests.StudentLoginRequest;
import com.learn.online.requests.StudentSignupRequest;
import com.learn.online.requests.StudentUpdateRequest;
import com.learn.online.securities.SecurityConstants;
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
	public ModelAndView doStudentSignup(@Valid StudentSignupRequest studentSignupRequest, 
				BindingResult bindingResult, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();

		if(bindingResult.hasErrors()) {
			
			modelAndView.setViewName("studentSignupForm");
		
		} else {
		
			modelAndView.setViewName(URLConstants.UI_RESULT_TEMPATE_NAME);
			modelAndView.addObject(URLConstants.UI_RESPONSE_BODY_NAME, 
					studentClient.createStudent(studentSignupRequest, session));
		}
		
		return modelAndView;
	}
	
	@GetMapping(value = URLConstants.UI_SHOW_LOGIN_SCREEN)
	public String showStudentInputForm(Model model) {
		
		model.addAttribute("studentLoginRequest", new StudentLoginRequest());
		return "studentLoginForm";
		
	}
	
	@PostMapping(value = URLConstants.UI_DO_STUDENT_LOGIN)
	public String doStudentLoginInputForm(
			StudentLoginRequest studentLoginRequest, BindingResult bindingResult, 
			HttpServletRequest request, HttpServletResponse response, 
			HttpSession session) {
		
		String retValue = "studentLoginForm";
		
		if(bindingResult.hasErrors()) {
			session.setAttribute("loginAttr", "Login failed. Please try again");
			return retValue;
		}
			
		try {
		/*
		 * Setting the security web token and username that is email  as session 
		 * attribute after successful login. Successful login generate fresh 
		 * JWT web token at server side and we get it here at our client side code 
		 * as response header and then we set it as session attribute for further 
		 * use for secured http rest endpoint call. In that case we access JWT 
		 * web token from session and set it to Authorization request header before 
		 * calling secured rest service 
		 */
		session.setAttribute(SecurityConstants.WEB_TOKEN, 
			studentClient.loginStudent(studentLoginRequest));
		
		session.setAttribute(SecurityConstants.EMAIL_ID, 
				studentLoginRequest.getEmail());
		
		/*
		 * Setting collection of courses student purchased. Remember this
		 * attribute has to be updated after student purchase and cancel 
		 * purchased courses for api call byCourses() and cancelPurchasedCourse
		 */
		studentClient.searchByEmail(studentLoginRequest.getEmail(), session);
		
			retValue = "redirect:/showStudentProfile";
			session.setAttribute("loginAttr", "");
		} catch(ResourceAccessException ex) {
			session.setAttribute("loginAttr", "Login failed. Please try again");
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
		} else if(!CustomUtils.verifyCrendentials(session)) {
			modelAndView.setViewName(URLConstants.UI_LOGIN_REDIRECT);
		} else {
			modelAndView.setViewName(URLConstants.UI_RESULT_TEMPATE_NAME);
			modelAndView.addObject(URLConstants.UI_RESPONSE_BODY_NAME, 
					studentClient.updateStudent(studentUpdateRequest, session));
		}
		
		return modelAndView;
	}
	
	
	/*
	 * It searches the student details and also update 
	 * student profiles such as current status of purchase courses
	 * update personal info and etc.
	 */
	@GetMapping(value = URLConstants.UI_SEARCH_BY_EMAIL)
	public ModelAndView searchByEmail(@PathVariable("email") String email, 
				HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("searchedByEmailingResponse");
		modelAndView.addObject("courseSearchedByEmailResponse", 
				studentClient.searchByEmail(email, session));
		
		return modelAndView;
	}	
	
	@GetMapping(value = URLConstants.UI_STUDENT_UI_LOGOUT_URL)
	public String logout(HttpSession session) {
		
		
		/*
		 * This is logout operation that let the access denied to secured resources
		 * As you know that login operation send login request to server and get 
		 * JWT Webtoken after successful login and then login operation set these 
		 * JWT Webtoken and email/username as session attributes so that secured 
		 * rest end point can be called. Secured call access the web token from 
		 * session scope and set it to Authorization rquest header. Now in case 
		 * of logout we remove replace Webtoken and username/email with dummy 
		 * webtoken and username in session with real webtoken and username so that 
		 * call to secured Rest Endpoint can be done and this is how we make it logout.
		 * Here we just replacing attributes, email(username) and secured web token 
		 * in session object. When username(email) and JWT Webtoken are replaced 
		 * with dummies ones in session scopes then student/user can not call 
		 * secured rest end point for example update student profile, 
		 * purchasing and canceling courses or looking the profile details of student.   
		 */
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
		return URLConstants.PURCHASE_TEMPLATE;
	}
	
	@PostMapping(value = URLConstants.UI_DO_PURCHASE)
	public ModelAndView doStudentPurchaseCourse(
			@Valid BuyOrCancelCouresesRequest buyOrCancelCouresesRequest,
			BindingResult bindingResult, HttpSession session) {
		
		if(session.getAttribute("allCourses") == null) {
			
			session.setAttribute("allCourses", studentClient
				.getAllCourses().getBody().getResponseDetail());
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allCourses", session.getAttribute("allCourses"));
		
		if(bindingResult.hasErrors()) {
			
			modelAndView.setViewName(URLConstants.PURCHASE_TEMPLATE);
		
		} else if(!CustomUtils.verifyCrendentials(session)) {
		
			modelAndView.setViewName(URLConstants.UI_LOGIN_REDIRECT);
	
		} else {
			
			modelAndView.setViewName(URLConstants.UI_RESULT_TEMPATE_NAME);
			
			modelAndView.addObject(URLConstants.UI_RESPONSE_BODY_NAME, 
				studentClient.buyCourse(buyOrCancelCouresesRequest, session));
			
			/*
			 * Updating Student profiles after purchasing new courses because 
			 * now student course collection is increased
			 */
			studentClient.searchByEmail(
					session.getAttribute(SecurityConstants.EMAIL_ID).toString(), session);

		}
				
		return modelAndView;
	}
	
	@GetMapping(value = URLConstants.UI_SHOW_CANCEL_PURCHASE_SCREEN)
	public String showDeletePurchasedCourseForm(Model model, HttpSession session) {
		
		
		if(!CustomUtils.verifyCrendentials(session)) {
		
			return URLConstants.UI_LOGIN_REDIRECT;
		
		} else if(session.getAttribute("studentProfile") == null) {
			
			studentClient.searchByEmail(session.getAttribute(
					SecurityConstants.EMAIL_ID).toString(), session);
			
			if(session.getAttribute("studentProfile")  == null ) {
				
				return URLConstants.UI_LOGIN_REDIRECT;
				
			}
				
		}
		
		model.addAttribute("buyOrCancelCouresesRequest", new BuyOrCancelCouresesRequest());
		model.addAttribute("studentProfile", session.getAttribute("studentProfile"));
		
		return URLConstants.CANCEL_PURCHASE_TEMPLATE;
	}
	
	@PostMapping(value = URLConstants.UI_DO_CANCEL_PURCHASE)
	public ModelAndView doDeleteCourses(
			@Valid BuyOrCancelCouresesRequest buyOrCancelCouresesRequest, 
			BindingResult bindingResult, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(!CustomUtils.verifyCrendentials(session)) {
		
			modelAndView.setViewName(URLConstants.UI_LOGIN_REDIRECT);
			
			return modelAndView;
		
		} else if(session.getAttribute("studentProfile") == null) {
			
			studentClient.searchByEmail(session.getAttribute(
					SecurityConstants.EMAIL_ID).toString(), session);
			
			if(session.getAttribute("studentProfile")  == null ) {
				
				modelAndView.setViewName(URLConstants.UI_LOGIN_REDIRECT);
				
				return modelAndView;
			}
				
		}
		
		modelAndView.addObject("studentProfile",
				session.getAttribute("studentProfile"));
		
		if(bindingResult.hasErrors()) {
		
			modelAndView.setViewName(URLConstants.CANCEL_PURCHASE_TEMPLATE);
		
		} else if(!CustomUtils.verifyCrendentials(session)) {
			modelAndView.setViewName(URLConstants.UI_LOGIN_REDIRECT);
		
		} else {

			modelAndView.setViewName(URLConstants.UI_RESULT_TEMPATE_NAME);
			modelAndView.addObject(URLConstants.UI_RESPONSE_BODY_NAME,
				studentClient.deleteCourses(buyOrCancelCouresesRequest, session));
			
			/*
			 * Updating Student profiles after cancellation of courses becuase 
			 * now student course collection is reduced
			 */
			studentClient.searchByEmail(
			  session.getAttribute(SecurityConstants.EMAIL_ID).toString(), session);

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
		modelAndView.addObject("coursesByDomainResponse", 
				studentClient.searchCoursesByDomain());
	
		return modelAndView;
	}
		
}