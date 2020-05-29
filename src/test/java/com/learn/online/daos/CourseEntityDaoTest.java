package com.learn.online.daos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learn.online.LearnOnlineApplication;
import com.learn.online.beans.CourseEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LearnOnlineApplication.class})
public class CourseEntityDaoTest {

	@Autowired
	CourseEntityDao courseEntityDao;
	
	@Test
	public void findCoursesByTechnology() {		
		Optional<List<CourseEntity>> rtCourseEntityList = courseEntityDao.findCoursesByTechnology("Java");
		assertNotNull(rtCourseEntityList);
	}
	
	
	@Test
	public void findByName() {
		Optional<List<CourseEntity>> rtCourseEntityList = courseEntityDao.findByName("Linux");
		assertNotNull(rtCourseEntityList);
	}
	
	@Test
	public void findCoursesByKey() {
		
		List<String> keysList = new ArrayList<>();
		keysList.add("2e552bb07890a68f4563bc2beaa1a82275ae55136f202194d570cb5ab8ce5fb0");
		keysList.add("d483bf6fad787dd0210d1ebd3dc8cd17651d693996f562f1173a578e4e6a9a4e");
		
		Optional<List<CourseEntity>> rtCourseEntityList = courseEntityDao.findCoursesByKey(keysList);
		assertNotNull(rtCourseEntityList);
	}
	
	
//	@Query("from CourseEntity c where c.courseKey in(:courseKeyList)") 
//	public Optional<List<CourseEntity>> findCoursesByKey(List<String> courseKeyList);

	

}
