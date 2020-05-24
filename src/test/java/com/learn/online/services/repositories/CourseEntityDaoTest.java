package com.learn.online.services.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learn.online.beans.CourseEntity;
import com.learn.online.daos.CourseEntityDao;
import com.learn.online.dtos.CourseDto;
import com.learn.online.dummies.DummyData;
import com.learn.online.utils.CustomUtils;

@SpringBootTest
public class CourseEntityDaoTest {

	@Autowired
	CourseEntityDao courseEntityDao;
	
	@Test
	public void findCoursesByTechnologyTest() {

		CustomUtils.convertToCourseEntityList(DummyData.getAllCourses())
			.forEach(courseEntity-> {
				courseEntityDao.save(courseEntity);
			});
		
		Optional<List<CourseEntity>> dbCourseEntityList = courseEntityDao.findCoursesByTechnology("Java");
		
		assertTrue(dbCourseEntityList.isPresent());
		assertTrue(dbCourseEntityList.get().stream().filter(courseEntity->
				courseEntity.getDomainName() != null && courseEntity.getDomainName().length()>0)
				.filter(courseEntity->courseEntity.getDomainName().equals("Java")).count() > 0);
	}
	
	@Test
	public void findCoursesByNameTest() {

		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setChapters(100);
		courseEntity.setCourseKey(CustomUtils.getSHA256());
		courseEntity.setCourseName("Sample Course");
		courseEntity.setCreationtDate(LocalDate.now());
		courseEntity.setDescription("Sample Description");
		courseEntity.setDomainName("Java");
		courseEntity.setDurationInHours(800);
		courseEntity.setPrice(500D);
		courseEntity.setRating(5D);
		courseEntityDao.save(courseEntity);
		
		Optional<List<CourseEntity>> courseEntityList = courseEntityDao.findByName("Sample Course");
		
		assertTrue(courseEntityList.isPresent());
		assertTrue(courseEntityList.get().stream().filter(dbCourseEntity->
				dbCourseEntity.getCourseName() != null && dbCourseEntity.getCourseName().length()>0)
				.filter(dbCourseEntity->dbCourseEntity.getCourseName().equals("Sample Course")).count() > 0);
	}
	
	
	@Test
	public void findCoursesByKeyTest() {

		List<CourseDto> courseDtoList = DummyData.getAllCourses();
		
		CustomUtils.convertToCourseEntityList(courseDtoList)
		.forEach(courseEntity-> {
			courseEntityDao.save(courseEntity);
		});

		List<String> coursesKeysList = courseDtoList.stream()
				.filter(courseDto->courseDto.getDomainName().equals("Java"))
				.map(courseDto->courseDto.getCourseKey()).collect(Collectors.toList());
	
		
		Optional<List<CourseEntity>> dbCourseEntityList = courseEntityDao.findCoursesByTechnology("Java");
		
		assertTrue(dbCourseEntityList.isPresent());
		
		List<String> dbCoursesKeysList = dbCourseEntityList.get().stream()
				.map(courseEntity->courseEntity.getCourseKey()).collect(Collectors.toList());
			
		
		assertTrue(dbCoursesKeysList.size() >= coursesKeysList.size());
		assertTrue(dbCoursesKeysList.containsAll(coursesKeysList));
		
	}
	
}

