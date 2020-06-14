package com.learn.online.controllers;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.learn.online.dtos.CourseDto;
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
	public String showStudentSignupForm(Model model, HttpServletRequest request) {
		
		CustomUtils.setupSessionForBredCrumbIfNot(request);
		
		model.addAttribute("studentSignupRequest", new StudentSignupRequest());
		return "studentSignupForm";
	}
	
	@PostMapping(value = URLConstants.UI_DO_STUDENT_SIGNUP)
	public ModelAndView doStudentSignup(
			@Valid StudentSignupRequest studentSignupRequest, 
			BindingResult bindingResult,
			HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();

		HttpSession session = 
			CustomUtils.setupSessionForBredCrumbIfNot(request);
		
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
	public String showStudentLoginForm(Model model, HttpServletRequest request) {
		
		CustomUtils.setupSessionForBredCrumbIfNot(request);
		
		model.addAttribute("studentLoginRequest", new StudentLoginRequest());
		return "studentLoginForm";
		
	}
	
	@PostMapping(value = URLConstants.UI_DO_STUDENT_LOGIN)
	public String doStudentLoginInputForm(
			StudentLoginRequest studentLoginRequest, 
			BindingResult bindingResult, 
			HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			session = request.getSession(true);
		}
		
		session.setAttribute("loginAttr", "");
		
		//Setting student/user email
		session.setAttribute(SecurityConstants.EMAIL_ID, 
				studentLoginRequest.getEmail());
		
		String retValue = "studentLoginForm";
		
		if(bindingResult.hasErrors()) {
			session.setAttribute("loginAttr", "Login failed. Please try again");
			session.setAttribute(SecurityConstants.EMAIL_ID, 
					SecurityConstants.DUMMY_EMAIL);
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
		
		/*
		 * Setting collection of courses student purchased. Remember this
		 * attribute has to be updated after student purchase and cancel 
		 * purchased courses for api call byCourses() and cancelPurchasedCourse
		 */
		studentClient.searchByEmail(studentLoginRequest.getEmail(), session);
		
			retValue = "redirect:/showStudentProfile";
		
		} catch(ResourceAccessException ex) {
			session.setAttribute("loginAttr", "Login failed. Please try again");
		}
		
		return retValue;
		
	}
	
	@GetMapping(value = "showStudentProfile") 
	public String showStudentProfile(HttpServletRequest request) {
		
		HttpSession session = CustomUtils.setupSessionForBredCrumbIfNot(request);
		
		String email = (String) session.getAttribute(SecurityConstants.EMAIL_ID);
		
		ModelAndView modelAndView = searchByEmail(
				email != null?email:SecurityConstants.DUMMY_EMAIL, 
					request, session);

		if(!URLConstants.UI_LOGIN_REDIRECT
				.equalsIgnoreCase(modelAndView.getViewName())) {
			
			return "studentProfile";
		
		} else {
		
			return modelAndView.getViewName();
		}
		
	}
	
	
	@GetMapping(value = URLConstants.UI_SHOW_STUDENT_UPDATE_SCREEN)
	public String showStudentUpdateForm(Model model, 
			HttpServletRequest request, HttpSession session) {
		
		StudentUpdateRequest studentUpdateRequest = new StudentUpdateRequest();
		model.addAttribute("studentUpdateRequest", studentUpdateRequest);

		CustomUtils.setupSessionForBredCrumbIfNot(request);
		
		Map studentDetailResponseMap = null;
		
		if(!CustomUtils.verifyCrendentials(session)) {
			
			return URLConstants.UI_LOGIN_REDIRECT;
		
		} else if(session.getAttribute("studentProfile") == null) {
			
			studentClient.searchByEmail(session.getAttribute(
					SecurityConstants.EMAIL_ID).toString(), session);
			
			if(session.getAttribute("studentProfile")  == null ) {
				
				return URLConstants.UI_LOGIN_REDIRECT;
				
			}
			
			studentDetailResponseMap =  (Map)
					session.getAttribute("studentProfile");
				
		} else {
			studentDetailResponseMap =  (Map)
					session.getAttribute("studentProfile");
		}
		
		try {
			
			studentUpdateRequest.setActive((Boolean)studentDetailResponseMap.get("active"));
			studentUpdateRequest.setCity((String)studentDetailResponseMap.get("city"));
			studentUpdateRequest.setEmail((String)studentDetailResponseMap.get("email"));
			studentUpdateRequest.setFirstName((String)studentDetailResponseMap.get("firstName"));
			studentUpdateRequest.setLastName((String)studentDetailResponseMap.get("lastName"));
			studentUpdateRequest.setPhone((String)studentDetailResponseMap.get("phone"));
			studentUpdateRequest.setState((String)studentDetailResponseMap.get("state"));
			studentUpdateRequest.setCountry((String)studentDetailResponseMap.get("country"));
			
		} catch (Exception e) {
			return URLConstants.UI_LOGIN_REDIRECT;
		}	
		
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
			HttpServletRequest request, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();

		CustomUtils.setupSessionForBredCrumbIfNot(request);
		
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
		
		modelAndView.setViewName("myCourses");
		modelAndView.addObject("studentProfile", session.getAttribute("studentProfile"));
		
		return modelAndView;
		
	}	
	
	@GetMapping(value = URLConstants.UI_STUDENT_UI_LOGOUT_URL)
	public String logout(HttpServletRequest request) {
		
		
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
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}

		return URLConstants.UI_LOGIN_REDIRECT;
	}
	
	
	@GetMapping(value = URLConstants.UI_SHOW_PURCHASE_SCREEN)
	public String showStudentPurchaseCourseForm(Model model, 
			HttpServletRequest request, HttpSession session) {
		
		CustomUtils.setupSessionForBredCrumbIfNot(request);
		
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
	public String showDeletePurchasedCourseForm(Model model, 
			HttpServletRequest request, HttpSession session) {
		
		CustomUtils.setupSessionForBredCrumbIfNot(request);
		
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
	public ModelAndView getAllCoures(HttpServletRequest request ) {
		 
		CustomUtils.setupSessionForBredCrumbIfNot(request);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("avaiableCourses");
		modelAndView.addObject("allCourses", studentClient.getAllCourses());
		return modelAndView;
	}
	
	@GetMapping(value = {"/", "welcome", "showHome"})
	public ModelAndView welcome(HttpServletRequest request) {
		
		HttpSession session = 
				CustomUtils.setupSessionForBredCrumbIfNot(request);
		
		String email = (String) session.getAttribute(SecurityConstants.EMAIL_ID);
		
		if(email == null) {
			session.setAttribute(SecurityConstants.EMAIL_ID, 
					SecurityConstants.DUMMY_EMAIL);
		}
		
		if(session.getAttribute("allCourses") == null) {
			
			session.setAttribute("allCourses", studentClient
				.getAllCourses().getBody().getResponseDetail());
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("welcome");
		modelAndView.addObject("allCourses", 
				session.getAttribute("allCourses"));
		
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "searchCourseByDomain")
	public ModelAndView searchCoursesByDomain(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		HttpSession session = 
				CustomUtils.setupSessionForBredCrumbIfNot(request);
		
		String email = (String) session.getAttribute(SecurityConstants.EMAIL_ID);
		
		if(email == null) {
			session.setAttribute(SecurityConstants.EMAIL_ID, 
					SecurityConstants.DUMMY_EMAIL);
		}
		
		Object allCoursesByDomainMap = session.getAttribute("allCoursesByDomain");
		
		if(allCoursesByDomainMap == null) {
			
			allCoursesByDomainMap = (Map<String, List<CourseDto>>)
			studentClient.searchCoursesByDomain().getBody().getResponseDetail();
			
			session.setAttribute("allCoursesByDomain", allCoursesByDomainMap);
		}
		
		modelAndView.setViewName("allCoursesByDomain");
		modelAndView.addObject("allCoursesByDomain", allCoursesByDomainMap);
	
		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "searchCourseByDomainAndRating")
	public ModelAndView searchCoursesByDomainAndRating(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		HttpSession session = 
				CustomUtils.setupSessionForBredCrumbIfNot(request);
		
		String email = (String) session.getAttribute(SecurityConstants.EMAIL_ID);
		
		if(email == null) {
			session.setAttribute(SecurityConstants.EMAIL_ID, 
					SecurityConstants.DUMMY_EMAIL);
		}
		
		Object allCoursesByDomainAndRatingMap = 
		(Map<String, Map<Double, List<CourseDto>>>)
		session.getAttribute("allCoursesByDomainAndRatingMap"); 
		
		if(allCoursesByDomainAndRatingMap == null) {
		
			allCoursesByDomainAndRatingMap = 
				studentClient.searchCoursesByDomainAndRating().getBody().getResponseDetail();
			
			session.setAttribute("allCoursesByDomainAndRatingMap",allCoursesByDomainAndRatingMap);
		}
		
		
		modelAndView.setViewName("allCoursesByDomainAndRating");
		modelAndView.addObject("allCoursesByDomainAndRatingMap", 
				allCoursesByDomainAndRatingMap);
	
		return modelAndView;
	}
		
}