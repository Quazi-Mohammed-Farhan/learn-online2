package com.learn.online.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.learn.online.beans.CourseEntity;
import com.learn.online.beans.CourseOrderEntity;
import com.learn.online.beans.StudentEntity;
import com.learn.online.dtos.CourseDto;
import com.learn.online.dtos.CourseOrderDto;
import com.learn.online.dtos.StudentDto;
import com.learn.online.responses.CoursesResponse;

public class CustomUtils_BAKUP {

	
	public static StudentEntity convertToStudentEntity(StudentDto studdentDto) {
		StudentEntity studentEntity = new StudentEntity();
		
		studentEntity.setActive(studdentDto.isActive());
		studentEntity.setCountry(studdentDto.getCountry());
		
		List<CourseOrderEntity> courseOrderEntityList = new ArrayList<>();
		if(studdentDto.getCourseOrders() !=null) {
		courseOrderEntityList = studdentDto.getCourseOrders().stream().map(courseOrderDto->{
			
			CourseOrderEntity courseOrderEntity = new CourseOrderEntity();
			
			CourseEntity courseEntity = new CourseEntity();
			courseEntity.setChapters(courseOrderDto.getCourse().getChapters());
			courseEntity.setCourseId(courseOrderDto.getCourse().getCourseId());
			courseEntity.setCourseKey(courseOrderDto.getCourse().getCourseKey());
			courseEntity.setCourseName(courseOrderDto.getCourse().getCourseName());
			courseEntity.setCreationtDate(courseOrderDto.getCourse().getCreationtDate());
			courseEntity.setDescription(courseOrderDto.getCourse().getDescription());
			courseEntity.setDomainName(courseOrderDto.getCourse().getDomainName());
			courseEntity.setDurationInHours(courseOrderDto.getCourse().getDurationInHours());
			courseEntity.setLastUpdateDate(courseOrderDto.getCourse().getLastUpdateDate());
			courseEntity.setPrice(courseOrderDto.getCourse().getPrice());
			courseEntity.setRating(courseOrderDto.getCourse().getRating());
			
			courseOrderEntity.setCourse(courseEntity);
			
			courseOrderEntity.setCourseOrderId(courseOrderDto.getCourseOrderId());
			courseOrderEntity.setCourseOrderKey(courseOrderDto.getCourseOrderKey());
			courseOrderEntity.setCreationDate(courseOrderDto.getCreationDate());
			courseOrderEntity.setDiscount(courseOrderDto.getDiscount());
			courseOrderEntity.setLastUpdateDate(courseOrderDto.getLastUpdateDate());
			courseOrderEntity.setRating(courseOrderDto.getRating());
			
			
			StudentEntity tempStudentEntity = new StudentEntity();
			tempStudentEntity.setActive(courseOrderDto.getStudent().isActive());
			tempStudentEntity.setCountry(courseOrderDto.getStudent().getCountry());
			
			tempStudentEntity.setCourseOrders(studentEntity.getCourseOrders());
			
			tempStudentEntity.setCreationtDate(courseOrderDto.getStudent().getCreationtDate());
			tempStudentEntity.setEmail(courseOrderDto.getStudent().getEmail());
			tempStudentEntity.setEncryptedPassword(courseOrderDto.getStudent().getEncryptedPassword());
			tempStudentEntity.setFirstName(courseOrderDto.getStudent().getFirstName());
			tempStudentEntity.setLastName(courseOrderDto.getStudent().getLastName());
			tempStudentEntity.setLastUpdateDate(courseOrderDto.getStudent().getLastUpdateDate());
			tempStudentEntity.setPhone(courseOrderDto.getStudent().getPhone());
			tempStudentEntity.setState(courseOrderDto.getStudent().getState());
			tempStudentEntity.setStudentId(courseOrderDto.getStudent().getStudentId());
			tempStudentEntity.setStudentKey(courseOrderDto.getStudent().getStudentKey());
			tempStudentEntity.setCourseOrders(courseOrderEntity.getStudent().getCourseOrders());
			
			courseOrderEntity.setStudent(tempStudentEntity);
			
			return courseOrderEntity;
		}).collect(Collectors.toList());
		}
		
		studentEntity.setCourseOrders(courseOrderEntityList);
		studentEntity.setCreationtDate(studdentDto.getCreationtDate());
		studentEntity.setEmail(studdentDto.getEmail());
		studentEntity.setEncryptedPassword(studdentDto.getEncryptedPassword());
		studentEntity.setFirstName(studdentDto.getFirstName());
		studentEntity.setLastName(studdentDto.getLastName());
		studentEntity.setLastUpdateDate(studdentDto.getLastUpdateDate());
		studentEntity.setPhone(studdentDto.getPhone());
		studentEntity.setState(studdentDto.getState());
		studentEntity.setStudentId(studdentDto.getStudentId());
		studentEntity.setStudentKey(studdentDto.getStudentKey());
		studentEntity.setStudentKey(CustomUtils.getSHA256());
		studentEntity.setCreationtDate(LocalDate.now());
		
		return studentEntity;
	}
	
