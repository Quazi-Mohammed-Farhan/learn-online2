package com.learn.online.services.impls;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.online.beans.CourseEntity;
import com.learn.online.beans.CourseOrderEntity;
import com.learn.online.beans.StudentEntity;
import com.learn.online.daos.CourseEntityDao;
import com.learn.online.daos.StudentEntityDao;
import com.learn.online.dtos.StudentDto;
import com.learn.online.enums.ErrorMessagesEnum;
import com.learn.online.exceptions.StudentServiceException;
import com.learn.online.services.StudentService;
import com.learn.online.utils.CustomUtils;

@Service
public class StudentServiceImpl implements StudentService {

	private static Logger LOGGER = LogManager.getLogger(StudentServiceImpl.class);
	
	@Autowired
	private StudentEntityDao studentEntityDao;
	
	@Autowired
	private CourseEntityDao courseEntityDao;
	
	@Override
	public StudentDto findByEmail(String email) {
		
		LOGGER.info("StudentServiceImpl::findByEmail() Started");
		
		LOGGER.info("Input: Student email: ");
		
		LOGGER.info("Search the student by email");
		
		LOGGER.info("StudentServiceImpl::findByEmail() Completed");
		
		return CustomUtils.convertToStudentDto(studentEntityDao.findByEmail(email.toLowerCase())
				.orElseThrow(()-> new StudentServiceException(
						ErrorMessagesEnum.REQUESTED_STUDENT_NOT_FOUND.getMessage())));
	}

	@Override
	public StudentDto signupStudent(StudentDto studentDto) {
		
		LOGGER.info("StudentServiceImpl::signupStudent() Started");
		
		LOGGER.info("Input: Student details {} {}", studentDto.getFirstName(), studentDto.getLastName());
		
		LOGGER.info("Save student details for signup");
		
		if(studentEntityDao.findByEmail(studentDto.getEmail().toLowerCase()).isPresent()) {
			
			LOGGER.info("Throwing StudentServiceException because some other student signed up with email");
			throw new StudentServiceException(ErrorMessagesEnum.DUPLICATE_STUDENT_ENTRY.getMessage());
		}
		
		LOGGER.info("Student details saved successfully for signup");
		
		LOGGER.info("StudentServiceImpl::signupStudent() Completed");
				
		return CustomUtils.convertToStudentDto(studentEntityDao.save(
				CustomUtils.convertToStudentEntity(studentDto)));
	}

	
	@Override
	public StudentDto updateStudent(StudentDto studentDto) {
		
		LOGGER.info("StudentServiceImpl::updateStudent() Started");
		
		LOGGER.info("Input: Student details {} {}", studentDto.getFirstName(), studentDto.getLastName());
		
		LOGGER.info("Updating student details for signup");
		
		StudentEntity studentEntity = studentEntityDao.findByEmail(studentDto.getEmail().toLowerCase())
				.orElseThrow(()-> new StudentServiceException(
					ErrorMessagesEnum.REQUESTED_STUDENT_NOT_FOUND.getMessage()));
		
		
		studentEntityDao.saveAndFlush(CustomUtils.loadStudentEntityForUpdate(studentDto, studentEntity));
		LOGGER.info("Student detailed updated successfully");
		
		LOGGER.info("StudentServiceImpl::signupStudent() Completed");
		
		return CustomUtils.convertToStudentDto(studentEntity);
		
	}

