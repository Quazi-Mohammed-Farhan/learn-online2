package com.learn.online.exceptions.handlers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.NonUniqueResultException;
import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.learn.online.exceptions.CourseNotFoundtException;
import com.learn.online.exceptions.CourseServiceException;
import com.learn.online.exceptions.LearnOnLineException;
import com.learn.online.exceptions.StudentServiceException;
import com.learn.online.responses.ErrorMessageResponse;

@ControllerAdvice
public class LearnOnlineExceptionHandler {

	
	private static Logger LOGGER = LogManager.getLogger(LearnOnlineExceptionHandler.class);
	
	@ExceptionHandler(value = {StudentServiceException.class})
	public ResponseEntity<Object> handleStudentServiceException(
			StudentServiceException ex, WebRequest wb) {
		
		LOGGER.info("handleStudentServiceException::handleStudentServiceException() handler started");
		
		LOGGER.info("Exception: {} ", ex.getMessage());
		
		LOGGER.info("handleStudentServiceException::handleStudentServiceException() handler ended");
		
		return new ResponseEntity<>(new ErrorMessageResponse(LocalDate.now(), ex.getMessage()),
				new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	@ExceptionHandler(value = {CourseServiceException.class})
	public ResponseEntity<Object> handleCourserServiceException(
			CourseServiceException ex, WebRequest wb) {
		
		LOGGER.info("handleStudentServiceException::handleCourserServiceException() handler started");
		
		LOGGER.info("Exception: {} ", ex.getMessage());
		
		LOGGER.info("handleStudentServiceException::handleCourserServiceException() handler ended");
		
		return new ResponseEntity<>(new ErrorMessageResponse(LocalDate.now(), ex.getMessage()),
				new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {CourseNotFoundtException.class})
	public ResponseEntity<Object> handleCourseNotFoundException(
			CourseNotFoundtException ex, WebRequest web) {
		
		LOGGER.info("handleStudentServiceException::handleCourseNotFoundException() handler started");
		
		LOGGER.info("Exception: {} ", ex.getMessage());
		
		LOGGER.info("handleStudentServiceException::handleCourseNotFoundException() handler ended");
		
		return new ResponseEntity<>(new ErrorMessageResponse(LocalDate.now(),
				ex.getMessage()), new HttpHeaders(),  HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(value = {LearnOnLineException.class})
	public ResponseEntity<Object> handleLearnOnLineException(
			LearnOnLineException ex, WebRequest rq) {
		
		LOGGER.info("handleStudentServiceException::handleLearnOnLineException() handler start");
		
		LOGGER.info("Exception: {} ", ex.getMessage());
		
		
		LOGGER.info("handleStudentServiceException::handleLearnOnLineException() handler ended");
		
		return new ResponseEntity<>(
				new ErrorMessageResponse(LocalDate.now(), ex.getMessage()),
				new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handleConstraintViolation(ConstraintViolationException ex) {
	    
		LOGGER.info("handleStudentServiceException::handleConstraintViolation() handler Started");
		
		LOGGER.info("Exception: {}", ex.getMessage());
		
		Map<String, String> errors = new HashMap<>();
	     
	    ex.getConstraintViolations().forEach(cv -> {
	        errors.put("message", cv.getMessage());
	        errors.put("path", (cv.getPropertyPath()).toString());
	    }); 
	 
	    
	    LOGGER.info("Error: {}", errors);
	    LOGGER.info("handleStudentServiceException::handleConstraintViolation() handler ended");
	    return errors;
	}
	
	
	@ExceptionHandler(value = {NonUniqueResultException.class})
	public ResponseEntity<Object> handleNonUniqueResultException(NonUniqueResultException ex,  
				WebRequest wb) {
		
		LOGGER.info("handleStudentServiceException::handleNonUniqueResultException() handler Started");
		
		LOGGER.info("Exception: {}", ex.getMessage());
		
		LOGGER.info("handleStudentServiceException::handleNonUniqueResultException() handler ended");
		
		return new ResponseEntity<>(new ErrorMessageResponse(LocalDate.now(), ex.getMessage()), 
				new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