	public static CourseEntity converToCourseEntity(CourseDto courseDto) {
		
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
			
		
	}
	
	public static StudentDto convertToStudentDto(StudentEntity studentEntity) {
		
		StudentDto studentDto = new StudentDto();
		
		studentDto.setActive(studentEntity.isActive());
		studentDto.setCountry(studentEntity.getCountry());
		
		
		List<CourseOrderDto> courseOrderDtoList = new ArrayList<CourseOrderDto>();
		
		if(studentEntity.getCourseOrders() != null) {
		courseOrderDtoList = studentEntity.getCourseOrders().stream().map(courseOrderEntity ->{
			
			CourseOrderDto courseOrderDto = new CourseOrderDto();
			
			CourseDto courseDto = new CourseDto();
			
			courseDto.setChapters(courseOrderEntity.getCourse().getChapters());
			courseDto.setCourseId(courseOrderEntity.getCourse().getCourseId());
			courseDto.setCourseKey(courseOrderEntity.getCourse().getCourseKey());
			courseDto.setCourseName(courseOrderEntity.getCourse().getCourseName());
			courseDto.setCreationtDate(courseOrderEntity.getCourse().getCreationtDate());
			courseDto.setDescription(courseOrderEntity.getCourse().getDescription());
			courseDto.setDomainName(courseOrderEntity.getCourse().getDomainName());
			courseDto.setDurationInHours(courseOrderEntity.getCourse().getDurationInHours());
			courseDto.setLastUpdateDate(courseOrderEntity.getCourse().getLastUpdateDate());
			courseDto.setPrice(courseOrderEntity.getCourse().getPrice());
			courseDto.setRating(courseOrderEntity.getCourse().getRating());
			
			courseOrderDto.setCourse(courseDto);
			
			courseOrderDto.setCourseOrderId(courseOrderEntity.getCourseOrderId());
			courseOrderDto.setCourseOrderKey(courseOrderEntity.getCourseOrderKey());
			courseOrderDto.setCreationDate(courseOrderEntity.getCreationDate());
			courseOrderDto.setDiscount(courseOrderEntity.getDiscount());
			courseOrderDto.setLastUpdateDate(courseOrderEntity.getLastUpdateDate());
			courseOrderDto.setRating(courseOrderEntity.getRating());
			
			
			StudentDto tempStudentDto = new StudentDto();
			tempStudentDto.setActive(courseOrderEntity.getStudent().isActive());
			tempStudentDto.setCountry(courseOrderEntity.getStudent().getCountry());
			
			
			tempStudentDto.setCourseOrders(studentDto.getCourseOrders());
			
			tempStudentDto.setCreationtDate(courseOrderEntity.getStudent().getCreationtDate());
			tempStudentDto.setEmail(courseOrderEntity.getStudent().getEmail());
			tempStudentDto.setEncryptedPassword(courseOrderEntity.getStudent().getEncryptedPassword());
			tempStudentDto.setFirstName(courseOrderEntity.getStudent().getFirstName());
			tempStudentDto.setLastName(courseOrderEntity.getStudent().getLastName());
			tempStudentDto.setLastUpdateDate(courseOrderEntity.getStudent().getLastUpdateDate());
			tempStudentDto.setPhone(courseOrderEntity.getStudent().getPhone());
			tempStudentDto.setState(courseOrderEntity.getStudent().getState());
			tempStudentDto.setStudentId(courseOrderEntity.getStudent().getStudentId());
			tempStudentDto.setStudentKey(courseOrderEntity.getStudent().getStudentKey());	
			tempStudentDto.setCourseOrders(
					courseOrderEntity.getStudent().getCourseOrders().stream().map(cOrderEntity->{
						CourseOrderDto cOrderDto = new CourseOrderDto();
						
						
						CourseEntity courseEntity = cOrderEntity.getCourse();
						CourseDto cDto = new CourseDto();
						cDto.setChapters(courseEntity.getChapters());
						cDto.setCourseId(courseEntity.getCourseId());
						cDto.setCourseKey(courseEntity.getCourseKey());
						cDto.setCourseName(courseEntity.getCourseName());
						cDto.setCreationtDate(courseEntity.getCreationtDate());
						cDto.setDescription(courseEntity.getDescription());
						cDto.setDomainName(courseEntity.getDomainName());
						cDto.setDurationInHours(courseEntity.getDurationInHours());
						cDto.setPrice(courseEntity.getPrice());
						cDto.setRating(courseEntity.getRating());
						
						cOrderDto.setCourse(cDto);
						cOrderDto.setCourseOrderId(cOrderEntity.getCourseOrderId());
						cOrderDto.setCourseOrderKey(cOrderEntity.getCourseOrderKey());
						cOrderDto.setCreationDate(cOrderEntity.getCreationDate());
						cOrderDto.setDiscount(cOrderEntity.getDiscount());
						cOrderDto.setLastUpdateDate(cOrderEntity.getLastUpdateDate());
						cOrderDto.setRating(cOrderEntity.getRating());
						//cOrderDto.setStudent(cOrderEntity.getStudent());
						
						return cOrderDto;
					}).collect(Collectors.toList())
				 );
			
			
			courseOrderDto.setStudent(tempStudentDto);
			
			return courseOrderDto;
		}).collect(Collectors.toList());
	    }
		
		studentDto.setCourseOrders(courseOrderDtoList);
		studentDto.setCreationtDate(studentEntity.getCreationtDate());
		studentDto.setEmail(studentEntity.getEmail());
		studentDto.setEncryptedPassword(studentEntity.getEncryptedPassword());
		studentDto.setFirstName(studentEntity.getFirstName());
		studentDto.setLastName(studentEntity.getLastName());
		studentDto.setLastUpdateDate(studentEntity.getLastUpdateDate());
		studentDto.setPhone(studentEntity.getPhone());
		studentDto.setState(studentEntity.getState());
		studentDto.setStudentId(studentEntity.getStudentId());
		studentDto.setStudentKey(studentEntity.getStudentKey());
		
		return studentDto;
	}
	
