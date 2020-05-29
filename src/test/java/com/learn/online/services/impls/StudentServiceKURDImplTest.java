package com.learn.online.services.impls;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.learn.online.beans.CourseEntity;
import com.learn.online.beans.CourseOrderEntity;
import com.learn.online.beans.StudentEntity;
import com.learn.online.daos.CourseEntityDao;
import com.learn.online.daos.RoleEntityDao;
import com.learn.online.daos.StudentEntityDao;
import com.learn.online.dtos.StudentDto;
import com.learn.online.dummies.DummyData;
import com.learn.online.enums.ErrorMessagesEnum;
import com.learn.online.exceptions.CourseNotFoundtException;
import com.learn.online.exceptions.StudentServiceException;
import com.learn.online.utils.CustomUtils;

public class StudentServiceKURDImplTest {

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

	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void signupTest() {

		Mockito.when(studentEntityDao.findByEmail(Mockito.anyString()))
		.thenReturn(Optional.empty());

		StudentEntity studentEntity = DummyData.getStudentEntityForCreation();
		StudentDto studentDto = CustomUtils.convertToStudentDto(studentEntity);

		Mockito.when(studentEntityDao.save(Mockito.any(StudentEntity.class)))
		.thenReturn(studentEntity);

		Mockito.when(bCryptPasswordEncoder.encode(Mockito.anyString()))
		.thenReturn("password");	
		
		Mockito.when(roleEntityDao.findByName(Mockito.anyString()))
			.thenReturn(Optional.of(studentEntity.getRoles().iterator().next()));
		
		StudentDto returnedStudentDto = studentService.signupStudent(studentDto);
		
		assertEquals(studentDto.getEmail(), returnedStudentDto.getEmail());
	}
	
