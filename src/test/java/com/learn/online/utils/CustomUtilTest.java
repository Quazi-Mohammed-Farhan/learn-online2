package com.learn.online.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.learn.online.beans.CourseEntity;
import com.learn.online.beans.StudentEntity;
import com.learn.online.daos.RoleEntityDao;
import com.learn.online.dtos.CourseDto;
import com.learn.online.dtos.StudentDto;
import com.learn.online.dummies.DummyData;
import com.learn.online.enums.SecurityRolesAndAuthorities;

@SpringBootTest
class CustomUtilTest {

	
	@MockBean 
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@MockBean
	RoleEntityDao roleEntityDao;
	
	@Test
	void testConvertoToStudentDto() {
		
		
		StudentEntity studentEntity = DummyData.getStudentEntityForCreation2();
		
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
	void testConvertoToStudentDtoWithCourseOrders() {
		
		
		StudentEntity studentEntity = DummyData.getStudentEntityForCreation2();
		
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
		
		StudentEntity studentEntity = DummyData.getStudentEntityForCreation2();
		
		StudentEntity resultStudentEntity = CustomUtils.convertToStudentEntity(
				CustomUtils.convertToStudentDto(DummyData.getStudentEntityForCreation2()));
		
		assertNotNull(studentEntity);
		assertTrue(studentEntity.isActive() == resultStudentEntity.isActive()
				&& studentEntity.getCity().equals(resultStudentEntity.getCity())
				&& studentEntity.getCountry().equals(resultStudentEntity.getCountry())
				&& studentEntity.getCreationtDate().equals(resultStudentEntity.getCreationtDate())
				&& studentEntity.getEmail().equals(resultStudentEntity.getEmail())
				&& studentEntity.getEncryptedPassword().equals(resultStudentEntity.getEncryptedPassword())
				&& studentEntity.getFirstName().equals(resultStudentEntity.getFirstName())
				&& studentEntity.getLastName().equals(resultStudentEntity.getLastName())
				&& studentEntity.getPhone().equals(resultStudentEntity.getPhone())
				&& studentEntity.getState().equals(resultStudentEntity.getState()));
	}
	
	
	@Test
	public void testconvertToStudentEntityWithRoleAndPasswordEncryption() {
		
		//public static StudentEntity convertToStudentEntity(StudentDto studdentDto, 
		//BCryptPasswordEncoder bCryptPasswordEncoder, RoleEntityDao roleEntityDao)
		
		StudentEntity studentEntity = DummyData.getStudentEntityForCreation2();
		
		Mockito.when(bCryptPasswordEncoder.encode(Mockito.anyString())).thenReturn("password");
		
		Mockito.when(roleEntityDao.findByName(Mockito.anyString()))
			.thenReturn(Optional.of(DummyData.getStudentEntityForCreation3()
					.getRoles().iterator().next()));

		
		StudentEntity resultStudentEntity = CustomUtils.convertToStudentEntity(
				CustomUtils.convertToStudentDto(DummyData.getStudentEntityForCreation2()), 
					bCryptPasswordEncoder, roleEntityDao);
		
		studentEntity.setEncryptedPassword("password");
		resultStudentEntity.setEncryptedPassword("password");
		
		assertNotNull(studentEntity);
		assertTrue(studentEntity.isActive() == resultStudentEntity.isActive()
				&& studentEntity.getCity().equals(resultStudentEntity.getCity())
				&& studentEntity.getCountry().equals(resultStudentEntity.getCountry())
				&& studentEntity.getEmail().equals(resultStudentEntity.getEmail())
				&& studentEntity.getEncryptedPassword().equals(resultStudentEntity.getEncryptedPassword())
				&& studentEntity.getFirstName().equals(resultStudentEntity.getFirstName())
				&& studentEntity.getLastName().equals(resultStudentEntity.getLastName())
				&& studentEntity.getPhone().equals(resultStudentEntity.getPhone())
				&& studentEntity.getState().equals(resultStudentEntity.getState()));
		
		
	}
	
	
	@Test
	public void testconvertToStudentEntityWithRoleAndPasswordEncryptionWithNonCourseOrders() {
		
		//public static StudentEntity convertToStudentEntity(StudentDto studdentDto, 
		//BCryptPasswordEncoder bCryptPasswordEncoder, RoleEntityDao roleEntityDao)
		
		StudentEntity studentEntity = DummyData.getStudentEntityForCreation3();
		
		Mockito.when(bCryptPasswordEncoder.encode(Mockito.anyString())).thenReturn("password");
		
		Mockito.when(roleEntityDao.findByName(Mockito.anyString()))
			.thenReturn(Optional.of(DummyData.getStudentEntityForCreation3()
					.getRoles().iterator().next()));

		
		StudentEntity resultStudentEntity = CustomUtils.convertToStudentEntity(
				CustomUtils.convertToStudentDto(DummyData.getStudentEntityForCreation3()), 
					bCryptPasswordEncoder, roleEntityDao);
		
		studentEntity.setEncryptedPassword("password");
		resultStudentEntity.setEncryptedPassword("password");
		
		assertNotNull(studentEntity);
		assertTrue(studentEntity.isActive() == resultStudentEntity.isActive()
				&& studentEntity.getCity().equals(resultStudentEntity.getCity())
				&& studentEntity.getCountry().equals(resultStudentEntity.getCountry())
				&& studentEntity.getEmail().equals(resultStudentEntity.getEmail())
				&& studentEntity.getEncryptedPassword().equals(resultStudentEntity.getEncryptedPassword())
				&& studentEntity.getFirstName().equals(resultStudentEntity.getFirstName())
				&& studentEntity.getLastName().equals(resultStudentEntity.getLastName())
				&& studentEntity.getPhone().equals(resultStudentEntity.getPhone())
				&& studentEntity.getState().equals(resultStudentEntity.getState()));
		
		
	}
	
	@Test
	void testConvertoToStudentEntityWithCourseOrder() {
		
		StudentEntity studentEntity = DummyData.getStudentEntityForCreation2();
		
		StudentEntity resultStudentEntity = CustomUtils.convertToStudentEntity(
				CustomUtils.convertToStudentDto(DummyData.getStudentEntityForCreation3()));
		
		assertNotNull(studentEntity);
		assertTrue(studentEntity.isActive() == resultStudentEntity.isActive()
				&& studentEntity.getCity().equals(resultStudentEntity.getCity())
				&& studentEntity.getCountry().equals(resultStudentEntity.getCountry())
				&& studentEntity.getCreationtDate().equals(resultStudentEntity.getCreationtDate())
				&& studentEntity.getEmail().equals(resultStudentEntity.getEmail())
				&& studentEntity.getEncryptedPassword().equals(resultStudentEntity.getEncryptedPassword())
				&& studentEntity.getFirstName().equals(resultStudentEntity.getFirstName())
				&& studentEntity.getLastName().equals(resultStudentEntity.getLastName())
				&& studentEntity.getPhone().equals(resultStudentEntity.getPhone())
				&& studentEntity.getState().equals(resultStudentEntity.getState()));
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
