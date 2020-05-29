package com.learn.online.services.impls;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.learn.online.beans.StudentEntity;
import com.learn.online.daos.CourseEntityDao;
import com.learn.online.daos.RoleEntityDao;
import com.learn.online.daos.StudentEntityDao;
import com.learn.online.dtos.StudentDto;
import com.learn.online.dummies.DummyData;
import com.learn.online.enums.SecurityRolesAndAuthorities;
import com.learn.online.securities.UserPrincipal;
import com.learn.online.utils.CustomUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SecurityContextHolder.class)
@PowerMockIgnore({"javax.management.*", "org.apache.http.conn.ssl.*", 
	"com.amazonaws.http.conn.ssl.*", "javax.net.ssl.*", "javax.script.*", "javax.security.*"})
public class StudentServiceUpdateImplTest {

	@Mock
	StudentEntityDao studentEntityDao;

	@Mock
	CourseEntityDao courseEntityDao;

	@Mock
	RoleEntityDao roleEntityDao; 
	
	@Mock 
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@InjectMocks
	private StudentServiceImpl studentService;
	
	@Test
	public void updateTest() {

		StudentEntity studentEntity = DummyData.getStudentEntityForCreation3();
		StudentDto studentDto = CustomUtils.convertToStudentDto(studentEntity);
		
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				new UserPrincipal(studentEntity), null, 
				Arrays.asList(new SimpleGrantedAuthority(
				SecurityRolesAndAuthorities.ROLE_ADMIN.name()),
				new SimpleGrantedAuthority(SecurityRolesAndAuthorities.ROLE_USER.name())));  
		
		SecurityContext securityContext = new SecurityContextImpl(authentication);
		
		PowerMockito.mockStatic(SecurityContextHolder.class);
		Mockito.when(SecurityContextHolder.getContext())
		 .thenReturn(securityContext); 
		
		Mockito.when(studentEntityDao.findByEmail(Mockito.anyString()))
		 .thenReturn(Optional.of(studentEntity));
		
		Mockito.when(studentEntityDao.saveAndFlush(Mockito.any()))
		.thenReturn(studentEntity);
		
		StudentDto rtStudentDto = studentService.updateStudent(studentDto);
		

		PowerMockito.verifyStatic();
		SecurityContextHolder.getContext();
	
		Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
		Mockito.verify(studentEntityDao, Mockito.times(1)).saveAndFlush(Mockito.any());
		
		assertTrue(studentDto.equals(rtStudentDto));
	}
	
}
