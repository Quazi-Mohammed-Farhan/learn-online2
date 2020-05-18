package com.learn.online.utils;

public interface URLConstants {
	
	public static final String BASE_URL = "http://localhost:8080/learn-online/";
	public static final String STUDENT_SINGN_UP_URL = "/learn";
	public static final String STUDENT_UPDATE_URL = "/learn";
	public static final String STUDENT_PURCHASE_COURSES_URL = "/learn/buy";
	public static final String STUDENT_CANCEL_PURCHASED_COURSES_URL = "/learn/cancel";
	public static final String STUDENT_WELCOME_URL = "/learn";
	public static final String SEARCH_COURSES_BY_DOMAIN= "/learn/coursesByDomain";
	public static final String SEARCH_COURSES_BY_DOMAIN_AND_RATING = "/learn/coursesByDomainAndRating";
	public static final String SARCH_STUDENT_BY_EMAIL = "/learn/search/{email}";
	
	
	public static final String STUDENT_WELCOME_VIEW_URL = "/learn/view";
	public static final String STUDENT_SHOW_SIGNUP_VIEW_URL = "/learn/view/show-signup";
	public static final String STUDENT_SIGNUP_VIEW_URL = "/learn/view/signup";
	
	public static final String STUDENT_SHOW_UPDATE_VIEW = "/learn/view/show-update";
	public static final String STUDENT_UPDATE_VIEW = "/learn/view/update";
}