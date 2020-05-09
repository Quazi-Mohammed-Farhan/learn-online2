package com.learn.online.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;

import com.learn.online.dtos.StudentDto;
import com.learn.online.dummies.DummyData;
import com.learn.online.services.CourseService;
import com.learn.online.services.StudentService;
import com.learn.online.utils.URLConstants;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentMgmtControllerKURDTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StudentService studentService;
	
	@MockBean
	private CourseService courseService;
	
	@Test
	public void testCreateStudent() throws Exception {
		
		Mockito.when(studentService.signupStudent(Mockito.any(StudentDto.class)))
						.thenReturn(DummyData.getStudentDto());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URLConstants.STUDENT_SINGN_UP_URL)
						.accept(MediaType.APPLICATION_JSON).content(DummyData.STUDENT_JSON_INPUT)
						.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals(DummyData.STUDENT_JSON_CREATE_OUTPUT, 
				mvcResult.getResponse().getContentAsString(), false);	
	}
	
	@Test
	public void updateStudentTest() throws Exception {
		
		Mockito.when(studentService.updateStudent(Mockito.any(StudentDto.class)))
		.thenReturn(DummyData.getStudentDto());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URLConstants.STUDENT_UPDATE_URL)
				.accept(MediaType.APPLICATION_JSON).content(DummyData.STUDENT_JSON_INPUT)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		JSONAssert.assertEquals(DummyData.STUDENT_JSON_UPDATE_OUTPUT, 
				mvcResult.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void purchaseCourseTest() throws Exception {
		
		Mockito.when(studentService.purchaseCourses(Mockito.anyString(),
				Mockito.anyList())).thenReturn(DummyData.getStudentDtoPurchasingCourses());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
					.post(URLConstants.STUDENT_PURCHASE_COURSES_URL).accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.content(DummyData.COURSES_TO_BUY_JSON_INPUT);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals(DummyData.PURCHASED_COURSES_EXPECTED_RESPONSE, 
				mvcResult.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void cancelPurchaseCourseTest() throws Exception {
		
		Mockito.when(studentService.cancellPurchasedCourses(Mockito.anyString(),
				Mockito.anyList())).thenReturn(DummyData.getStudentDtoPurchasingCourses());

		RequestBuilder requestBuilder = MockMvcRequestBuilders
					.delete(URLConstants.STUDENT_CANCEL_PURCHASED_COURSES_URL).accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.content(DummyData.COURSES_TO_CANCEL_JSON_INPUT);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals(DummyData.CANCELLED_COURSES_EXPECTED_RESPONSE, 
				mvcResult.getResponse().getContentAsString(), false);
	}

}