	@Test
	public void signupFailedTest() {

		Mockito.when(studentEntityDao.findByEmail(Mockito.anyString()))
				.thenReturn(Optional.of(DummyData.getStudentEntityForUpdate()));

		StudentEntity studentEntity = DummyData.getStudentEntityForCreation();
		StudentDto studentDto = CustomUtils.convertToStudentDto(studentEntity);
		Mockito.when(studentEntityDao.save(Mockito.any(StudentEntity.class)))
		.thenReturn(studentEntity);
		
		
		exceptionRule.expect(StudentServiceException.class);
		exceptionRule.expectMessage(ErrorMessagesEnum.DUPLICATE_STUDENT_ENTRY.getMessage());
		
		studentService.signupStudent(studentDto);
		
		Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
		Mockito.verify(studentEntityDao, Mockito.never())
			.save(Mockito.any(StudentEntity.class));
		
	}

	
	@Test
	public void RequestedCoursestoBuyDoNotExistsTest() {

		StudentEntity studentEntity = DummyData.getStudentEntityForUpdate();
		List<String> keysList = new ArrayList<>();
		keysList.add("2e552bb07890a68f4563bc2beaa1a82275ae55136f202194d570cb5ab8ce5fb0");
		keysList.add("d483bf6fad787dd0210d1ebd3dc8cd17651d693996f562f1173a578e4e6a9a4e");
		
		Optional<StudentEntity> optionalStudentEntity = Optional.of(studentEntity);

		Mockito.when(studentEntityDao.findByEmail(Mockito.anyString()))
						.thenReturn(optionalStudentEntity);

		Mockito.when(courseEntityDao.findCoursesByKey(Mockito.anyList()))
					.thenReturn(Optional.empty());

		Mockito.when(studentEntityDao.save(Mockito.any(StudentEntity.class)))
					.thenReturn(studentEntity);

		exceptionRule.expect(CourseNotFoundtException.class);
		exceptionRule.expectMessage(ErrorMessagesEnum
				.REQUESTED_COURSES_NOT_FOUND_FOR_PURCHASE.getMessage());
		
		studentService.purchaseCourses("apple@gmail.com", keysList);
		
		Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
		Mockito.verify(courseEntityDao, Mockito.times(1)).findCoursesByKey(Mockito.anyList());
		
	}
	
	
	@Test 
	public void duplicatePurchaseCoursesTest() {
	  
	  StudentEntity studentEntity = DummyData.getStudentEntityForUpdate();
		  
	  List<String> keysList = new ArrayList<>(); 
	  keysList.add("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
	  keysList.add("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
	  
	  List<CourseEntity> courseEntityList = new ArrayList<>();
	  
	  CourseEntity courseEntity = new CourseEntity();
	  courseEntity.setChapters(400); courseEntity.setCourseId(111L);
	  courseEntity.setCourseKey("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570c"
	  		+ "b5ab8ce5fb0");
	  courseEntity.setCourseName("Master Java Programming");
	  courseEntity.setCreationtDate(LocalDate.of(2018, 9, 7));
	  courseEntity.setDescription("This book teach you real time skills");
	  courseEntity.setDomainName("Java"); courseEntity.setDurationInHours(500);
	  courseEntity.setLastUpdateDate(LocalDate.of(2020, 1, 1));
	  courseEntity.setPrice(500D); courseEntity.setRating(5D);
	  courseEntityList.add(courseEntity);
	  
	  List<CourseOrderEntity> courseOrderEntityList = new ArrayList<>();
	  CourseOrderEntity courseOrderEntity = new CourseOrderEntity();
	  courseOrderEntity.setCourse(courseEntity);
	  courseOrderEntity.setCourseOrderKey("bbb9f61ed51461fde2400c6aee189d1ef5f90c8325"
	  		+ "14ddc6cf490f9f8c9fd6e8");
	  
	  courseOrderEntity.setCreationDate(LocalDate.now());
	  courseOrderEntity.setRating(5D); courseOrderEntity.setStudent(studentEntity);
	  
	  courseOrderEntityList.add(courseOrderEntity);
	  
	  courseEntity = new CourseEntity(); courseEntity.setChapters(200);
	  courseEntity.setCourseId(112L); courseEntity.setCourseKey(
	  "d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
	  courseEntity.setCourseName("Unix In Action");
	  courseEntity.setCreationtDate(LocalDate.of(2019, 1, 10));
	  courseEntity.setDescription("Unix Cook book of experts");
	  courseEntity.setDomainName("OS"); courseEntity.setDurationInHours(340);
	  courseEntity.setLastUpdateDate(LocalDate.of(2019, 11, 10));
	  courseEntity.setPrice(400D); courseEntity.setRating(5D);
	  courseEntityList.add(courseEntity);
	  
	  courseOrderEntity = new CourseOrderEntity();
	  courseOrderEntity.setCourse(courseEntity);
	  courseOrderEntity.setCourseOrderKey(
	  "30ef27b8c959123ec2971a4fcfaf36ff6c46d1090b40592fb02" + "f3a8cafd4221e");
	  
	  courseOrderEntity.setCreationDate(LocalDate.now());
	  courseOrderEntity.setRating(5D); 
	  courseOrderEntity.setStudent(studentEntity);
	  courseOrderEntityList.add(courseOrderEntity);
	  
	  studentEntity.setCourseOrders(courseOrderEntityList); Optional<StudentEntity>
	  optionalStudentEntity = Optional.of(studentEntity);
	  
	  Mockito.when(studentEntityDao.findByEmail(Mockito.anyString()))
	  .thenReturn(optionalStudentEntity);
	  
	  Mockito.when(courseEntityDao.findCoursesByKey(Mockito.anyList()))
	  .thenReturn(Optional.of(courseEntityList));
	  
	  Mockito.when(studentEntityDao.save(Mockito.any(StudentEntity.class)))
	  .thenReturn(studentEntity);
	  
	  exceptionRule.expect(StudentServiceException.class);
	  exceptionRule.expectMessage(ErrorMessagesEnum
			  .BUYING_DUPLICATE_COURSES.getMessage().substring(0, 24));
	  
	  studentService.purchaseCourses("apple@gmail.com", keysList);
	  
	  Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
	  Mockito.verify(courseEntityDao, Mockito.times(1)).findCoursesByKey(Mockito.anyList());
	  Mockito.verify(studentEntityDao, Mockito.never())
	  	.save(Mockito.any(StudentEntity.class));
	  
	}
	
	@Test 
	public void purchaseCoursesTest() {
	  
	  StudentEntity studentEntity = DummyData.getStudentEntityForCreation();
	  
	  StudentDto studentDto = CustomUtils.convertToStudentDto(studentEntity);

	  List<String> keysList = new ArrayList<>();
	  keysList.add("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
	  keysList.add("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
	
	  studentEntity.setCourseOrders(new ArrayList<CourseOrderEntity>()); 
	  Optional<StudentEntity> optionalStudentEntity = Optional.of(studentEntity);
	  
	  Mockito.when(studentEntityDao.findByEmail(Mockito.anyString())).thenReturn(
			  				optionalStudentEntity);
	  
	  Mockito.when(studentEntityDao.save(Mockito.any(StudentEntity.class))).thenReturn(
  				studentEntity);
	  
	  Mockito.when(courseEntityDao.findCoursesByKey(Mockito.anyList()))
	  .thenReturn(Optional.of(new ArrayList<CourseEntity>()));
	  
	  StudentDto returnedStudentDto = studentService.purchaseCourses("apple@gmail.com", 
			  keysList);
	  
	  Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
	  Mockito.verify(studentEntityDao, Mockito.times(1))
	  	.save(Mockito.any(StudentEntity.class));
	 
	  Mockito.verify(courseEntityDao, Mockito.times(1)).findCoursesByKey(Mockito.anyList());
	  
	  assertTrue(studentDto.getCity().equals(returnedStudentDto.getCity()) 
			 && studentDto.getCountry().equals(returnedStudentDto.getCountry())
			 && studentDto.getEmail().equals(returnedStudentDto.getEmail())
			 && studentDto.getFirstName().equals(returnedStudentDto.getFirstName())
			 && studentDto.getLastName().equals(returnedStudentDto.getLastName())
			 && studentDto.getPhone().equals(returnedStudentDto.getPhone()));
    }

	
	@Test 
	public void cancePurcahsedCoursesNotFoundForDeletionTest() { 
		  
	  	StudentEntity studentEntity = DummyData.getStudentEntityForUpdate();
	  
	  List<String> keysList = new ArrayList<>(); 
	  keysList.add("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
	  keysList.add("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
	  
	  List<CourseEntity> courseEntityList = new ArrayList<>();
	  
	  CourseEntity courseEntity = new CourseEntity();
	  courseEntity.setChapters(400); courseEntity.setCourseId(111L);
	  courseEntity.setCourseKey("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570c"
	  		+ "b5ab8ce5fb0");
	  courseEntity.setCourseName("Master Java Programming");
	  courseEntity.setCreationtDate(LocalDate.of(2018, 9, 7));
	  courseEntity.setDescription("This book teach you real time skills");
	  courseEntity.setDomainName("Java"); courseEntity.setDurationInHours(500);
	  courseEntity.setLastUpdateDate(LocalDate.of(2020, 1, 1));
	  courseEntity.setPrice(500D); courseEntity.setRating(5D);
	  courseEntityList.add(courseEntity);
	  
	  List<CourseOrderEntity> courseOrderEntityList = new ArrayList<>();
	  CourseOrderEntity courseOrderEntity = new CourseOrderEntity();
	  courseOrderEntity.setCourse(courseEntity);
	  courseOrderEntity.setCourseOrderKey("bbb9f61ed51461fde2400c6aee189d1ef5f90c8325"
	  		+ "14ddc6cf490f9f8c9fd6e8");
	  
	  courseOrderEntity.setCreationDate(LocalDate.now());
	  courseOrderEntity.setRating(5D); courseOrderEntity.setStudent(studentEntity);
	  
	  courseOrderEntityList.add(courseOrderEntity);
	  
	  courseEntity = new CourseEntity(); courseEntity.setChapters(200);
	  courseEntity.setCourseId(112L); courseEntity.setCourseKey(
	  "d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
	  courseEntity.setCourseName("Unix In Action");
	  courseEntity.setCreationtDate(LocalDate.of(2019, 1, 10));
	  courseEntity.setDescription("Unix Cook book of experts");
	  courseEntity.setDomainName("OS"); courseEntity.setDurationInHours(340);
	  courseEntity.setLastUpdateDate(LocalDate.of(2019, 11, 10));
	  courseEntity.setPrice(400D); courseEntity.setRating(5D);
	  courseEntityList.add(courseEntity);
	  
	  courseOrderEntity = new CourseOrderEntity();
	  courseOrderEntity.setCourse(courseEntity);
	  courseOrderEntity.setCourseOrderKey(
	  "30ef27b8c959123ec2971a4fcfaf36ff6c46d1090b40592fb02" + "f3a8cafd4221e");
	  
	  courseOrderEntity.setCreationDate(LocalDate.now());
	  courseOrderEntity.setRating(5D); 
	  courseOrderEntity.setStudent(studentEntity);
	  courseOrderEntityList.add(courseOrderEntity);
	  
	  studentEntity.setCourseOrders(courseOrderEntityList); 
	  Optional<StudentEntity> optionalStudentEntity = Optional.of(studentEntity);
	  
	  Mockito.when(studentEntityDao.findByEmail(Mockito.anyString()))
	  .thenReturn(optionalStudentEntity);
	  
	  Mockito.when(courseEntityDao.findCoursesByKey(Mockito.anyList()))
	  .thenReturn(Optional.of(new ArrayList<CourseEntity>()));
	  
	  Mockito.when(studentEntityDao.saveAndFlush(Mockito.any(StudentEntity.class)))
	  .thenReturn(studentEntity);
	  
	  exceptionRule.expect(StudentServiceException.class);
	  exceptionRule.expectMessage(ErrorMessagesEnum.REQUESTED_COURSES_NOT_FOUND_FOR_DELETE
			  .getMessage().substring(0, 25));
	  
	  studentService.cancellPurchasedCourses("12301f3b04f21a9a8f5d507b246c3b84f20"
				+ "cd8aead3b06214caa128cca0e65c", keysList);
		  
	  Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
	  Mockito.verify(courseEntityDao, Mockito.times(1)).findCoursesByKey(Mockito.anyList());
	  Mockito.verify(studentEntityDao, Mockito.times(1))
	  .saveAndFlush(Mockito.any(StudentEntity.class));
	  
	}

	@Test 
	public void purchaseCoursesStudentNotFoundTest() {
	  
	  List<String> keysList = new ArrayList<>();
	  keysList.add("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
	  keysList.add("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
	  
	  Mockito.when(studentEntityDao.findByEmail(Mockito.anyString())).thenReturn(
			  				Optional.empty());
	  
	  exceptionRule.expect(StudentServiceException.class);
	  exceptionRule.expectMessage(ErrorMessagesEnum.REQUESTED_STUDENT_NOT_FOUND.getMessage());
	  
	  studentService.purchaseCourses("apple@gmail.com", keysList);
	  
	  Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
	  
    }

	
	@Test 
	public void cancelPurchaseCoursesTest() {
	  
	  StudentEntity studentEntity = DummyData.getStudentEntityForCreation();
		  
	  List<String> keysList = new ArrayList<>(); 
	  keysList.add("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
	  keysList.add("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
	  
	  List<CourseEntity> courseEntityList = new ArrayList<>();
	  
	  CourseEntity courseEntity = new CourseEntity();
	  courseEntity.setChapters(400); courseEntity.setCourseId(111L);
	  courseEntity.setCourseKey("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d5"
	  		+ "70cb5ab8ce5fb0");
	  courseEntity.setCourseName("Master Java Programming");
	  courseEntity.setCreationtDate(LocalDate.of(2018, 9, 7));
	  courseEntity.setDescription("This book teach you real time skills");
	  courseEntity.setDomainName("Java"); courseEntity.setDurationInHours(500);
	  courseEntity.setLastUpdateDate(LocalDate.of(2020, 1, 1));
	  courseEntity.setPrice(500D); courseEntity.setRating(5D);
	  courseEntityList.add(courseEntity);
	  
	  List<CourseOrderEntity> courseOrderEntityList = new ArrayList<>();
	  CourseOrderEntity courseOrderEntity = new CourseOrderEntity();
	  courseOrderEntity.setCourse(courseEntity);
	  courseOrderEntity.setCourseOrderKey("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494"
	  		+ "d570cb5ab8ce5fb0");
	  
	  courseOrderEntity.setCreationDate(LocalDate.now());
	  courseOrderEntity.setRating(5D); 
	  courseOrderEntity.setStudent(studentEntity);
	  
	  courseOrderEntityList.add(courseOrderEntity);
	  
	  courseEntity = new CourseEntity(); 
	  courseEntity.setChapters(200);
	  courseEntity.setCourseId(112L); 
	  courseEntity.setCourseKey("d483bf6fad787dd0210d1ebd8dc"
	  		+ "8cd17651d673996f561f1173a578e4e6a9a4e");
	  
	  courseEntity.setCourseName("Unix In Action");
	  courseEntity.setCreationtDate(LocalDate.of(2019, 1, 10));
	  courseEntity.setDescription("Unix Cook book of experts");
	  courseEntity.setDomainName("OS"); courseEntity.setDurationInHours(340);
	  courseEntity.setLastUpdateDate(LocalDate.of(2019, 11, 10));
	  courseEntity.setPrice(400D); courseEntity.setRating(5D);
	  courseEntityList.add(courseEntity);
	  
	  courseOrderEntity = new CourseOrderEntity();
	  courseOrderEntity.setCourse(courseEntity);
	  courseOrderEntity.setCourseOrderKey("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f5"
	  		+ "61f1173a578e4e6a9a4e");
	  
	  courseOrderEntity.setCreationDate(LocalDate.now());
	  courseOrderEntity.setRating(5D); 
	  courseOrderEntity.setStudent(studentEntity);
	  courseOrderEntityList.add(courseOrderEntity);
	  
	  studentEntity.setCourseOrders(courseOrderEntityList); 
	  Optional<StudentEntity> optionalStudentEntity = Optional.of(studentEntity);
	  
	  Mockito.when(studentEntityDao.findByEmail(Mockito.anyString()))
	  .thenReturn(optionalStudentEntity);
	  
	  Mockito.when(courseEntityDao.findCoursesByKey(Mockito.anyList()))
	  .thenReturn(Optional.of(courseEntityList));
	  
	  Mockito.when(studentEntityDao.saveAndFlush(Mockito.any(StudentEntity.class)))
	  .thenReturn(studentEntity);
	   
	  StudentDto returnedStudentDto = studentService
			  .cancellPurchasedCourses("apple@gmail.com", keysList);
	  
	  Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
	  Mockito.verify(studentEntityDao, Mockito.times(1))
	  	.saveAndFlush(Mockito.any(StudentEntity.class));
	  
	  assertTrue(returnedStudentDto.getCourseOrders() == null 
			  || returnedStudentDto.getCourseOrders().isEmpty()
			  || returnedStudentDto.getCourseOrders().size() < courseEntityList.size());
	  
	}
	 
	
	@Test 
	public void canNotcancelPurchaseCoursesDatesExccedTest() {
	  
	  StudentEntity studentEntity = DummyData.getStudentEntityForCreation();
		  
	  List<String> keysList = new ArrayList<>(); 
	  keysList.add("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
	  keysList.add("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
	  
	  List<CourseEntity> courseEntityList = new ArrayList<>();
	  
	  CourseEntity courseEntity = new CourseEntity();
	  courseEntity.setChapters(400); courseEntity.setCourseId(111L);
	  courseEntity.setCourseKey("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5a"
	  		+ "b8ce5fb0");
	  courseEntity.setCourseName("Master Java Programming");
	  courseEntity.setCreationtDate(LocalDate.of(2018, 9, 7));
	  courseEntity.setDescription("This book teach you real time skills");
	  courseEntity.setDomainName("Java"); courseEntity.setDurationInHours(500);
	  courseEntity.setLastUpdateDate(LocalDate.of(2020, 1, 1));
	  courseEntity.setPrice(500D); courseEntity.setRating(5D);
	  courseEntityList.add(courseEntity);
	  
	  List<CourseOrderEntity> courseOrderEntityList = new ArrayList<>();
	  CourseOrderEntity courseOrderEntity = new CourseOrderEntity();
	  courseOrderEntity.setCourse(courseEntity);
	  courseOrderEntity.setCourseOrderKey("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d5"
	  		+ "70cb5ab8ce5fb0");
	  
	  courseOrderEntity.setCreationDate(LocalDate.now());
	  courseOrderEntity.setRating(5D); 
	  courseOrderEntity.setStudent(studentEntity);
	  courseOrderEntity.setCreationDate(LocalDate.of(2010, 1, 1));
	  
	  courseOrderEntityList.add(courseOrderEntity);
	  
	  courseEntity = new CourseEntity(); 
	  courseEntity.setChapters(200);
	  courseEntity.setCourseId(112L); 
	  courseEntity.setCourseKey("d483bf6fad787dd0210d1ebd8dc"
	  		+ "8cd17651d673996f561f1173a578e4e6a9a4e");
	  
	  courseEntity.setCourseName("Unix In Action");
	  courseEntity.setCreationtDate(LocalDate.of(2019, 1, 10));
	  courseEntity.setDescription("Unix Cook book of experts");
	  courseEntity.setDomainName("OS"); courseEntity.setDurationInHours(340);
	  courseEntity.setLastUpdateDate(LocalDate.of(2019, 11, 10));
	  courseEntity.setPrice(400D); courseEntity.setRating(5D);
	  courseEntityList.add(courseEntity);
	  
	  courseOrderEntity = new CourseOrderEntity();
	  courseOrderEntity.setCourse(courseEntity);
	  courseOrderEntity.setCourseOrderKey("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173"
	  		+ "a578e4e6a9a4e");
	  
	  courseOrderEntity.setCreationDate(LocalDate.of(2010, 1, 1));
	  courseOrderEntity.setRating(5D); 
	  courseOrderEntity.setStudent(studentEntity);
	  courseOrderEntityList.add(courseOrderEntity);
	  
	  studentEntity.setCourseOrders(courseOrderEntityList); 
	  Optional<StudentEntity> optionalStudentEntity = Optional.of(studentEntity);
	  
	  Mockito.when(studentEntityDao.findByEmail(Mockito.anyString()))
	  .thenReturn(optionalStudentEntity);
	  
	  Mockito.when(courseEntityDao.findCoursesByKey(Mockito.anyList()))
	  .thenReturn(Optional.of(courseEntityList));
	  
	  Mockito.when(studentEntityDao.saveAndFlush(Mockito.any(StudentEntity.class)))
	  .thenReturn(studentEntity);

	  exceptionRule.expect(StudentServiceException.class);
	  exceptionRule.expectMessage("exceeds 30 days limits");

	  studentService.cancellPurchasedCourses("apple@gmail.com", keysList);
	  
	  Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
	  Mockito.verify(courseEntityDao, Mockito.times(1)).findCoursesByKey(Mockito.anyList());
	  Mockito.verify(studentEntityDao, Mockito.times(1))
	  	.saveAndFlush(Mockito.any(StudentEntity.class));
	  
	}
	
	@Test 
	public void cancelPurchaseCoursesStudentNotFoundTest() {
	  

	  List<String> keysList = new ArrayList<>();
	  keysList.add("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
	  keysList.add("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
	  
	  Mockito.when(studentEntityDao.findByEmail(Mockito.anyString())).thenReturn(
			  				Optional.empty());
	 
	  exceptionRule.expect(StudentServiceException.class);
	  exceptionRule.expectMessage(ErrorMessagesEnum.REQUESTED_STUDENT_NOT_FOUND.getMessage());
	  
	  studentService.cancellPurchasedCourses(
				"apple@gmail.com", 
						keysList);
	  
	  Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
	  
    }

	
	
	@Test 
	public void cancelPurchaseEmptyCourseListTest() {
	  
		 List<String> keysList = new ArrayList<>();
		 keysList.add("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
		 keysList.add("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");	
   		
	  StudentEntity studentEntity = new StudentEntity();
	  studentEntity.setCourseOrders(new ArrayList<CourseOrderEntity>());
	  
	  Mockito.when(studentEntityDao.findByEmail(Mockito.anyString()))
	  .thenReturn(Optional.of(studentEntity));
	  
	  Mockito.when(courseEntityDao.findCoursesByKey(Mockito.anyList()))
	  .thenReturn(Optional.empty());
	   
	  exceptionRule.expect(StudentServiceException.class);
	  exceptionRule.expectMessage(ErrorMessagesEnum.EMPTY_COURSES_LIST.getMessage());
	  
	  studentService.cancellPurchasedCourses("someone@gmail.com"
			+ "f20cd8aead3b06214caa128cca0e65c",  keysList);
	  
	}
		
	@Test
	public void searchStudentByEmailId() {
		
		StudentEntity studentEntity = DummyData.getStudentEntityForCreation();
		
		Mockito.when(studentEntityDao.findByEmail(Mockito.anyString()))
			.thenReturn(Optional.of(studentEntity));
		
		StudentDto returnedStudentDto = studentService.findByEmail("abcd@gmail.com");
		
		Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
		
		assertNotNull(returnedStudentDto);
	}
	
	@Test
	public void searchStudentByEmailIdNotFound() {
		
		Mockito.when(studentEntityDao.findByEmail(Mockito.anyString()))
			.thenReturn(Optional.empty());
		
		exceptionRule.expect(StudentServiceException.class);
		exceptionRule.expectMessage("Requested Student is not found. Please make sure "
				+ "you entered your registered primary email properly.");
		
		studentService.findByEmail("abcd@gmail.com");
		
		Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
		
	}
	
}
