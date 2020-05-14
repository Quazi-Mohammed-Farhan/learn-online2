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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.learn.online.beans.CourseEntity;
import com.learn.online.beans.CourseOrderEntity;
import com.learn.online.beans.StudentEntity;
import com.learn.online.dtos.CourseDto;
import com.learn.online.dtos.CourseOrderDto;
import com.learn.online.dtos.StudentDto;

public class CustomUtils {

	
	private static Logger LOGGER = LogManager.getLogger(CustomUtils.class);
	
	public static StudentEntity convertToStudentEntity(StudentDto studdentDto) {
		
		LOGGER.info("CustomUtils::convertToStudentEntity() Started");
		
		StudentEntity studentEntity = new StudentEntity();
		
		LOGGER.info("Converting StudentDto to Student Entity {} {}", 
					studdentDto.getFirstName(), studdentDto.getLastName());
		
		studentEntity.setActive(studdentDto.isActive());
		studentEntity.setCountry(studdentDto.getCountry());
		studentEntity.setCity(studdentDto.getCity());
		
		List<CourseOrderEntity> courseOrderEntityList = new ArrayList<>();
		if(studdentDto.getCourseOrders() !=null) {
			
			LOGGER.info("Converting List of OrderedCourseEntity to List of OrderedCourseDto");
			
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
			tempStudentEntity.setCourseOrders(studentEntity.getCourseOrders());
			
			if(courseOrderDto.getStudent() != null) {
				
				tempStudentEntity.setActive(courseOrderDto.getStudent().isActive());
				tempStudentEntity.setCountry(courseOrderDto.getStudent().getCountry());
				
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
			}
			
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
		studentEntity.setStudentKey(CustomUtils.getSHA256());
		studentEntity.setCreationtDate(LocalDate.now());
		studentEntity.setEncryptedPassword(studdentDto.getEncryptedPassword());
		
		LOGGER.info("CustomUtils::convertToStudentEntity() Completed");
		
		return studentEntity;
	}
	
	public static CourseEntity convertToCourseEntity(CourseDto courseDto) {
		
		LOGGER.info("CustomUtils::converToCourseEntity() Started");
		
		CourseEntity courseEntity = new CourseEntity();
		
		LOGGER.info("Converting courseDto to CourseEntity");
		
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
		
		LOGGER.info("CustomUtils::converToCourseEntity() Completed");
		return courseEntity;
			
		
	}
	
	public static StudentDto convertToStudentDto(StudentEntity studentEntity) {
		
		LOGGER.info("CustomUtils::convertToStudentDto() Started");
		
		StudentDto studentDto = new StudentDto();
		
		studentDto.setActive(studentEntity.isActive());
		studentDto.setCountry(studentEntity.getCountry());
		studentDto.setCity(studentEntity.getCity());
		
		List<CourseOrderDto> courseOrderDtoList = new ArrayList<CourseOrderDto>();
		
		if(studentEntity.getCourseOrders() != null) {
			
			LOGGER.info("Converting List of CourseOrderEntity to List of CourseOrderDto");
			
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
			tempStudentDto.setCourseOrders(studentDto.getCourseOrders());
			
			if(courseOrderDto.getStudent() != null) {
				tempStudentDto.setActive(courseOrderDto.getStudent().isActive());
				tempStudentDto.setCountry(courseOrderDto.getStudent().getCountry());
				tempStudentDto.setCreationtDate(courseOrderDto.getStudent().getCreationtDate());
				tempStudentDto.setEmail(courseOrderDto.getStudent().getEmail());
				tempStudentDto.setEncryptedPassword(courseOrderDto.getStudent().getEncryptedPassword());
				tempStudentDto.setFirstName(courseOrderDto.getStudent().getFirstName());
				tempStudentDto.setLastName(courseOrderDto.getStudent().getLastName());
				tempStudentDto.setLastUpdateDate(courseOrderDto.getStudent().getLastUpdateDate());
				tempStudentDto.setPhone(courseOrderDto.getStudent().getPhone());
				tempStudentDto.setState(courseOrderDto.getStudent().getState());
				tempStudentDto.setStudentId(courseOrderDto.getStudent().getStudentId());
				tempStudentDto.setStudentKey(courseOrderDto.getStudent().getStudentKey());
				courseOrderDto.setStudent(tempStudentDto);
			}
			
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
		
		LOGGER.info("CustomUtils::convertToStudentDto() Completed");
		
		return studentDto;
	}
	
	public static CourseDto convertToCourseDto(CourseEntity courseEntity) {
		
		LOGGER.info("CustomUtils::converToCourseEntity() Started");
		
		CourseDto courseDto = new CourseDto();
		
		LOGGER.info("Converting courseDto to CourseEntity");
		
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
		
		LOGGER.info("CustomUtils::converToCourseEntity() Completed");
		
		return courseDto;
		
	}
	
	public static CourseDto convertToCourseDtoWithoutIds(CourseEntity courseEntity) {
		
		LOGGER.info("CustomUtils::convertToCourseDtoWithoutIds() Started");
		
		CourseDto courseDto = new CourseDto();
		
		LOGGER.info("Converting CourseEntity to CourseDto without initializing confidential"
				+ " information to StudentDto object");
		
		courseDto.setChapters(courseEntity.getChapters());
		courseDto.setCourseKey(courseEntity.getCourseKey());
		courseDto.setCourseName(courseEntity.getCourseName());
		courseDto.setCreationtDate(courseEntity.getCreationtDate());
		courseDto.setDescription(courseEntity.getDescription());
		courseDto.setDomainName(courseEntity.getDomainName());
		courseDto.setDurationInHours(courseEntity.getDurationInHours());
		courseDto.setLastUpdateDate(courseEntity.getLastUpdateDate());
		courseDto.setPrice(courseEntity.getPrice());
		courseDto.setRating(courseEntity.getRating());
		
		LOGGER.info("CustomUtils::convertToCourseDtoWithoutIds() Completed");
		return courseDto;
			
		
	}
	
	
	public static List<CourseOrderEntity> courseEnityListToCourseOrderEntityList(List<CourseEntity> courseEntityList) {
		
		LOGGER.info("CustomUtils::courseEnityListToCourseOrderEntityList() Started");
		
		LOGGER.info("Converting list of CourseEntity to the list of CourseEntity");
		
		if(courseEntityList != null) {
		return courseEntityList.stream().map(courseEntity -> {
			CourseOrderEntity courseOrderEntity =  new CourseOrderEntity();
			courseOrderEntity.setCourse(courseEntity);
			courseOrderEntity.setCourseOrderKey(getSHA256());
			courseOrderEntity.setCreationDate(LocalDate.now());
			courseOrderEntity.setDiscount(courseOrderEntity.getDiscount());
			courseOrderEntity.setRating(courseEntity.getRating());
			courseOrderEntity.setStudent(courseOrderEntity.getStudent());
						
			LOGGER.info("Converting list of CourseEntity to the list of CourseOrdereEntity is completed successfully");
			LOGGER.info("CustomUtils::courseEnityListToCourseOrderEntityList() Completed");
			
			return courseOrderEntity;
		}).collect(Collectors.toList());	
		} 
		
		LOGGER.info("Converting list of CourseEntity to the list of CourseOrdereEntity is completed successfully");
		LOGGER.info("CustomUtils::courseEnityListToCourseOrderEntityList() Completed");
		
		return new ArrayList<CourseOrderEntity>();
			
	}
	

	public static List<CourseOrderEntity> convertToCourseOrderEntityList(List<CourseOrderDto> courseOrderDtoList) {

		LOGGER.info("CustomUtils::convertToCourseOrderEntityList() Started");
		
		LOGGER.info("Converting list of CourseOrderDto to the list of OrderedCourseEntity");
		
		
		if(courseOrderDtoList == null) {
			
			LOGGER.info("Empty list of CourseOrderDto hence can not be converted to List of CourseOrderEntity");
			
			return new ArrayList<CourseOrderEntity>();
		}
		
		LOGGER.info("Converting list of CoursOrderDto to the list of CourseOrdereEntity is completed successfully");
		
		LOGGER.info("CustomUtils::convertToCourseOrderEntityList() Completed");

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
		
		LOGGER.info("CustomUtils::convertToCourseDtoList() Started");
		LOGGER.info("Converting list of courseEntity to list of CourseDto");
		
		if(courseEntityList == null) {
			
			LOGGER.info("Empty list of Course entity. Can not convert to list of CourseDto");
			return new ArrayList<CourseDto>();
		}
		
		LOGGER.info("CustomUtils::convertToCourseDtoList() Completed");
		
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
	
	
	public static boolean contains(String courseOrderKey, List<String> courseOrderKeyList) {
		
		LOGGER.info("CustomUtils::contains() Started");
		LOGGER.info("Checking if {} course order key exis in the list of course order keys");
		
		for(String tempCourseOrderKey : courseOrderKeyList) {
			if(courseOrderKey.equals(tempCourseOrderKey)) {
				LOGGER.info("Course order key found in the list of course order keys");
				LOGGER.info("CustomUtils::contains() Completed");
				return true;
			}
		}
		
		LOGGER.info("CustomUtils::contains() Completed");
		return false;
	}
	
	
	public static void addUmatchedCourseOrdKey(String courseOrderKey, List<String> courseKeys,
			List<String> coursesNotExistList) {
		
		LOGGER.info("CustomUtils::addUmatchedCourseOrdKey() Started");
		LOGGER.info("Compare courseKey with the list one course keys and all unmatched "
				+ "course to other of unmatched course keys ");
		
		for(String tempCourseOrderKey : courseKeys) {
			if(!courseOrderKey.equals(tempCourseOrderKey)) {
				coursesNotExistList.add(tempCourseOrderKey);
			}
		}
		
		LOGGER.info("CustomUtils::addUmatchedCourseOrdKey() Completed");
		
	}
	
	
	public static String getSHA256() {
		
		LOGGER.debug("CustomUtils::getSHA256() Started");
		
		try {
			LOGGER.debug("CustomUtils::getSHA256() will be Completed after other calls");
			return toHexString(getSHA(String.valueOf(ThreadLocalRandom.current().nextLong())));
		}
		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			LOGGER.debug("CustomUtils::getSHA256() Excpetion: {}", e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		
	}

	private static byte[] getSHA(String input) throws NoSuchAlgorithmException {
		
		LOGGER.debug("CustomUtils::getSHA() Started");
		LOGGER.debug("Static getInstance method is called with hashing SHA");
		
		// Static getInstance method is called with hashing SHA
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		// digest() method called
		// to calculate message digest of an input
		// and return array of byte
		LOGGER.debug("CustomUtils::getSHA() Completed");
		return md.digest(input.getBytes(StandardCharsets.UTF_8));
	}

	private static String toHexString(byte[] hash) {
		
		LOGGER.debug("CustomUtils::toHexString() Started");
		
		LOGGER.debug("Generating hashing");
		// Convert byte array into signum representation
		BigInteger number = new BigInteger(1, hash);

		// Convert message digest into hex value
		StringBuilder hexString = new StringBuilder(number.toString(16));

		// Pad with leading zeros
		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}

		LOGGER.debug("Hashign generation completed successfully");	
		LOGGER.debug("CustomUtils::toHexString() Completed");
		return hexString.toString();
	}

	public static StudentEntity loadStudentEntityForUpdate(StudentDto studentDto, StudentEntity studentEntity) {
		
		LOGGER.info("CustomUtils::loadStudentEntityForUpdate() Started");
		LOGGER.info("Converting StudentEntity to StudentDto for profile update hence no need to assign list of "
				+ "coure order entity from studentEntity to list of CourseOrder property of StudentDto");
		
		studentEntity.setActive(studentDto.isActive());
		studentEntity.setCity(studentDto.getCity());
		studentEntity.setCountry(studentDto.getCountry());
		studentEntity.setEmail(studentDto.getEmail());
		studentEntity.setEncryptedPassword(studentDto.getEncryptedPassword());
		studentEntity.setFirstName(studentDto.getFirstName());
		studentEntity.setLastName(studentDto.getLastName());
		studentEntity.setLastUpdateDate(LocalDate.now());
		studentEntity.setPhone(studentDto.getPhone());
		studentEntity.setState(studentDto.getState());
		studentEntity.setLastUpdateDate(LocalDate.now());
		
		LOGGER.info("CustomUtils::loadStudentEntityForUpdate() Completed");
		return studentEntity;
	}
	
	public static List<CourseEntity> convertToCourseEntityList(List<CourseDto> courseDtoList) {
		
		LOGGER.info("CustomUtils::convertToCourseEntityList() Started");
		LOGGER.info("Converting list of courseEntity to list of CourseDto");
		
		if(courseDtoList == null) {
			
			LOGGER.info("Empty list of Course entity. Can not convert to list of CourseDto");
			return new ArrayList<CourseEntity>();
		}
		
		LOGGER.info("CustomUtils::convertToCourseEntityList() Completed");
		
		return courseDtoList.stream().map(courseDto->{
			
			CourseEntity courseEntity = new CourseEntity();
			courseEntity.setChapters(courseDto.getChapters());
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
	}
	
}