	public static CourseDto convertToCourseDto(CourseEntity courseEntity) {
		
		CourseDto courseDto = new CourseDto();
		
		courseDto.setChapters(courseEntity.getChapters());
		courseDto.setCourseId(courseEntity.getCourseId());
		courseDto.setCourseKey(courseEntity.getCourseKey());
		courseDto.setCourseName(courseEntity.getCourseName());
		courseDto.setCreationtDate(courseEntity.getCreationtDate());
		courseDto.setDescription(courseEntity.getDescription());
		courseDto.setDomainName(courseEntity.getDomainName());
		courseDto.setDurationInHours(courseEntity.getDurationInHours());
		courseDto.setLastUpdateDate(courseEntity.getLastUpdateDate());
		courseDto.setPrice(courseEntity.getPrice());
		courseDto.setRating(courseEntity.getRating());
		
		return courseDto;
			
		
	}
	
	public static List<CourseOrderEntity> courseEnityListToCourseOrderEntityList(List<CourseEntity> courseEntityList) {
		
		if(courseEntityList != null) {
		return courseEntityList.stream().map(courseEntity -> {
			CourseOrderEntity courseOrderEntity =  new CourseOrderEntity();
			courseOrderEntity.setCourse(courseEntity);
			courseOrderEntity.setCourseOrderKey(getSHA256());
			courseOrderEntity.setCreationDate(LocalDate.now());
			courseOrderEntity.setDiscount(courseOrderEntity.getDiscount());
			courseOrderEntity.setRating(courseEntity.getRating());
			courseOrderEntity.setStudent(courseOrderEntity.getStudent());
			
			return courseOrderEntity;
		}).collect(Collectors.toList());	
		} 
		
		return new ArrayList<CourseOrderEntity>();
			
	}
	

