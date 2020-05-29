package com.learn.online.daos;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learn.online.LearnOnlineApplication;
import com.learn.online.beans.StudentEntity;
import com.learn.online.dummies.DummyData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LearnOnlineApplication.class})
public class StudentEntityDaoTest {

	@Autowired
	StudentEntityDao studentEntityDao;
	
	
	@Test
	public void findByEmail() {		
		Optional<StudentEntity> rtStudentEntity = studentEntityDao.findByEmail("chacha@gmail.com");
		assertNotNull(rtStudentEntity);
	}
	
	@Test
	 public void findByStudentKey() {
		Optional<StudentEntity> rtStudentEntity = studentEntityDao
				.findByStudentKey("99dd322339bd790a9440f7d07d436f752521d8f78d8065639980748f378f59bf");
		
		assertNotNull(rtStudentEntity);
	}
	
	@Test
	 public void save() {
		StudentEntity studentEntity = DummyData.getStudentEntityForCreation2();
		StudentEntity rtstudentEntity = studentEntityDao.save(studentEntity);
		assertNotNull(rtstudentEntity);
	}
	
	@Test
	public void saveAndFlush() {
		StudentEntity studentEntity = DummyData.getStudentEntityForCreation2();
		StudentEntity rtstudentEntity = studentEntityDao.saveAndFlush(studentEntity);
		assertNotNull(rtstudentEntity);
	}

}
