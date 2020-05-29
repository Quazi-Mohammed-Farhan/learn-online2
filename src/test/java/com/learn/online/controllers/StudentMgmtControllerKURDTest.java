package com.learn.online.controllers;

import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.learn.online.LearnOnlineApplication;
import com.learn.online.dtos.StudentDto;
import com.learn.online.dummies.DummyData;
import com.learn.online.enums.ResponseMessages;
import com.learn.online.enums.ResponseStatus;
import com.learn.online.securities.SecurityConstants;
import com.learn.online.services.CourseService;
import com.learn.online.services.StudentService;
import com.learn.online.utils.URLConstants;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LearnOnlineApplication.class})
public class StudentMgmtControllerKURDTest {
	
	private MockMvc mockMvc;

	@Mock
	private StudentService studentService;
	
	@Mock
	private CourseService courseService;
	
	@InjectMocks
	StudentMgmtController studentMgmtController;
	
	@Captor
	ArgumentCaptor<StudentDto> studendDtoArgCaptor;
	
	@Before
	public void init() {
		
		  MockitoAnnotations.initMocks(this); 
		  mockMvc = MockMvcBuilders.standaloneSetup(studentMgmtController).build();
	}
	
	@Test
	public void testCreateStudent() throws Exception {
		
		Mockito.when(studentService.signupStudent(Mockito.any(StudentDto.class)))
		  .thenReturn(DummyData.getStudentDto());
		
		mockMvc.perform(post(URLConstants.STUDENT_SINGN_UP_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(DummyData.STUDENT_JSON_INPUT))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message")
					.value(ResponseMessages.STUDENT_ADD_OPERATION_SUCCESSFUL
						.getResponseMessage()))
				.andExpect(jsonPath("$.status")
					.value(ResponseStatus.SUCCESS.name()));
		
		Mockito.verify(studentService, Mockito.times(1))
			.signupStudent(Mockito.any(StudentDto.class));
		
		Mockito.verify(studentService, Mockito.times(1))
		.signupStudent(studendDtoArgCaptor.capture());
		
		assertEquals(DummyData.getStudentDto(), studendDtoArgCaptor.getValue());
	}
	
	@Test
	public void updateStudentTest() throws Exception {
		
		Mockito.when(studentService.updateStudent(Mockito.any(StudentDto.class)))
		.thenReturn(DummyData.getStudentDto());
		
		mockMvc.perform(put(URLConstants.STUDENT_UPDATE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header(SecurityConstants.HEADER_STRING, "99dd322339bd790a9440f7d07d436f752521d8f7"
						+ "8d8065639980748f378f59bf")
				.content(DummyData.STUDENT_JSON_INPUT))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message")
					.value(ResponseMessages.STUDENT_UPDATE_OPERATION_SUCCESSFUL
							.getResponseMessage()))
				.andExpect(jsonPath("$.status").value(ResponseStatus.SUCCESS.name()));
		
		Mockito.verify(studentService, Mockito.times(1))
			.updateStudent(Mockito.any(StudentDto.class));
		
		Mockito.verify(studentService, Mockito.times(1))
		.updateStudent(Mockito.any(StudentDto.class));
		
		Mockito.verify(studentService, Mockito.times(1))
		.updateStudent(studendDtoArgCaptor.capture());
		
		assertEquals(DummyData.getStudentDto(), studendDtoArgCaptor.getValue());
		
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void purchaseCourseTest() throws Exception {
		
		Mockito.when(studentService.purchaseCourses(Mockito.anyString(),
				Mockito.anyList())).thenReturn(DummyData.getStudentDtoPurchasingCourses());
		
		mockMvc.perform(post(URLConstants.STUDENT_PURCHASE_COURSES_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header("Authorization", "99dd322339bd790a9440f7d07d436f752521d8f7"  
						+ "8d8065639980748f378f59bf")
				.content(DummyData.COURSES_TO_BUY_JSON_INPUT))
				.andExpect(jsonPath("$.message").value("Your courses purchased order have "
						+ "been placed successfully"))
				.andExpect(jsonPath("$.status").value("SUCCESS"));

		Mockito.verify(studentService, Mockito.times(1)).purchaseCourses(Mockito.anyString(),
				Mockito.anyList());		
	}
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void cancelPurchaseCourseTest() throws Exception {
		
		Mockito.when(studentService.cancellPurchasedCourses(Mockito.anyString(),
				Mockito.anyList())).thenReturn(DummyData.getStudentDtoPurchasingCourses());
		
		mockMvc.perform(delete(URLConstants.STUDENT_CANCEL_PURCHASED_COURSES_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header(SecurityConstants.HEADER_STRING, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJ"
						+ "zdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJleHAiOjE1OTExOTY1MTF9."
						+ "j5RzuNRE1LZu0fWIyhinAypMOuyWxQrC8KEYq7zGP03qCJNwTrHENTXinXpiM_"
						+ "LFFIUYdoBbbi0LiqK0AZSPsw")
				.content(DummyData.COURSES_TO_CANCEL_JSON_INPUT))
				.andExpect(jsonPath("$.message").value("Courses canncellation request proccessed "
						+ "sucessfully"))
				.andExpect(jsonPath("$.status").value("SUCCESS"));
		
		Mockito.verify(studentService, Mockito.times(1))
		.cancellPurchasedCourses(Mockito.anyString(), Mockito.anyList());
		
	}
}