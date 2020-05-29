package com.learn.online.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.learn.online.LearnOnlineApplication;
import com.learn.online.dummies.DummyData;
import com.learn.online.enums.ResponseMessages;
import com.learn.online.enums.ResponseStatus;
import com.learn.online.securities.SecurityConstants;
import com.learn.online.services.CourseService;
import com.learn.online.services.StudentService;
import com.learn.online.utils.URLConstants;

import static org.junit.Assert.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LearnOnlineApplication.class})
public class StudentMgmtControllerSearchTest {

	private MockMvc mockMvc;
	
	@Mock
	private StudentService studentService;
	
	@Mock
	private CourseService courseService;
	
	@InjectMocks
	private StudentMgmtController studentMgmtController;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(studentMgmtController).build();
	}
	
	@Test
	public void testWelcome() throws Exception {
		
		mockMvc.perform(get(URLConstants.STUDENT_WELCOME_URL)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message")
						.value("Courses search by domain and rating is successful"))
				.andExpect(jsonPath("$.status").value("SUCCESS"));
			
		Mockito.verify(courseService, Mockito.times(1)).findAllCoursesGroupByDomainAndRating();
		
	}
	
	@Test
	public void searchCoursesByDomainAndRating() throws Exception {
		
		Mockito.when(courseService.findAllCoursesGroupByDomainAndRating())
					.thenReturn(DummyData.getCourseResponseByRatingAndDomainMap());
		
		mockMvc.perform(get(URLConstants.SEARCH_COURSES_BY_DOMAIN_AND_RATING)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Courses search by domain and "
						+ "rating is successful"))
				.andExpect(jsonPath("$.status").value("SUCCESS"));
		
		Mockito.verify(courseService, Mockito.times(1)).findAllCoursesGroupByDomainAndRating();
	}
	
	@Test
	public void searchCoursesByDomain() throws Exception {
		
		Mockito.when(courseService.findAllCoursesGroupByDomain())
					.thenReturn(DummyData.getCourseResponseBydDomainMap());
		
		mockMvc.perform(get(URLConstants.SEARCH_COURSES_BY_DOMAIN)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message")
				.value(ResponseMessages.COURSES_SEARCH_BY_DOMAIN_RATING.getResponseMessage()))
				.andExpect(jsonPath("$.status").value("SUCCESS"));
		
		Mockito.verify(courseService, Mockito.times(1)).findAllCoursesGroupByDomain();
	}
		
	@Test
	public void testSearchByEmail() throws Exception {
		
		Mockito.when(studentService.findByEmail(Mockito.anyString()))
					.thenReturn(DummyData.getStudentByEmail());
		
		 mockMvc.perform(get(URLConstants.SEARCH_STUDENT_BY_EMAIL, 
				 	DummyData.getStudentByEmail())
				 .accept(MediaType.APPLICATION_JSON)
				 .header(SecurityConstants.HEADER_STRING, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWI"
				 		+ "iOiJhZG1pbkBnbWFpbC5jb20iLCJleHAiOjE1OTEyNzE0NTN9.S2os_lLtoQhzx"
				 		+ "FeYp_rQcVuzJA54w8a2xtL1cLbVi3OGAdCA8vcg6XD5ytI2D1fPROGEa9PgTfCU"
				 		+ "pp3tE97Sgw"))
		 		 .andExpect(status().isOk())
		 		 .andExpect(jsonPath("$.message")
		 				 .value(ResponseMessages.DATA_FOUND.getResponseMessage()))
		 		 .andExpect(jsonPath("$.status").value(ResponseStatus.SUCCESS.name()));
		 
		 Mockito.verify(studentService, Mockito.times(1)).findByEmail(Mockito.anyString());
		
	}
	
}
