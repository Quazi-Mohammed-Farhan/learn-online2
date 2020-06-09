package com.learn.online.exceptions.handlers;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Scanner;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.online.enums.ErrorMessagesEnum;
import com.learn.online.enums.ResponseStatus;
import com.learn.online.exceptions.CourseToCancellationExpiredClientSideException;
import com.learn.online.exceptions.CourseToPurchaseDoesNotExistsClientSideException;
import com.learn.online.exceptions.DuplicateCoursePurchaseClientSideException;
import com.learn.online.exceptions.InvalidCourseKeyClientSideException;
import com.learn.online.exceptions.InvalidCourseOrderKeyClientSideException;
import com.learn.online.responses.LearnOnlineResponse;
import com.learn.online.responses.StudentResponse;
import com.learn.online.utils.GeneralResponseBody;

@ControllerAdvice
public class RequestExceptionHandler implements ResponseErrorHandler {


 @ExceptionHandler
 public ResponseEntity<LearnOnlineResponse<StudentResponse>> handleException(DuplicateCoursePurchaseClientSideException exc) {
	 
  StudentResponse studentResponse = new StudentResponse();
	studentResponse.setStudentKey("No-Student-Key");
  
  LearnOnlineResponse<StudentResponse> error =  LearnOnlineResponse.build(studentResponse, 
			ErrorMessagesEnum.BUYING_DUPLICATE_COURSES.getMessage(), 
			ResponseStatus.ERROR.name());
  
  return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
 }
 
 @ExceptionHandler
 public ResponseEntity<LearnOnlineResponse<StudentResponse>> handleException(CourseToPurchaseDoesNotExistsClientSideException exc) {
	 
  StudentResponse studentResponse = new StudentResponse();
	studentResponse.setStudentKey("No-Student-Key");
  
  LearnOnlineResponse<StudentResponse> error =  LearnOnlineResponse.build(studentResponse, 
			ErrorMessagesEnum.REQUESTED_COURSES_NOT_FOUND_FOR_PURCHASE.getMessage(), 
			ResponseStatus.ERROR.name());
  
  return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
 }
 
 @ExceptionHandler
 public ResponseEntity<LearnOnlineResponse<StudentResponse>> handleException(InvalidCourseKeyClientSideException exc) {
	 
  StudentResponse studentResponse = new StudentResponse();
	studentResponse.setStudentKey("No-Student-Key");
  
  LearnOnlineResponse<StudentResponse> error =  LearnOnlineResponse.build(studentResponse, 
			ErrorMessagesEnum.INVALID_OR_EMPTY_COURSE_KEY_FOUND.getMessage(), 
			ResponseStatus.ERROR.name());
  
   return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
 }
 
 @ExceptionHandler
 public ResponseEntity<LearnOnlineResponse<StudentResponse>> handleException(InvalidCourseOrderKeyClientSideException exc) {
	 
  StudentResponse studentResponse = new StudentResponse();
	studentResponse.setStudentKey("No-Student-Key");
  
  LearnOnlineResponse<StudentResponse> error =  LearnOnlineResponse.build(studentResponse, 
			ErrorMessagesEnum.INVALID_OR_EMPTY_COURSE_ORDER_KEY_FOUND.getMessage(), 
			ResponseStatus.ERROR.name());
  
   return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
 }
 
 @ExceptionHandler
 public ResponseEntity<LearnOnlineResponse<StudentResponse>> handleException(CourseToCancellationExpiredClientSideException exc) {
	 
  StudentResponse studentResponse = new StudentResponse();
	studentResponse.setStudentKey("No-Student-Key");
  
  LearnOnlineResponse<StudentResponse> error =  LearnOnlineResponse.build(studentResponse, 
			ErrorMessagesEnum.COURSES_EXCEED_30DAYS_CAN_NOT_BE_DELETED.getMessage(), 
			ResponseStatus.ERROR.name());
  
   return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
 }
 
 
 @Override
 public boolean hasError(ClientHttpResponse response) throws IOException {
  return (
		  response.getStatusCode().series() == Series.CLIENT_ERROR 
          || response.getStatusCode().series() == Series.SERVER_ERROR);
 }

 @Override
 public void handleError(ClientHttpResponse response) throws IOException {
	 
	 String responseAsString = toString(response.getBody());
	 GeneralResponseBody generalResponseBody  = new ObjectMapper().readValue(responseAsString.getBytes(), GeneralResponseBody.class);
	 
	 String message = generalResponseBody.getMessage().substring(0, 25);
	 if(ErrorMessagesEnum.REQUESTED_COURSES_NOT_FOUND_FOR_PURCHASE.getMessage().indexOf(message) != -1) {
		 throw new CourseToPurchaseDoesNotExistsClientSideException(ErrorMessagesEnum.REQUESTED_COURSES_NOT_FOUND_FOR_PURCHASE.getMessage());
	 } else if (ErrorMessagesEnum.BUYING_DUPLICATE_COURSES.getMessage().indexOf(message) != -1) {
		 throw new DuplicateCoursePurchaseClientSideException(ErrorMessagesEnum.BUYING_DUPLICATE_COURSES.getMessage());
	 } else if (ErrorMessagesEnum.INVALID_OR_EMPTY_COURSE_KEY_FOUND.getMessage().indexOf(message) != -1) {
		 throw new InvalidCourseOrderKeyClientSideException(ErrorMessagesEnum.INVALID_OR_EMPTY_COURSE_KEY_FOUND.getMessage());
	 } else if (ErrorMessagesEnum.INVALID_OR_EMPTY_COURSE_ORDER_KEY_FOUND.getMessage().indexOf(message) != -1) {
		 throw new InvalidCourseOrderKeyClientSideException(ErrorMessagesEnum.INVALID_OR_EMPTY_COURSE_ORDER_KEY_FOUND.getMessage());
	 } else if (ErrorMessagesEnum.COURSES_EXCEED_30DAYS_CAN_NOT_BE_DELETED.getMessage().indexOf(message) != -1) {
		 throw new CourseToCancellationExpiredClientSideException(ErrorMessagesEnum.COURSES_EXCEED_30DAYS_CAN_NOT_BE_DELETED.getMessage());
	 }
	 	 
 }
 
 @Override
 public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
		handleError(response);
	}
 
 
 String toString(InputStream inputStream) {
    @SuppressWarnings("resource")
	Scanner s = new Scanner(inputStream).useDelimiter("\\A");
     return s.hasNext() ? s.next() : "";
 }
}