	public static List<CourseOrderEntity> convertToCourseOrderEntityList(List<CourseOrderDto> courseOrderDtoList) {
		
		
		if(courseOrderDtoList == null) {
			return new ArrayList<CourseOrderEntity>();
		}
		
		return courseOrderDtoList.stream().map(courseOrderDto -> {
			
			CourseOrderEntity courseOrderEntity = new CourseOrderEntity();
			
			CourseEntity courseEntity = new CourseEntity();
			courseEntity.setChapters(courseOrderDto.getCourse().getChapters());        
			courseEntity.setCourseId(courseOrderDto.getCourse().getCourseId());
			courseEntity.setCourseKey(courseOrderDto.getCourse().getCourseKey());
			courseEntity.setCourseName(courseOrderDto.getCourse().getCourseName());
			courseEntity.setCreationtDate(courseOrderDto.getCourse().getCreationtDate());
			courseEntity.setDescription(courseOrderDto.getCourse().getDescription());
			courseEntity.setDomainName(courseOrderDto.getCourse().getDomainName());
			courseEntity.setDurationInHours(courseOrderDto.getCourse().getDurationInHours());
			courseEntity.setLastUpdateDate(courseOrderDto.getCourse().getLastUpdateDate());
			courseEntity.setPrice(courseOrderDto.getCourse().getPrice());
			courseEntity.setRating(courseOrderDto.getCourse().getRating());
			
			courseOrderEntity.setCourse(courseEntity);
			
			courseOrderEntity.setCourseOrderId(courseOrderDto.getCourseOrderId());
			courseOrderEntity.setCourseOrderKey(courseOrderDto.getCourseOrderKey());
			courseOrderEntity.setCreationDate(courseOrderDto.getCreationDate());
			courseOrderEntity.setDiscount(courseOrderDto.getDiscount());
			courseOrderEntity.setLastUpdateDate(courseOrderDto.getLastUpdateDate());
			courseOrderEntity.setRating(courseOrderDto.getRating());
			
			
			StudentEntity studentEntity = new StudentEntity();
			
			studentEntity.setActive(courseOrderDto.getStudent().isActive());
			studentEntity.setCountry(courseOrderDto.getStudent().getCountry());
			
			
			List<CourseOrderEntity> tempCourseOrderEntityList = courseOrderDto.getStudent().getCourseOrders().stream().map(tempCourseOrderDto ->{
				
				CourseOrderEntity tempCourseOrderEntity = new CourseOrderEntity();
				
				tempCourseOrderEntity.setCourse(courseOrderEntity.getCourse());
				tempCourseOrderEntity.setCourseOrderId(tempCourseOrderDto.getCourseOrderId());
				tempCourseOrderEntity.setCourseOrderKey(tempCourseOrderDto.getCourseOrderKey());
				tempCourseOrderEntity.setCreationDate(tempCourseOrderDto.getCreationDate());
				tempCourseOrderEntity.setDiscount(tempCourseOrderDto.getDiscount());
				tempCourseOrderEntity.setLastUpdateDate(tempCourseOrderDto.getLastUpdateDate());
				tempCourseOrderEntity.setRating(tempCourseOrderDto.getRating());
				tempCourseOrderEntity.setStudent(studentEntity);
			    return tempCourseOrderEntity;
			}).collect(Collectors.toList());
			
			studentEntity.setCourseOrders(tempCourseOrderEntityList);
			
			studentEntity.setCreationtDate(courseOrderDto.getStudent().getCreationtDate());
			studentEntity.setEmail(courseOrderDto.getStudent().getEmail());
			studentEntity.setEncryptedPassword(courseOrderDto.getStudent().getEncryptedPassword());
			studentEntity.setFirstName(courseOrderDto.getStudent().getFirstName());
			studentEntity.setLastName(courseOrderDto.getStudent().getLastName());
			studentEntity.setLastUpdateDate(courseOrderDto.getStudent().getLastUpdateDate());
			studentEntity.setPhone(courseOrderDto.getStudent().getPhone());
			studentEntity.setState(courseOrderDto.getStudent().getState());
			studentEntity.setStudentId(courseOrderDto.getStudent().getStudentId());
			studentEntity.setStudentKey(courseOrderDto.getStudent().getStudentKey());
			
			courseOrderEntity.setStudent(studentEntity);
			
			return courseOrderEntity;
			
		}).collect(Collectors.toList());
	}
	
	
	public static List<CourseDto> convertToCourseDtoList(List<CourseEntity> courseEntityList) {
		
		if(courseEntityList == null) {
			return new ArrayList<CourseDto>();
		}
		
		return courseEntityList.stream().map(courseEntity->{
			
			CourseDto courseDto = new CourseDto();
			courseDto.setChapters(courseEntity.getChapters());
			courseDto.setCourseId(courseEntity.getCourseId());
			courseDto.setCourseKey(courseEntity.getCourseKey());
			courseDto.setCourseName(courseEntity.getCourseName());
			courseDto.setCreationtDate(courseEntity.getCreationtDate());
			courseDto.setDescription(courseEntity.getDescription());
			courseDto.setDomainName(courseEntity.getDomainName());
			courseDto.setDurationInHours(courseEntity.getDurationInHours());
			courseDto.setLastUpdateDate(courseEntity.getLastUpdateDate());
			courseDto.setPrice(courseEntity.getPrice());
			courseDto.setRating(courseEntity.getRating());
			
			return courseDto;
			
		}).collect(Collectors.toList());
	}
	
