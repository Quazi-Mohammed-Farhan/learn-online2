package com.learn.online.utils;

public interface URLConstants {
	
	public static final String BASE_URL = "http://localhost:8080/learn-online";
	public static final String STUDENT_SINGN_UP_URL = "/learn";
	public static final String STUDENT_LOGIN_URL = "/login";
	public static final String STUDENT_UPDATE_URL = "/learn";
	public static final String STUDENT_PURCHASE_COURSES_URL = "/learn/buy";
	public static final String STUDENT_CANCEL_PURCHASED_COURSES_URL = "/learn/cancel";
	public static final String STUDENT_WELCOME_URL = "/learn";
	public static final String SEARCH_COURSES_BY_DOMAIN= "/learn/coursesByDomain";
	public static final String SEARCH_COURSES_BY_DOMAIN_AND_RATING = "/learn/coursesByDomainAndRating";
	public static final String SEARCH_STUDENT_BY_EMAIL = "/learn/search/{email}";
	public static final String SEARCH_ALL_COURSES = "/learn/allCourses";
	
	//UI
	public static final String UI_SHOW_STUDENT_SIGNUP_SCREEN = "studentSignup";
	public static final String UI_DO_STUDENT_SIGNUP = "doStudentSignup";
	public static final String UI_SHOW_LOGIN_SCREEN = "showLogin";
	public static final String UI_DO_STUDENT_LOGIN = "doLogin";
	public static final String UI_SHOW_STUDENT_UPDATE_SCREEN = "studentUpdate";
	public static final String UI_DO_STUDENT_UPDATE = "doStudentUpdate";
	public static final String UI_SEARCH_BY_EMAIL = "restclient/search/{email}";
	public static final String UI_STUDENT_UI_LOGOUT_URL = "learn/logout";
	public static final String UI_LOGIN_REDIRECT =  "redirect:/showLogin";
	public static final String UI_RESULT_TEMPATE_NAME = "result";
	public static final String UI_RESPONSE_BODY_NAME = "response";
	public static final String UI_SHOW_PURCHASE_SCREEN = "showPurchaseCourse";
	public static final String UI_SHOW_CANCEL_PURCHASE_SCREEN = "showCancelledPurchaseCourse";
	public static final String PURCHASE_TEMPLATE = "purchaseCourseForm";
	public static final String CANCEL_PURCHASE_TEMPLATE = "cancelPurchasedCourseForm";
	public static final String UI_DO_PURCHASE = "doPurchaseCourse";
	public static final String UI_DO_CANCEL_PURCHASE = "doCancelPurchasedCourse";
	public static final String UI_GET_ALL_COURSES = "uiGetAllCourses";
	
}