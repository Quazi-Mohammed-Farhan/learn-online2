package com.learn.online.services.impls;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;

import com.learn.online.beans.CourseEntity;
import com.learn.online.beans.CourseOrderEntity;
import com.learn.online.beans.StudentEntity;
import com.learn.online.daos.CourseEntityDao;
import com.learn.online.daos.StudentEntityDao;
import com.learn.online.dtos.StudentDto;
import com.learn.online.dummies.DummyData;
import com.learn.online.enums.ErrorMessagesEnum;
import com.learn.online.exceptions.StudentServiceException;
import com.learn.online.services.StudentService;
import com.learn.online.utils.CustomUtils;

@SpringBootTest
public class StudentServiceKURDImplTest {

	@Autowired
	private StudentService studentService;

	@MockBean
	StudentEntityDao studentEntityDao;

	@MockBean
	CourseEntityDao courseEntityDao;

	@Test
	public void signupTest() {

		Mockito.when(studentEntityDao.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

		StudentEntity studentEntity = DummyData.getStudentEntityForCreation();
		StudentDto studentDto = CustomUtils.convertToStudentDto(studentEntity);

		Mockito.when(studentEntityDao.save(Mockito.any(StudentEntity.class))).thenReturn(studentEntity);

		StudentDto returnedStudentDto = studentService.signupStudent(studentDto);
		
		Mockito.verify(studentEntityDao,  Mockito.times(1)).findByEmail(Mockito.anyString());
		Mockito.verify(studentEntityDao, Mockito.times(1)).save(Mockito.any(StudentEntity.class));
		
		assertEquals(studentDto.getEmail(), returnedStudentDto.getEmail());
	}
	
	@Test
	public void signupFailedTest() {

		Mockito.when(studentEntityDao.findByEmail(Mockito.anyString()))
				.thenReturn(Optional.of(DummyData.getStudentEntityForUpdate()));

		StudentEntity studentEntity = DummyData.getStudentEntityForCreation();
		StudentDto studentDto = CustomUtils.convertToStudentDto(studentEntity);
		Mockito.when(studentEntityDao.save(Mockito.any(StudentEntity.class))).thenReturn(studentEntity);
		
		StudentServiceException studentServiceException = assertThrows(StudentServiceException.class,
				()->{
					studentService.signupStudent(studentDto);		
				});
		
		Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
		Mockito.verify(studentEntityDao, Mockito.never()).save(Mockito.any(StudentEntity.class));
		
		assertTrue(studentServiceException.getMessage()
				.contains("You are already registered student"));
	}

	@Test
	public void updateTest() {

		StudentEntity studentEntity = DummyData.getStudentEntityForUpdate();
		StudentDto studentDto = CustomUtils.convertToStudentDto(studentEntity);

		Mockito.when(studentEntityDao.findByEmail(Mockito.anyString()))
					.thenReturn(Optional.of(studentEntity));

		Mockito.when(studentEntityDao.saveAndFlush(Mockito.any(StudentEntity.class)))
					.thenReturn(studentEntity);
		
		StudentDto returnedStudentDto = studentService.updateStudent(studentDto);
		
		Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
		Mockito.verify(studentEntityDao, Mockito.times(1))
			.saveAndFlush(Mockito.any(StudentEntity.class));
		
		assertTrue(studentDto.equals(returnedStudentDto));

	}
	
	
	@Test
	public void updateFailedTest() {

		StudentEntity studentEntity = DummyData.getStudentEntityForUpdate();
		StudentDto studentDto = CustomUtils.convertToStudentDto(studentEntity);

		Mockito.when(studentEntityDao.findByEmail(Mockito.anyString()))
					.thenReturn(Optional.empty());

		Mockito.when(studentEntityDao.saveAndFlush(Mockito.any(StudentEntity.class)))
					.thenReturn(studentEntity);
		
		StudentServiceException studentServiceException = assertThrows(
				StudentServiceException.class, ()->{
					studentService.updateStudent(studentDto);
				});
		
		Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
		Mockito.verify(studentEntityDao, Mockito.never())
			.saveAndFlush(Mockito.any(StudentEntity.class));
		
		
		assertTrue(studentServiceException.getMessage()
			.contains("Please make sure you entered your registered primary email properly"));

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

		StudentServiceException studentServiceException = assertThrows(StudentServiceException.class,
				() -> studentService.purchaseCourses("12301f3b04f21a9a8f5d507b246c3b84f20cd8a"
						+ "ead3b06214caa128cca0e65c", keysList));

		assertTrue(studentServiceException.getMessage()
					.contains("Requested courses are not found For Purchase"));

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
	  courseEntity.setCourseKey("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
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
	  
	  StudentServiceException studentServiceException = assertThrows(
	  StudentServiceException.class, ()-> 
	  			studentService.purchaseCourses("12301f3b04f21a9a8f5d507b246c3b84f20"
	  					+ "cd8aead3b06214caa128cca0e65c", keysList));
	  
	  assertTrue(studentServiceException.getMessage().
	  contains("Please remove duplicate course entries"));
	  
	}
	
	@Test 
	public void purchaseCoursesTest() {
	  
	  StudentEntity studentEntity = DummyData.getStudentEntityForUpdate();
	  
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
	  
	  StudentDto returnedStudentDto = studentService.purchaseCourses(
			  "12301f3b04f21a9a8f5d507b246c3b84f20cd8aead3b06214caa128cca0e65c", keysList);
	  
	  assertTrue(studentDto.getEmail().equalsIgnoreCase(returnedStudentDto.getEmail()));
	  
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
	  courseEntity.setCourseKey("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
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
	  
	  
	   StudentServiceException studentServiceException = assertThrows(StudentServiceException.class, 
			   ()-> studentService.cancellPurchasedCourses("12301f3b04f21a9a8f5d507b246c3b84f20"
	  					+ "cd8aead3b06214caa128cca0e65c", keysList));
	   
	  assertTrue(studentServiceException.getMessage()
			  .contains("Requested courses are not found For Deletion"));
		  
	}
	
	@Test 
	public void purchaseCoursesStudentNotFoundTest() {
	  

	  List<String> keysList = new ArrayList<>();
	  keysList.add("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
	  keysList.add("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
	  
	  Mockito.when(studentEntityDao.findByEmail(Mockito.anyString())).thenReturn(
			  				Optional.empty());
	 
	  
	  StudentServiceException studentServiceException = assertThrows(
			  StudentServiceException.class, ()-> {
				  studentService.purchaseCourses(
						"12301f3b04f21a9a8f5d507b246c3b84f20cd8aead3b06214caa128cca0e65c", 
								keysList);
			  });
	  
	  Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
	  
	  assertTrue(studentServiceException.getMessage()
			  .contains("Requested Student is not found. Please make sure you entered "
			  		+ "your registered primary email properly."));  
    }
	
	@Test 
	public void cancelPurchaseCoursesTest() {
	  
	  StudentEntity studentEntity = DummyData.getStudentEntityForUpdate();
		  
	  List<String> keysList = new ArrayList<>(); 
	  keysList.add("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
	  keysList.add("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
	  
	  List<CourseEntity> courseEntityList = new ArrayList<>();
	  
	  CourseEntity courseEntity = new CourseEntity();
	  courseEntity.setChapters(400); courseEntity.setCourseId(111L);
	  courseEntity.setCourseKey("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
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
	  courseOrderEntity.setCourseOrderKey("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
	  
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
	  courseOrderEntity.setCourseOrderKey("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
	  
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
	  
	   
	  StudentDto returnedStudentDto = studentService.cancellPurchasedCourses("12301f3b04f21a9a8f5d5"
	  		+ "07b246c3b84f20cd8aead3b06214caa128cca0e65c", keysList);
	  
	  assertTrue(returnedStudentDto.getCourseOrders() == null 
			  || returnedStudentDto.getCourseOrders().isEmpty()
			  || returnedStudentDto.getCourseOrders().size() < courseEntityList.size());
	  
	}
	 
	
	@Test 
	public void canNotcancelPurchaseCoursesDatesExccedTest() {
	  
	  StudentEntity studentEntity = DummyData.getStudentEntityForUpdate();
		  
	  List<String> keysList = new ArrayList<>(); 
	  keysList.add("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
	  keysList.add("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
	  
	  List<CourseEntity> courseEntityList = new ArrayList<>();
	  
	  CourseEntity courseEntity = new CourseEntity();
	  courseEntity.setChapters(400); courseEntity.setCourseId(111L);
	  courseEntity.setCourseKey("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
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
	  courseOrderEntity.setCourseOrderKey("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
	  
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
	  courseOrderEntity.setCourseOrderKey("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
	  
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
	  
	  StudentServiceException studentServiceException = assertThrows(StudentServiceException.class,
			  ()->studentService.cancellPurchasedCourses("12301f3b04f21a9a8f5d5"
				  		+ "07b246c3b84f20cd8aead3b06214caa128cca0e65c", keysList));
	  
	  assertTrue(studentServiceException.getMessage()
			  .contains("exceeds 30 days limits"));
	  
	}
	
	
	@Test 
	public void cancelPurchaseCoursesStudentNotFoundTest() {
	  

	  List<String> keysList = new ArrayList<>();
	  keysList.add("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
	  keysList.add("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
	  
	  Mockito.when(studentEntityDao.findByEmail(Mockito.anyString())).thenReturn(
			  				Optional.empty());
	 
	  
	  StudentServiceException studentServiceException = assertThrows(
			  StudentServiceException.class, ()-> {
				  studentService.cancellPurchasedCourses(
						"12301f3b04f21a9a8f5d507b246c3b84f20cd8aead3b06214caa128cca0e65c", 
								keysList);
			  });
	  
	  Mockito.verify(studentEntityDao, Mockito.times(1)).findByEmail(Mockito.anyString());
	  
	  assertTrue(studentServiceException.getMessage()
			  .contains("Requested Student is not found. Please make sure you entered "
			  		+ "your registered primary email properly."));
	  
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
	   
	   
	  	StudentServiceException studentServiceException = assertThrows(
	  			StudentServiceException.class, () ->{
	  				studentService.cancellPurchasedCourses("someone@gmail.com"
	  						+ "f20cd8aead3b06214caa128cca0e65c",  
	  						keysList);
	  			});
	  
	  assertTrue(studentServiceException.getMessage()
			  .contains(ErrorMessagesEnum.EMPTY_COURSES_LIST.getMessage()));
	  
	}
	
	@Test
	public void searchStudentByEmailId() {
		
		StudentEntity studentEntity = new StudentEntity();
		
		Mockito.when(studentEntityDao.findByEmail(Mockito.anyString()))
			.thenReturn(Optional.of(studentEntity));

		
		StudentDto returnedStudentDto = studentService.findByEmail("abcd@gmail.com");
		
		assertNotNull(returnedStudentDto);
	}
	
	
	@Test
	public void searchStudentByEmailIdNotFound() {
		
		Mockito.when(studentEntityDao.findByEmail(Mockito.anyString()))
			.thenReturn(Optional.empty());

		
		StudentServiceException studentServiceException = assertThrows(
				StudentServiceException.class, ()->{
					studentService.findByEmail("abcd@gmail.com");
				});
		
		assertTrue(studentServiceException.getMessage()
				.contains("Requested Student is not found. Please make sure you entered "
						+ "your registered primary email properly."));		
	}
	
	
}
