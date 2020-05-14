package com.learn.online.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.learn.online.beans.CourseEntity;
import com.learn.online.beans.StudentEntity;
import com.learn.online.dtos.CourseDto;
import com.learn.online.dtos.StudentDto;
import com.learn.online.dummies.DummyData;

class CustomUtilTest {

	@Test
	void testConvertoToStudentDto() {
	
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setActive(true);
		studentEntity.setCity("Bangalore");
		studentEntity.setCountry("India");
		studentEntity.setCreationtDate(LocalDate.now());
		studentEntity.setEmail("farhan@gmail.com");
		studentEntity.setEncryptedPassword("abcd");
		studentEntity.setFirstName("Farhan");
		studentEntity.setLastName("Quazi");
		studentEntity.setPhone("1234567892");
		studentEntity.setState("KA");
		studentEntity.setStudentKey(CustomUtils.getSHA256());
		
		StudentDto studentDto = CustomUtils.convertToStudentDto(studentEntity);
		
		assertNotNull(studentDto);
		assertTrue(studentEntity.isActive() == studentDto.isActive()
				&& studentEntity.getCity().equals(studentDto.getCity())
				&& studentEntity.getCountry().equals(studentDto.getCountry())
				&& studentEntity.getCreationtDate().equals(studentDto.getCreationtDate())
				&& studentEntity.getEmail().equals(studentDto.getEmail())
				&& studentEntity.getEncryptedPassword().equals(studentDto.getEncryptedPassword())
				&& studentEntity.getFirstName().equals(studentDto.getFirstName())
				&& studentEntity.getLastName().equals(studentDto.getLastName())
				&& studentEntity.getPhone().equals(studentDto.getPhone())
				&& studentEntity.getState().equals(studentDto.getState()));
	}
	
	@Test
	void testConvertoToStudentEntity() {
	
		StudentDto studentDto = new StudentDto();
		studentDto.setActive(true);
		studentDto.setCity("Bangalore");
		studentDto.setCountry("India");
		studentDto.setCreationtDate(LocalDate.now());
		studentDto.setEmail("farhan@gmail.com");
		studentDto.setEncryptedPassword("abcd");
		studentDto.setFirstName("Farhan");
		studentDto.setLastName("Quazi");
		studentDto.setPhone("1234567892");
		studentDto.setState("KA");
		studentDto.setStudentKey(CustomUtils.getSHA256());
		
		StudentEntity studentEntity = CustomUtils.convertToStudentEntity(studentDto);
		
		assertNotNull(studentEntity);
		assertTrue(studentDto.isActive() == studentEntity.isActive()
				&& studentDto.getCity().equals(studentEntity.getCity())
				&& studentDto.getCountry().equals(studentEntity.getCountry())
				&& studentDto.getCreationtDate().equals(studentEntity.getCreationtDate())
				&& studentDto.getEmail().equals(studentEntity.getEmail())
				&& studentDto.getEncryptedPassword().equals(studentEntity.getEncryptedPassword())
				&& studentDto.getFirstName().equals(studentEntity.getFirstName())
				&& studentDto.getLastName().equals(studentEntity.getLastName())
				&& studentDto.getPhone().equals(studentEntity.getPhone())
				&& studentDto.getState().equals(studentEntity.getState()));
	}
	
	@Test
	void testConvertoToCourseEntityList() {
	
		List<CourseDto> courseDtoList = DummyData.getAllCourses();
		
		List<CourseEntity> courseEntityList = CustomUtils.convertToCourseEntityList(courseDtoList);
		
		assertTrue(courseEntityList != null);
		assertTrue(courseEntityList.size() == courseDtoList.size());
		
		int count = 0;
		for(CourseDto courseDto : courseDtoList) {
			for(CourseEntity courseEntity : courseEntityList) {
				if(courseEntity.getChapters().equals(courseDto.getChapters())
						&& courseEntity.getCourseKey().equals(courseDto.getCourseKey())
						&& courseEntity.getCourseName().equals(courseDto.getCourseName())
						&& courseEntity.getDescription().equals(courseDto.getDescription())
						&& courseEntity.getDomainName().equals(courseDto.getDomainName())
						&& courseEntity.getDurationInHours().equals(courseDto.getDurationInHours())
						&& courseEntity.getPrice().equals(courseDto.getPrice())
						&& courseEntity.getRating().equals(courseDto.getRating())) {
					count++;
				}
			}
		}
		
		assertEquals(courseDtoList.size(), count);
		assertTrue(courseEntityList.size() == courseDtoList.size());
		
	}
	
