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

import com.learn.online.dummies.DummyData;
import com.learn.online.services.CourseService;
import com.learn.online.services.StudentService;
import com.learn.online.utils.URLConstants;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentMgmtControllerSearchTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StudentService studentService;
	
	@MockBean
	private CourseService courseService;
	
	@Test
	public void testWelcome() throws Exception {
		
		Mockito.when(courseService.findAllCoursesGroupByDomainAndRating())
							.thenReturn(DummyData.getCourseResponseByRatingAndDomainMap());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URLConstants.STUDENT_WELCOME_URL)
						.accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals(DummyData.COURSES_BY_DOMAIN_AND_RATING_EXPECTED_VALUE, 
				mvcResult.getResponse().getContentAsString(), false);
		
	}
		
	@Test
	public void searchCoursesByDomainAndRating() throws Exception {
		
		Mockito.when(courseService.findAllCoursesGroupByDomainAndRating())
					.thenReturn(DummyData.getCourseResponseByRatingAndDomainMap());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URLConstants.SEARCH_COURSES_BY_DOMAIN_AND_RATING)
							.accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals(DummyData.COURSES_BY_DOMAIN_AND_RATING_EXPECTED_VALUE, 
					mvcResult.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void searchCoursesByDomain() throws Exception {
		
		Mockito.when(courseService.findAllCoursesGroupByDomain())
					.thenReturn(DummyData.getCourseResponseBydDomainMap());

		RequestBuilder requestBuilder = MockMvcRequestBuilders
					.get(URLConstants.SEARCH_COURSES_BY_DOMAIN).accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals(DummyData.COURSES_BY_DOMAIN_EXPECTED_VALUE, 
				mvcResult.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void testSearchByEmail() throws Exception {
		
		
		Mockito.when(studentService.findByEmail(Mockito.anyString()))
					.thenReturn(DummyData.getStudentByEmail());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/learn/search/farhan@gmail.com")
					.accept(MediaType.APPLICATION_JSON);
		
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals(DummyData.STUDENT_BY_EMAIL, 
					mvcResult.getResponse().getContentAsString(), false);
	}
	
}