	public static List<CoursesResponse> generateWelcomeResponse() {
		List<CoursesResponse> courseResponseList = new ArrayList<>();
		CoursesResponse coursesResponse = new CoursesResponse();
		
		coursesResponse.setCourseName("Microservice Zero to Hero");
		coursesResponse.setDescription("Make yourself better and equip yourself with "
				+ "necessary skills to stay atop in ever chaning globe");
		coursesResponse.setPrice(420D);
		coursesResponse.setDomainName("Java");
		coursesResponse.setRating(5D);
		coursesResponse.setChapters(200);
		coursesResponse.setDurationInHours(750);
		coursesResponse.setCreationtDate(LocalDate.of(2017, 6, 7));
		coursesResponse.setLastUpdateDate(LocalDate.of(2019, 1, 1));
		coursesResponse.setUrl("https://learn-online/courses/java/microservice_zero_to_hero");
		courseResponseList.add(coursesResponse);
		
		coursesResponse = new CoursesResponse();
		
		coursesResponse.setCourseName("SpringBoot, Spring MVC and RestFul WebServices");
		coursesResponse.setDescription("This course is built for beginners, intermidiate and expert students"
				+ " Bigginer learn and become expert and intermidate or expert can upskill and upgrade."
				+ " It also help you to work on your weak area to improve. It also offer your "
				+ "JUnit, Mockito, PowerMock, LogBack and different logging tutorial");
		
		coursesResponse.setPrice(420D);
		coursesResponse.setDomainName("Java");
		coursesResponse.setRating(5D);
		coursesResponse.setChapters(400);
		coursesResponse.setDurationInHours(880);
		coursesResponse.setCreationtDate(LocalDate.of(2019, 1, 1));
		coursesResponse.setLastUpdateDate(LocalDate.of(2020, 1, 1));
		coursesResponse.setUrl("https://learn-online/courses/java/sprintboot-microservices-restful-testing");
		courseResponseList.add(coursesResponse);
		
		coursesResponse.setCourseName("Linux for Bigginers and experts");
		coursesResponse.setDescription("This course is built for beginners, intermidiate students"
				+ " Bigginer learn and become expert and intermidate works on your weak area to improve. "
				+ " After completion of this course you can even write your own shell scripts. "
				+ " This course give hand on learning and shows you cheat sheet of commands. "
				+ " We have also coverd all design and artechture of hardware and OS, Software to "
				+ " make you clear understanding. We also showed you deployment of verious application"
				+ " and also showed about dockers, their command and how to wrok with dockers");
		
		coursesResponse.setPrice(420D);
		coursesResponse.setDomainName("Unix/Linux");
		coursesResponse.setRating(5D);
		coursesResponse.setChapters(400);
		coursesResponse.setDurationInHours(500);
		coursesResponse.setCreationtDate(LocalDate.of(2018, 7, 15));
		coursesResponse.setLastUpdateDate(LocalDate.of(2019, 8, 25));
		coursesResponse.setUrl("https://learn-online/courses/os/linux-unix/total-linux-solution");
		courseResponseList.add(coursesResponse);
		
		return courseResponseList;
	}
	
	
	public static String getSHA256() {
		try {
			return toHexString(getSHA(String.valueOf(ThreadLocalRandom.current().nextLong())));
		}
		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private static byte[] getSHA(String input) throws NoSuchAlgorithmException {
		// Static getInstance method is called with hashing SHA
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		// digest() method called
		// to calculate message digest of an input
		// and return array of byte
		return md.digest(input.getBytes(StandardCharsets.UTF_8));
	}

	private static String toHexString(byte[] hash) {
		// Convert byte array into signum representation
		BigInteger number = new BigInteger(1, hash);

		// Convert message digest into hex value
		StringBuilder hexString = new StringBuilder(number.toString(16));

		// Pad with leading zeros
		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}

		return hexString.toString();
	}
}