	@Override 
	public StudentDto purchaseCourses(String email, List<String> courseKeys) {
		
		LOGGER.info("StudentServiceImpl::purchaseCourses() Started");
		
		LOGGER.info("Input: Student email");
		
		LOGGER.info("Fetching student details by email to let him purchase courses");
		
		StudentEntity studentEntity = studentEntityDao.findByEmail(email.toLowerCase()).orElseThrow(()-> 
	 	new StudentServiceException(ErrorMessagesEnum.REQUESTED_STUDENT_NOT_FOUND.getMessage()));
		
		LOGGER.info("Fetching courses details by courses key");
		
		List<CourseEntity> courseEntityList = courseEntityDao.findCoursesByKey(courseKeys)
				.orElseThrow(()-> new StudentServiceException(
					ErrorMessagesEnum.REQUESTED_COURSES_NOT_FOUND_FOR_PURCHASE.getMessage()));
	
		LOGGER.info("Student details and courses are found. Now purchasing courses for students");
		
		List<CourseOrderEntity> courseOrdEntityList = studentEntity.getCourseOrders();
		List<String> duplicateCourseList = new ArrayList<String>();
		
		LOGGER.info("Applying bussiness logic for example. Can student purchase them");
		
		courseKeys.forEach(key->{
			
			for(CourseOrderEntity tempCourseOrdEntity : courseOrdEntityList) {
				
				if(tempCourseOrdEntity.getCourse().getCourseKey().equals(key)) {
					duplicateCourseList.add(tempCourseOrdEntity.getCourse().getCourseName());
				}
			}		
			
		});
		
		
		if(duplicateCourseList.isEmpty()) {
			
			studentEntity.addCourseOrders(
					CustomUtils.courseEnityListToCourseOrderEntityList(courseEntityList));
			
			LOGGER.info("Student purchase courses {} successfully", courseEntityList.size());
			
			LOGGER.info("StudentServiceImpl::purchaseCourses() Completed");
			
			return CustomUtils.convertToStudentDto(studentEntityDao.save(studentEntity));
			
		} else {
			
			LOGGER.info("Courses can not be purchased because student already purchased one them hence "
					+ "throwing StudentService Exception. Number of courses {} ", courseEntityList.size());
			
			throw new StudentServiceException(
					String.format(ErrorMessagesEnum.BUYING_DUPLICATE_COURSES.getMessage(), 
							duplicateCourseList.stream().collect(Collectors.joining(", "))));
		}
		
	}
	

	@Override
	public StudentDto cancellPurchasedCourses(String email, List<String> courseKeys) {
		
		LOGGER.info("StudentServiceImpl::cancellPurchasedCourses() Started");
		
		LOGGER.info("Input: Student email");
		
		LOGGER.info("Fetching student details by email to let him cancel courses");
		
		StudentEntity studentEntity = studentEntityDao.findByEmail(email.toLowerCase()).orElseThrow(()-> 
		 	new StudentServiceException(ErrorMessagesEnum.REQUESTED_STUDENT_NOT_FOUND.getMessage()));
		
		LocalDate currentDate = LocalDate.now();
		
		List<CourseOrderEntity> courseToBeDeletedList = new ArrayList<>();
		List<String> coursesExceeds30DaysList = new ArrayList<String>();
		List<String> coursesNotExistList = new ArrayList<String>();
		
		LOGGER.info("Applying bussiness logic for example. Can student purchase them");
		
		List<CourseOrderEntity> courseOrdEntityList = studentEntity.getCourseOrders();
		
		if(courseOrdEntityList.isEmpty()) {
			
			LOGGER.info("Can not canccel pruchased courses becuase course list is empty");
			throw new StudentServiceException(ErrorMessagesEnum.EMPTY_COURSES_LIST.getMessage());
		} 
		
		courseKeys.forEach(key->{
			
			boolean found = false;
			CourseOrderEntity courseOrdEntity = null;
			
			for(CourseOrderEntity tempCourseOrdEntity : courseOrdEntityList) {
				
				if(tempCourseOrdEntity.getCourseOrderKey().equals(key)) {
					courseOrdEntity = tempCourseOrdEntity;
					found = true;
					break;
				}
			}
				
			if(found) {
				if(ChronoUnit.DAYS.between(courseOrdEntity.getCreationDate(), currentDate) <= 30) {					
					courseToBeDeletedList.add(courseOrdEntity);
				} else {
					coursesExceeds30DaysList.add(courseOrdEntity.getCourse().getCourseName());
				}
			} else {
				coursesNotExistList.add(key);
			}
			
		});
		
		
		if(!coursesNotExistList.isEmpty()) {
		
			LOGGER.info("Can not cancel course purchased because student does not have courses to delete");	
			throw new StudentServiceException(
					ErrorMessagesEnum.REQUESTED_COURSES_NOT_FOUND_FOR_DELETE.getMessage());
		} else if(!coursesExceeds30DaysList.isEmpty()) {
			
			LOGGER.info("purchased courses can not be cancelled because courses can not cancelled "
					+ "after 30 days of purchase. Numner of courses exceeded 30 days: {}", 
					coursesExceeds30DaysList.size());
			
			throw new StudentServiceException(ErrorMessagesEnum
					.COURSES_EXCEED_30DAYS_CAN_NOT_BE_DELETED.getMessage());
		} else {
		
			studentEntity.removeCourseOrders(courseToBeDeletedList);
			
			LOGGER.info("{} purchased courses are cancelled successfully", courseToBeDeletedList.size());
			studentEntityDao.saveAndFlush(studentEntity);
		}
		
		LOGGER.info("StudentServiceImpl::cancellPurchasedCourses() Completed");
		
		return CustomUtils.convertToStudentDto(studentEntity);
		
	}	
}