	@Test
	void testConvertoToCourseDtoList() {
	
		List<CourseEntity> courseEntityList = CustomUtils.convertToCourseEntityList(DummyData.getAllCourses());
		
		List<CourseDto> courseDtoList = CustomUtils.convertToCourseDtoList(courseEntityList);
		
		assertTrue(courseDtoList != null);
		assertTrue(courseDtoList.size() == courseEntityList.size());
		
		int count = 0;
		for(CourseEntity courseEntity : courseEntityList) {
			for(CourseDto courseDto : courseDtoList) {
				if(courseDto.getChapters().equals(courseEntity.getChapters())
						&& courseDto.getCourseKey().equals(courseEntity.getCourseKey())
						&& courseDto.getCourseName().equals(courseEntity.getCourseName())
						&& courseDto.getDescription().equals(courseEntity.getDescription())
						&& courseDto.getDomainName().equals(courseEntity.getDomainName())
						&& courseDto.getDurationInHours().equals(courseEntity.getDurationInHours())
						&& courseDto.getPrice().equals(courseEntity.getPrice())
						&& courseDto.getRating().equals(courseEntity.getRating())) {
					count++;
				}
			}
		}
		
		assertEquals(courseEntityList.size(), count);
		assertTrue(courseDtoList.size() == courseEntityList.size());
		
	}
	
	@Test
	void testConvertoToCourseEntity() {
	
		CourseDto courseDto = new CourseDto();
		
		courseDto.setChapters(100);
		courseDto.setCourseId(1L);
		courseDto.setCourseKey(CustomUtils.getSHA256());
		courseDto.setCourseName("Sample Course");
		courseDto.setCreationtDate(LocalDate.now());
		courseDto.setDescription("Sample Description");
		courseDto.setDomainName("Java");
		courseDto.setDurationInHours(1000);
		courseDto.setLastUpdateDate(LocalDate.now());
		courseDto.setPrice(500D);
		courseDto.setRating(5D);
		
		CourseEntity courseEntity = CustomUtils.convertToCourseEntity(courseDto);
		
		assertTrue(courseEntity != null);
		assertTrue(courseEntity.getChapters().equals(courseDto.getChapters())
					&& courseEntity.getCourseKey().equals(courseDto.getCourseKey())
					&& courseEntity.getCourseName().equals(courseDto.getCourseName())
					&& courseEntity.getDescription().equals(courseDto.getDescription())
					&& courseEntity.getDomainName().equals(courseDto.getDomainName())
					&& courseEntity.getDurationInHours().equals(courseDto.getDurationInHours())
					&& courseEntity.getPrice().equals(courseDto.getPrice())
					&& courseEntity.getRating().equals(courseDto.getRating()) 
		
		);
	}
	
	@Test
	void testConvertoToCourseDto() {
	
		CourseEntity courseEntity = new CourseEntity();
		
		courseEntity.setChapters(100);
		courseEntity.setCourseId(1L);
		courseEntity.setCourseKey(CustomUtils.getSHA256());
		courseEntity.setCourseName("Sample Course");
		courseEntity.setCreationtDate(LocalDate.now());
		courseEntity.setDescription("Sample Description");
		courseEntity.setDomainName("Java");
		courseEntity.setDurationInHours(1000);
		courseEntity.setLastUpdateDate(LocalDate.now());
		courseEntity.setPrice(500D);
		courseEntity.setRating(5D);
		
		CourseDto courseDto = CustomUtils.convertToCourseDto(courseEntity);
		
		assertTrue(courseDto != null);
		assertTrue(courseDto.getChapters().equals(courseEntity.getChapters())
					&& courseDto.getCourseKey().equals(courseEntity.getCourseKey())
					&& courseDto.getCourseName().equals(courseEntity.getCourseName())
					&& courseDto.getDescription().equals(courseEntity.getDescription())
					&& courseDto.getDomainName().equals(courseEntity.getDomainName())
					&& courseDto.getDurationInHours().equals(courseEntity.getDurationInHours())
					&& courseDto.getPrice().equals(courseEntity.getPrice())
					&& courseDto.getRating().equals(courseEntity.getRating()) 
		
		);
	}

}
