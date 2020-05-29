package com.learn.online.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.learn.online.beans.CourseEntity;
import com.learn.online.beans.CourseOrderEntity;
import com.learn.online.beans.RoleEntity;
import com.learn.online.beans.StudentEntity;
import com.learn.online.daos.RoleEntityDao;
import com.learn.online.dtos.CourseDto;
import com.learn.online.dtos.CourseOrderDto;
import com.learn.online.dtos.StudentDto;
import com.learn.online.dummies.DummyData;
import com.learn.online.securities.UserPrincipal;

public class CustomUtilTest {

	
	@Mock 
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Mock
	RoleEntityDao roleEntityDao;
	
	
	@Before
	public  void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testConvertoToStudentDto() {
		
		
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
	public void testConvertoToStudentDtoWithCourseOrders() {
		
		
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
	public void testConvertoToStudentEntity() {
		
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
	public  void testconvertToStudentEntityWithRoleAndPasswordEncryption() {
		
		//public void  static StudentEntity convertToStudentEntity(StudentDto studdentDto, 
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
	public  void testconvertToStudentEntityWithRoleAndPasswordEncryptionWithNonCourseOrders() {
		
		//public void  static StudentEntity convertToStudentEntity(StudentDto studdentDto, 
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
	public void testConvertoToStudentEntityWithCourseOrder() {
		
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
	public void testConvertoToCourseEntityList() {
	
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
	public void testConvertoToCourseEntityListIsNull() {
		
		List<CourseEntity> courseEntityList = CustomUtils.convertToCourseEntityList(null);
		
		assertNotNull(courseEntityList);
		assertEquals(0, courseEntityList.size());
		
	}
		
	@Test
	public void testConvertoToCourseDtoList() {
	
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
	public void testConvertoToCourseDtoListIsNull() {
	
		List<CourseDto> courseDtoList = CustomUtils.convertToCourseDtoList(null);

		assertNotNull(courseDtoList);
		assertEquals(0, courseDtoList.size());
		
	}
	
	@Test
	public void testConvertoToCourseEntity() {
	
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
	public void testConvertoToCourseDto() {
	
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
	
	
	@Test
	public void testConvertToCourseDtoWithoutIds() {
		CourseEntity  courseEntity = DummyData.getStudentEntityForCreation3()
				.getCourseOrders().get(0).getCourse();
		
		CourseDto courseDto = CustomUtils.convertToCourseDtoWithoutIds(courseEntity);
		
		assertNotNull(courseDto);
		assertTrue(courseEntity.getChapters().equals(courseDto.getChapters())
			&& courseEntity.getCourseName().equals(courseDto.getCourseName())
			&& courseEntity.getDomainName().equals(courseDto.getDomainName())
			&& courseEntity.getDurationInHours().equals(courseDto.getDurationInHours())
			&& courseEntity.getPrice().equals(courseDto.getPrice())
			&& courseEntity.getRating().equals(courseDto.getRating()));
		
	}
	
	@Test
	public void  testCourseEnityListToCourseOrderEntityList() {
		
		List<CourseEntity> coursEntities = DummyData.getAllCourses()
				.stream().map(courseDto->{
					CourseEntity courseEntity = new CourseEntity();
					
					courseEntity.setChapters(courseDto.getChapters());
					courseEntity.setCourseId(courseDto.getCourseId());
					courseEntity.setCourseKey(courseDto.getCourseKey());
					courseEntity.setCourseName(courseDto.getCourseName());
					courseEntity.setCreationtDate(courseDto.getCreationtDate());
					courseEntity.setDescription(courseDto.getDescription());
					courseEntity.setDomainName(courseDto.getDomainName());
					courseEntity.setDurationInHours(courseDto.getDurationInHours());
					courseEntity.setLastUpdateDate(courseDto.getLastUpdateDate());
					courseEntity.setPrice(courseDto.getPrice());
					courseEntity.setRating(courseDto.getRating());
					
					return courseEntity;
				}).collect(Collectors.toList());
		
		
		List<CourseOrderEntity> courseOrderEntityList =  CustomUtils
				.courseEnityListToCourseOrderEntityList(coursEntities);
		
		assertNotNull(courseOrderEntityList);
		assertEquals(coursEntities.size(), courseOrderEntityList.size());
		
	}
	
	@Test
	public void testCourseEnityListToCourseOrderEntityListIsNull() {
		
		List<CourseOrderEntity> courseOrderEntityList =  CustomUtils
				.courseEnityListToCourseOrderEntityList(null);
		
		assertNotNull(courseOrderEntityList);
		assertEquals(0, courseOrderEntityList.size());
		
	}
	
	@Test
	public void testCourseDtoListToCourseOrderEntityList() {
		
		List<Long> idList = new ArrayList<>();
		idList.add(101l);
		idList.add(102l);
		idList.add(103l);
		idList.add(104l);
		idList.add(105l);
		Iterator<Long> idIterator = idList.iterator();
		
		List<String> courseOrderKeysList = new ArrayList<>();
		courseOrderKeysList.add("bbb9f61ed51461fde2400c6aee189d1ef5f90c832514ddc6cf490f9f8c9fd6e8");
		courseOrderKeysList.add("73c505743df6f970587f4cd7e4ab9034fa3671fe6a60feb8c689f756e9bf6ef7");
		courseOrderKeysList.add("844042be5181c67cad41495e48ad98fdce0c6833664f5071979f33267783f833");
		courseOrderKeysList.add("f6d2609eadd734e987a9b5ebdfb39dc25a77dff7ee5896f7c5e05cb11a987ec4");
		courseOrderKeysList.add("18dfa010f8910b458d863ce6c6f749e535b726dd4ea509ee192002800a4c40fc");
		Iterator<String> orderKeyIterator = courseOrderKeysList.iterator();
		
		StudentDto studentDto = DummyData.getStudentDto();
		
		List<CourseOrderDto> coursCourseOrderDtoList = DummyData.getAllCourses()
				.stream().map(courseDto->{
					CourseOrderDto courseOrderDto = new CourseOrderDto();
					
					courseOrderDto.setCourse(courseDto);
					courseOrderDto.setCourseOrderId(idIterator.next());
					courseOrderDto.setCourseOrderKey(orderKeyIterator.next());
					courseOrderDto.setCreationDate(LocalDate.now());
					courseOrderDto.setLastUpdateDate(LocalDate.now());
					courseOrderDto.setRating(courseDto.getRating());
					courseOrderDto.setStudent(studentDto);
					
					return courseOrderDto;
				}).collect(Collectors.toList());
		
		
		List<CourseOrderEntity> courseOrderEntityList =  CustomUtils
				.convertToCourseOrderEntityList(coursCourseOrderDtoList);
		
		assertNotNull(courseOrderEntityList);
		assertEquals(coursCourseOrderDtoList.size(), courseOrderEntityList.size());
		
	}
	
	@Test
	public void testCourseDtoListToCourseOrderEntityListIsNull() {
		
		List<CourseOrderEntity> courseOrderEntityList =  CustomUtils
				.convertToCourseOrderEntityList(null);
		
		assertNotNull(courseOrderEntityList);
		assertEquals(0, courseOrderEntityList.size());
		
	}
	
	@Test
	public  void testContainsFound() {
		
		List<String> courseOrderKeyList = new ArrayList<>();
		courseOrderKeyList.add("bbb9f61ed51461fde2400c6aee189d1ef5f90c832514ddc6cf490f9f8c9fd6e8");
		courseOrderKeyList.add("73c505743df6f970587f4cd7e4ab9034fa3671fe6a60feb8c689f756e9bf6ef7");
		courseOrderKeyList.add("844042be5181c67cad41495e48ad98fdce0c6833664f5071979f33267783f833");
		courseOrderKeyList.add("f6d2609eadd734e987a9b5ebdfb39dc25a77dff7ee5896f7c5e05cb11a987ec4");
		courseOrderKeyList.add("18dfa010f8910b458d863ce6c6f749e535b726dd4ea509ee192002800a4c40fc");
		
		String courseOrderKey = "18dfa010f8910b458d863ce6c6f749e535b726dd4ea509ee192002800a4c40fc";
		
		assertEquals(true, CustomUtils.contains(courseOrderKey, courseOrderKeyList));
	}
	
	@Test
	public void testContainsNotFound() {
		
		List<String> courseOrderKeyList = new ArrayList<>();
		courseOrderKeyList.add("bbb9f61ed51461fde2400c6aee189d1ef5f90c832514ddc6cf490f9f8c9fd6e8");
		courseOrderKeyList.add("73c505743df6f970587f4cd7e4ab9034fa3671fe6a60feb8c689f756e9bf6ef7");
		courseOrderKeyList.add("844042be5181c67cad41495e48ad98fdce0c6833664f5071979f33267783f833");
		courseOrderKeyList.add("f6d2609eadd734e987a9b5ebdfb39dc25a77dff7ee5896f7c5e05cb11a987ec4");
		courseOrderKeyList.add("18dfa010f8910b458d863ce6c6f749e535b726dd4ea509ee192002800a4c40fc");
		
		String courseOrderKey = "5561c1da400c41b70b293a261e68977f86e0517397cc09bfeb3fc7ce1bf3c42f";
		
		assertEquals(false, CustomUtils.contains(courseOrderKey, courseOrderKeyList));
	}

	@Test
	public void testAddUmatchedCourseOrdKey() {
		
		List<String> courseOrderKeyList = new ArrayList<>();
		courseOrderKeyList.add("bbb9f61ed51461fde2400c6aee189d1ef5f90c832514ddc6cf490f9f8c9fd6e8");
		courseOrderKeyList.add("73c505743df6f970587f4cd7e4ab9034fa3671fe6a60feb8c689f756e9bf6ef7");
		courseOrderKeyList.add("844042be5181c67cad41495e48ad98fdce0c6833664f5071979f33267783f833");
		courseOrderKeyList.add("f6d2609eadd734e987a9b5ebdfb39dc25a77dff7ee5896f7c5e05cb11a987ec4");
		courseOrderKeyList.add("18dfa010f8910b458d863ce6c6f749e535b726dd4ea509ee192002800a4c40fc");
		
		List<String> courseNotExistList = new ArrayList<>();
		
		String courseOrderKey = "18dfa010f8910b458d863ce6c6f749e535b726dd4ea509ee192002800a4c40fc";
		
		 CustomUtils.addUmatchedCourseOrdKey(courseOrderKey, 
					courseOrderKeyList, courseNotExistList);
		
		assertTrue(courseNotExistList.size() == courseOrderKeyList.size()-1);
	}
	
	@Test
	public void testLoadStudentEntityForUpdate() {
		
		StudentDto studentDto = DummyData.getStudentDto();
		StudentEntity studentEntity = new StudentEntity();
		
		
		CustomUtils.loadStudentEntityForUpdate(studentDto, studentEntity);
		assertTrue(studentDto.getCity().equals(studentEntity.getCity())
				&& studentDto.getCountry().equals(studentEntity.getCountry())
				&& studentDto.getFirstName().equals(studentEntity.getFirstName())
				&& studentDto.getLastName().equals(studentEntity.getLastName())
				&& studentDto.getState().equals(studentEntity.getState()));
		
		
		
	}
	
	@Test
	public void testLoadStudentEntityForUpdate2() {
		
		StudentDto studentDto = DummyData.getStudentDto();
		StudentEntity studentEntity = DummyData.getStudentEntityForCreation3();
		
		Mockito.when(bCryptPasswordEncoder.encode(Mockito.anyString())).thenReturn("password");
		
		UserPrincipal userPrincipal = new UserPrincipal(studentEntity);
		
		CustomUtils.loadStudentEntityForUpdate(studentDto, studentEntity, bCryptPasswordEncoder, userPrincipal);
		
		assertTrue(studentDto.getCity().equals(studentEntity.getCity())
				&& studentDto.getCountry().equals(studentEntity.getCountry())
				&& studentDto.getFirstName().equals(studentEntity.getFirstName())
				&& studentDto.getLastName().equals(studentEntity.getLastName())
				&& studentDto.getState().equals(studentEntity.getState()));
		
		
		
	}
	
	@Test
	public void testLoadStudentEntityForUpdate3() {
		
		StudentDto studentDto = DummyData.getStudentDto();
		StudentEntity studentEntity = DummyData.getStudentEntityForCreation3();
		
		Mockito.when(bCryptPasswordEncoder.encode(Mockito.anyString())).thenReturn("password");
		
		studentEntity.getRoles().iterator().next().setName("ADMIN");
		UserPrincipal userPrincipal = new UserPrincipal(studentEntity);
		studentEntity.setEmail("aaa@gmail.com");
		studentDto.setEmail("bbb@gamil.com");
		
		assertNotNull(CustomUtils.loadStudentEntityForUpdate(studentDto, 
				studentEntity, bCryptPasswordEncoder, userPrincipal));
		
	}
	
	@Test
	public void testLoadStudentEntityForUpdate4() {
		
		StudentDto studentDto = DummyData.getStudentDto();
		StudentEntity studentEntity = DummyData.getStudentEntityForCreation2();
		
		Mockito.when(bCryptPasswordEncoder.encode(Mockito.anyString())).thenReturn("password");
		
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setName("Role_ADMIN");
		studentEntity.getRoles().add(roleEntity);
		
		roleEntity = new RoleEntity();
		roleEntity.setName("ADMIN");
		studentEntity.getRoles().add(roleEntity);
		
		UserPrincipal userPrincipal = new UserPrincipal(studentEntity) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				
				Set<GrantedAuthority> grantedAuthoritySet =new HashSet<>();
				grantedAuthoritySet.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				grantedAuthoritySet.add(new SimpleGrantedAuthority("ADMIN"));
				return grantedAuthoritySet;
			}
			
			@Override
			public String getEmail() {
				return "strange@gmail.com";
			}
			
		};
		studentEntity.setEmail("aaa@gmail.com");
		studentDto.setEmail("bbb@gmail.com");
		
		assertNotNull(CustomUtils.loadStudentEntityForUpdate(studentDto, 
				studentEntity, bCryptPasswordEncoder, userPrincipal));
		
	}
}