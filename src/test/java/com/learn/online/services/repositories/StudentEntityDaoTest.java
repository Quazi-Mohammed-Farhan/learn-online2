package com.learn.online.services.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learn.online.beans.StudentEntity;
import com.learn.online.daos.StudentEntityDao;
import com.learn.online.dummies.DummyData;
import com.learn.online.utils.CustomUtils;

@SpringBootTest
public class StudentEntityDaoTest {

	@Autowired
	StudentEntityDao studentEntityDao;
	
	@Test
	public void testFindByEmail() {
		
		StudentEntity studentEntity = DummyData.getStudentEntityForCreation();
		studentEntity.setEmail("someUnique@gmail.com");
		String studentKey = CustomUtils.getSHA256();
		studentEntity.setStudentKey(studentKey);
		studentEntityDao.save(studentEntity);
		
		Optional<StudentEntity> dbStudentEntity = studentEntityDao.findByEmail("someUnique@gmail.com");
		
		assertNotNull(!dbStudentEntity.isPresent());
		assertEquals(studentEntity, dbStudentEntity.get());
		
	}
	
	@Test
	public void testFindByStudentKey() {
		
		StudentEntity studentEntity = DummyData.getStudentEntityForCreation();
		studentEntity.setEmail("someUnique@gmail.com");
		String studentKey = CustomUtils.getSHA256();
		studentEntity.setStudentKey(studentKey);
		
		studentEntityDao.save(studentEntity);
		
		Optional<StudentEntity> dbStudentEntity = studentEntityDao.findByStudentKey(studentKey);
		
		assertNotNull(!dbStudentEntity.isPresent());
		assertTrue(studentEntity.getStudentKey().equals(dbStudentEntity.get().getStudentKey()));
			
	}
	
	@Test
	public void testSaveAndFlush() {
		
		StudentEntity studentEntity = DummyData.getStudentEntityForCreation();
		String studentKey = CustomUtils.getSHA256();
		studentEntity.setStudentKey(studentKey);
		
		StudentEntity dbStudentEntity = studentEntityDao.saveAndFlush(studentEntity);

		assertNotNull(dbStudentEntity);
		assertEquals(studentEntity, dbStudentEntity);
	}
	
	
	@Test
	public void testSave() {
		
		StudentEntity studentEntity = DummyData.getStudentEntityForCreation();
		String studentKey = CustomUtils.getSHA256();
		studentEntity.setStudentKey(studentKey);
		
		StudentEntity dbStudentEntity = studentEntityDao.save(studentEntity);

		assertNotNull(dbStudentEntity);
		assertEquals(studentEntity, dbStudentEntity);
		
	}
	
}