package com.learn.online.dummies;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.learn.online.beans.AuthorityEntity;
import com.learn.online.beans.CourseEntity;
import com.learn.online.beans.CourseOrderEntity;
import com.learn.online.beans.RoleEntity;
import com.learn.online.beans.StudentEntity;
import com.learn.online.dtos.CourseDto;
import com.learn.online.dtos.CourseOrderDto;
import com.learn.online.dtos.StudentDto;
import com.learn.online.securities.UserPrincipal;
import com.learn.online.utils.CustomUtils;

public interface DummyData {
	/*
	public final static String STUDENT_JSON_INPUT = "{\"firstName\":\"chacha\","
			+ "\"lastName\":\"kaka\",\"email\":\"chacha@gmail.com\","
			+ "\"reemail\":\"chacha@gmail.com\",\"password\":\"x1cK$1nt\","
			+ "\"repassword\":\"x1cK$1nt\",\"active\":\"true\",\"phone\":\"987654321\","
			+ "\"city\":\"Bangalore\",\"state\":\"KA\",\"country\":\"India\","
			+ "\"studentKey\":\"12301f3b04f21a9a8f5d507b246c3b84f20cd8aead3b06214caa128cca0e65c\"}";*/
	
	public final static String STUDENT_JSON_INPUT = "{\"firstName\":\"chacha\","
			+ "\"lastName\":\"kaka\",\"email\":\"chacha@gmail.com\","
			+ "\"reemail\":\"chacha@gmail.com\",\"password\":\"x1cK$1nt\","
			+ "\"repassword\":\"x1cK$1nt\",\"active\":\"true\",\"phone\":\"9987654321\","
			+ "\"city\":\"Bangalore\",\"state\":\"KA\",\"country\":\"India\"}";
	
	public final static String STUDENT_JSON_CREATE_OUTPUT = 
			"{\"responseDetail\":{\"studentKey\":\"12301f3b04f21a9a8f5d507b246c3b84f20cd8a"
			+ "ead3b06214caa128cca0e65c\"},\"message\":\"Student added/signup is successfully\","
			+ "\"status\":\"SUCCESS\"}";
	
	public final static String STUDENT_JSON_UPDATE_OUTPUT = 
			"{\"responseDetail\":{\"studentKey\":\"12301f3b04f21a9a8f5d507b246c3b84f20cd8aea"
			+ "d3b06214caa128cca0e65c\"},\"message\":\"Student update is successfull\","
			+ "\"status\":\"SUCCESS\"}";
	
	public final static String COURSES_TO_BUY_JSON_INPUT =
			"{\"studentEmail\": \"admin@gmail.com\",\"courseKeys\": [\"2e552bb07890a68f4563bc"
			+ "2beaa1a8227aae55106f202494d570cb5ab8ce5fb0\","
			+ "\"3e633d515cb7e087749d52f2f95567ded8b326928c7ad876e32939a26717046e\"]}";
	
	public final static String PURCHASED_COURSES_EXPECTED_RESPONSE = 
			"{\"responseDetail\":{\"studentKey\":\"12301f3b04f21a9a8f5d507b246c3b84f20cd"
			+ "8aead3b06214caa128cca0e65c\"},\"message\":\"Your courses purchased order have been "
			+ "placed successfully\",\"status\":\"SUCCESS\"}";
	
	public final static String COURSES_TO_CANCEL_JSON_INPUT = 
			"{\"studentEmail\": \"admin@gmail.com\",\"courseKeys\": [\"2e552bb07890a68f4563bc2bea"
			+ "a1a8227aae55106f202494d570cb5ab8ce5fb0\","
			+ "\"3e633d515cb7e087749d52f2f95567ded8b326928c7ad876e32939a26717046e\"]}";
	
	public final static String CANCELLED_COURSES_EXPECTED_RESPONSE = 
			"{\"responseDetail\":{\"studentKey\":\"12301f3b04f21a9a8f5d507b246c3b84f20cd8aea"
			+ "d3b06214caa128cca0e65c\"},\"message\":\"Courses canncellation request proccessed "
			+ "sucessfully\",\"status\":\"SUCCESS\"}";
	
	public static final String COURSES_BY_DOMAIN_AND_RATING_EXPECTED_VALUE = "{\"responseDetail\""
			+ ":{\"Java\":{\"5.8\":[{\"courseKey\":\"2e552bb07890a68f4563bc2beaa1a8227aae55106f2024"
			+ "94d570cb5ab8ce5fb0\",\"domainName\":\"Java\",\"courseName\":\"Microservice Zero to Hero\","
			+ "\"description\":\"Take you from beggining to Expert level quickly\","
			+ "\"chapters\":200,\"durationInHours\":500,\"price\":420.0,\"rating\":5.8,"
			+ "\"creationtDate\":\"2018-06-10\",\"lastUpdateDate\":\"2020-01-01\"}],"
			+ "\"5.0\":[{\"courseKey\":\"d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173"
			+ "a578e4e6a9a4e\",\"domainName\":\"Java\",\"courseName\":\"Restful Web Service with "
			+ "Spring boot\",\"description\":\"Give you hands on experience on creating project with"
			+ " Restful WebService, JDK8 and Spring Boot\",\"chapters\":180,\"durationInHours\":200,"
			+ "\"price\":420.0,\"rating\":5.0,\"creationtDate\":\"2016-09-28\","
			+ "\"lastUpdateDate\":\"2019-08-28\"},{\"courseKey\":\"3e633d515cb7e087749d52f2f95567ded8b3"
			+ "26928c7ad876e32939a26717046e\",\"domainName\":\"Java\",\"courseName\":\"JMS, RabiitMQ, "
			+ "ActiveMQ, Kubernate, Multi Theading\",\"description\":\"This course is good for"
			+ " advance java student. It will teach effective use JMS, Asynchronous Programming, Thead"
			+ " handling and testing using JUNIT, Mockito, PowerMock, user Sonar Qube "
			+ "and Code Coverage\",\"chapters\":200,\"durationInHours\":800,\"price\":500.0,"
			+ "\"rating\":5.0,\"creationtDate\":\"2020-02-20\",\"lastUpdateDate\":\"2020-04-28\"},"
			+ "{\"courseKey\":\"67a7b6e00304c3605df81c1d578ff6f7f8ce63bc5b9acb\","
			+ "\"domainName\":\"Java\",\"courseName\":\"Java Design Pattern and UML\","
			+ "\"description\":\"It teaches you design patterns, Design pattern in Java "
			+ "programming and UML designing\",\"chapters\":500,\"durationInHours\":200,\"price\":420.0,"
			+ "\"rating\":5.0,\"creationtDate\":\"2020-01-29\"}]},"
			+ "\"dev\":{\"4.0\":[{\"courseKey\":\"7a84d7b03398b0978e9bcf23e3a7a102e397b1236141ee71d"
			+ "9b5c8ecda6d9ad6\",\"domainName\":\"dev\",\"courseName\":\"AWS Deployment\","
			+ "\"description\":\"Course is build for Bignner, and Expert and make you confident DWAPS\","
			+ "\"chapters\":150,\"durationInHours\":300,\"price\":500.0,\"rating\":4.0,\"creationtDate\":\"2020-01-01\",\"lastUpdateDate\":\"2020-10-30\"}]}},\"message\":\"Courses search by domain and rating is successful\",\"status\":\"SUCCESS\"}";
	
	
	public final static String COURSES_BY_DOMAIN_EXPECTED_VALUE = "{\"responseDetail\""
			+ ":{\"Java\":[{\"courseKey\":\"d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f117"
			+ "3a578e4e6a9a4e\",\"domainName\":\"Java\",\"courseName\":"
			+ "\"Restful Web Service with Spring boot\",\"description\":\"Give you hands on "
			+ "experience on creating project with Restful WebService, JDK8 and Spring Boot\","
			+ "\"chapters\":180,\"durationInHours\":200,\"price\":420.0,\"rating\":5.0,"
			+ "\"creationtDate\":\"2016-09-28\",\"lastUpdateDate\":\"2019-08-28\"},"
			+ "{\"courseKey\":\"3e633d515cb7e087749d52f2f95567ded8b326928c7ad876e32939a26717046e\","
			+ "\"domainName\":\"Java\",\"courseName\":\"JMS, RabiitMQ, ActiveMQ, Kubernate,"
			+ " Multi Theading\",\"description\":\"This course is good for advance java student. "
			+ "It will teach effective use JMS, Asynchronous Programming, Thead handling and testing "
			+ "using JUNIT, Mockito, PowerMock, user Sonar Qube and Code Coverage\",\"chapters\":200,"
			+ "\"durationInHours\":800,\"price\":500.0,\"rating\":5.0,\"creationtDate\":\"2020-02-20\","
			+ "\"lastUpdateDate\":\"2020-04-28\"},{\"courseKey\":\"67a7b6e00304c3605df81c1d578ff6"
			+ "f7f8ce63bc5b9acb\",\"domainName\":\"Java\",\"courseName\":\"Java Design Pattern and UML\","
			+ "\"description\":\"It teaches you design patterns, Design pattern in Java programming "
			+ "and UML designing\",\"chapters\":500,\"durationInHours\":200,\"price\":420.0,"
			+ "\"rating\":5.0,\"creationtDate\":\"2020-01-29\"},"
			+ "{\"courseKey\":\"2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0\","
			+ "\"domainName\":\"Java\",\"courseName\":\"Microservice Zero to Hero\","
			+ "\"description\":\"Take you from beggining to Expert level quickly\",\"chapters\":200,"
			+ "\"durationInHours\":500,\"price\":420.0,\"rating\":5.8,\"creationtDate\":\"2018-06-10\","
			+ "\"lastUpdateDate\":\"2020-01-01\"}],\"dev\":[{\"courseKey\":\"7a84d7b03398b0978e9bcf"
			+ "23e3a7a102e397b1236141ee71d9b5c8ecda6d9ad6\",\"domainName\":\"dev\","
			+ "\"courseName\":\"AWS Deployment\",\"description\":\"Course is build for Bignner, and "
			+ "Expert and make you confident DWAPS\",\"chapters\":150,\"durationInHours\":300,"
			+ "\"price\":500.0,\"rating\":4.0,\"creationtDate\":\"2020-01-01\","
			+ "\"lastUpdateDate\":\"2020-10-30\"}]},\"message\":\"Courses search by domain and "
			+ "rating is successful\",\"status\":\"SUCCESS\"}";
	
	public final static String STUDENT_BY_EMAIL = "{\"responseDetail\":{\"studentKey\":\"12301f3b04f21a9a8f5d507b246c3b84f20cd8aead3b06214caa128cca0e65c\",\"firstName\":\"Farhan\",\"lastName\":\"Quazi\",\"email\":\"farhan@gmail.com\",\"encryptedPassword\":\"**********\",\"phone\":\"1234567869\",\"country\":\"India\",\"state\":\"KA\",\"courseOrders\":[{\"courseOrderId\":0,\"course\":{\"courseId\":0,\"courseKey\":\"2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0\",\"domainName\":\"Java\",\"courseName\":\"Microservice Zero to Hero\",\"description\":\"Take you from beggining to Expert level quickly\",\"chapters\":200,\"durationInHours\":500,\"price\":420.0,\"rating\":5.8,\"creationtDate\":\"2018-06-10\",\"lastUpdateDate\":\"2020-01-01\"},\"rating\":5.8,\"creationDate\":\"2020-02-28\",\"lastUpdateDate\":\"2020-01-01\",\"courseOrderKey\":\"bbb9f61ed51461fde2400c6aee189d1ef5f90c832514ddc6cf490f9f8c9fd6e8\"},{\"courseOrderId\":0,\"course\":{\"courseId\":0,\"courseKey\":\"d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e\",\"domainName\":\"Java\",\"courseName\":\"Restful Web Service with Spring boot\",\"description\":\"Give you hands on experience on creating project with Restful WebService, JDK8 and Spring Boot\",\"chapters\":180,\"durationInHours\":200,\"price\":420.0,\"rating\":5.0,\"creationtDate\":\"2016-09-28\",\"lastUpdateDate\":\"2020-01-01\"},\"rating\":5.0,\"creationDate\":\"2020-05-02\",\"courseOrderKey\":\"30ef27b8c959123ec2971a4fcfaf36ff6c46d1090b40592fb02f3a8cafd4221e\"}],\"active\":false,\"creationtDate\":\"2020-04-28\",\"lastUpdateDate\":\"2020-05-02\"},\"message\":\"Data Found\",\"status\":\"SUCCESS\"}";
	
	
	public static StudentDto getStudentDto() {
		
		CourseOrderDto courseOrderDto = new CourseOrderDto();
		List<CourseOrderDto> courseOrderDtoList = new ArrayList<CourseOrderDto>();
		CourseDto courseDto = new CourseDto();
		StudentDto studentDto = new StudentDto();
		
		//1
		courseDto.setChapters(200);
		courseDto.setCourseId(101L);
		courseDto.setCourseKey("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
		courseDto.setCourseName("Microservice Zero to Hero");
		courseDto.setCreationtDate(LocalDate.of(2018, 06, 10));
		courseDto.setDescription("Take you from beggining to Expert level quickly");
		courseDto.setDomainName("Java");
		courseDto.setDurationInHours(500);
		courseDto.setPrice(420D);
		courseDto.setLastUpdateDate(LocalDate.of(2020, 1, 01));
		courseDto.setRating(5.8D);
		courseOrderDto.setCourse(courseDto);
		courseOrderDto.setRating(5.8D);
		courseOrderDto.setCreationDate(LocalDate.of(2020, 2, 28));
		courseOrderDto.setLastUpdateDate(LocalDate.of(2020, 1, 1));
		courseOrderDto.setDiscount(null);
		courseOrderDto.setCourseOrderKey("bbb9f61ed51461fde2400c6aee189d1ef5f90c832514ddc6cf490f9"
						+ "f8c9fd6e8");
		courseOrderDto.setStudent(null);
		courseOrderDtoList.add(courseOrderDto);
		
		/////2
		courseDto = new CourseDto();
		courseDto.setChapters(180);
		courseDto.setCourseId(102L);
		courseDto.setCourseKey("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
		courseDto.setCourseName("Restful Web Service with Spring boot");
		courseDto.setCreationtDate(LocalDate.of(2016, 9, 28));
		courseDto.setDescription("Give you hands on experience on creating project with Restful "
					+ "WebService, JDK8 and Spring Boot");
		courseDto.setDomainName("Java");
		courseDto.setDurationInHours(200);
		courseDto.setPrice(420D);
		courseDto.setLastUpdateDate(LocalDate.of(2020, 1, 01));
		courseDto.setRating(5D);
		
		courseOrderDto = new CourseOrderDto();
		courseOrderDto.setCourse(courseDto);
		courseOrderDto.setRating(5D);
		courseOrderDto.setCreationDate(LocalDate.of(2020,05,02));
		courseOrderDto.setLastUpdateDate(null);
		courseOrderDto.setDiscount(null);
		courseOrderDto.setCourseOrderKey("30ef27b8c959123ec2971a4fcfaf36ff6c46d1090b40592fb02"
					+ "f3a8cafd4221e");
		courseOrderDto.setStudent(null);
		courseOrderDtoList.add(courseOrderDto);
		
		studentDto.setCourseOrders(courseOrderDtoList);
		studentDto.setActive(false);
		studentDto.setCity("Bangalore");
		studentDto.setCountry("India");
		studentDto.setCreationtDate(LocalDate.of(2020, 04,28));
		studentDto.setEmail("chacha@gmail.com");
		studentDto.setEncryptedPassword("x1cK$1nt");
		studentDto.setFirstName("chacha");
		studentDto.setLastName("kaka");
		studentDto.setPassword("x1cK$1nt");
		studentDto.setPhone("1234567869");
		studentDto.setState("KA");
		studentDto.setStudentId(null);
		studentDto.setStudentKey("12301f3b04f21a9a8f5d507b246c3b84f20cd8aead3b"
					+ "06214caa128cca0e65c");
		studentDto.setLastUpdateDate(LocalDate.of(2020, 05, 02));
		
		return studentDto;
		
	}
	
	
	public static StudentDto getStudentDtoPurchasingCourses() {
		
		CourseOrderDto courseOrderDto = new CourseOrderDto();
		List<CourseOrderDto> courseOrderDtoList = new ArrayList<CourseOrderDto>();
		CourseDto courseDto = new CourseDto();
		
		//1
		courseDto.setChapters(200);
		courseDto.setCourseId(101L);
		courseDto.setCourseKey("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
		courseDto.setCourseName("Microservice Zero to Hero");
		courseDto.setCreationtDate(LocalDate.of(2018, 06, 10));
		courseDto.setDescription("Take you from beggining to Expert level quickly");
		courseDto.setDomainName("Java");
		courseDto.setDurationInHours(500);
		courseDto.setPrice(420D);
		courseDto.setLastUpdateDate(LocalDate.of(2020, 1, 01));
		courseDto.setRating(5.8D);
		courseOrderDto.setCourse(courseDto);
		courseOrderDto.setRating(5.8D);
		courseOrderDto.setCreationDate(LocalDate.of(2020, 2, 28));
		courseOrderDto.setLastUpdateDate(LocalDate.of(2020, 1, 1));
		courseOrderDto.setDiscount(null);
		courseOrderDto.setCourseOrderKey("bbb9f61ed51461fde2400c6aee189d1ef5f90c832514ddc6cf490"
					+ "f9f8c9fd6e8");
		courseOrderDto.setStudent(null);
		courseOrderDtoList.add(courseOrderDto);
		
		/////2
		courseDto = new CourseDto();
		courseDto.setChapters(180);
		courseDto.setCourseId(102L);
		courseDto.setCourseKey("3e633d515cb7e087749d52f2f95567ded8b326928c7ad876e32939a26717046e");
		courseDto.setCourseName("Restful Web Service with Spring boot");
		courseDto.setCreationtDate(LocalDate.of(2016, 9, 28));
		courseDto.setDescription("Give you hands on experience on creating project with Restful "
				+ "WebService, JDK8 and Spring Boot");
		courseDto.setDomainName("Java");
		courseDto.setDurationInHours(200);
		courseDto.setPrice(420D);
		courseDto.setLastUpdateDate(LocalDate.of(2020, 1, 01));
		courseDto.setRating(5D);
		
		courseOrderDto = new CourseOrderDto();
		courseOrderDto.setCourse(courseDto);
		courseOrderDto.setRating(5D);
		courseOrderDto.setCreationDate(LocalDate.of(2020,05,02));
		courseOrderDto.setLastUpdateDate(null);
		courseOrderDto.setDiscount(null);
		courseOrderDto.setCourseOrderKey("30ef27b8c959123ec2971a4fcfaf36ff6c46d1090b40592fb02f3a"
				+ "8cafd4221e");
		courseOrderDto.setStudent(null);
		courseOrderDtoList.add(courseOrderDto);
		
		StudentDto studentDto = new StudentDto();
		studentDto.setCourseOrders(courseOrderDtoList);
		studentDto.setActive(false);
		studentDto.setCity("Bangalore");
		studentDto.setCountry("India");
		studentDto.setCreationtDate(LocalDate.of(2020, 04,28));
		studentDto.setEmail("Amjad@gmail.com");
		studentDto.setEncryptedPassword("SomeSecret2Password");
		studentDto.setFirstName("Amjad");
		studentDto.setLastName("Quazi");
		studentDto.setPassword("SomeSecret2Password");
		studentDto.setPhone("7878489761");
		studentDto.setState("MH");
		studentDto.setStudentId(null);
		studentDto.setStudentKey("12301f3b04f21a9a8f5d507b246c3b84f20cd8aead3b06214caa128cca0e65c");
		studentDto.setLastUpdateDate(LocalDate.of(2020, 05, 02));
		
		return studentDto;
	}
	
	
	public static Map<String,Map<Double,List<CourseDto>>> getCourseResponseByRatingAndDomainMap() {
		
		return getAllCourses().stream().collect(Collectors.groupingBy(CourseDto::getDomainName, 
				Collectors.groupingBy(CourseDto::getRating)));
	}
	
	public static Map<String,List<CourseDto>> getCourseResponseBydDomainMap() {
		
		return getAllCourses().stream().collect(Collectors.groupingBy(CourseDto::getDomainName));
	}
	
	public static List<CourseDto> getAllCourses() {
		
		List<CourseDto> coursesList = new ArrayList<>();
		CourseDto courseDto = new CourseDto();
		
		courseDto.setChapters(180);
		courseDto.setCourseKey("d483bf6fad787dd0210d1ebd8dc8cd17651d6739"
				+ "96f561f1173a578e4e6a9a4e");
		courseDto.setCourseName("Restful Web Service with Spring boot");
		courseDto.setCreationtDate(LocalDate.of(2016,9,28));
		courseDto.setDescription("Give you hands on experience on creating project with "
				+ "Restful WebService, JDK8 and Spring Boot");
		courseDto.setDomainName("Java");
		courseDto.setDurationInHours(200);
		courseDto.setLastUpdateDate(LocalDate.of(2019, 8, 28));
		courseDto.setPrice(420D);
		courseDto.setRating(5D);
		coursesList.add(courseDto);
		
		courseDto = new CourseDto();	  
		courseDto.setChapters(200);
		courseDto.setCourseKey("3e633d515cb7e087749d52f2f95567ded8"
				+ "b326928c7ad876e32939a26717046e");
		courseDto.setCourseName("JMS, RabiitMQ, ActiveMQ, "
				+ "Kubernate, Multi Theading");
		courseDto.setCreationtDate(LocalDate.of(2020, 2, 20));
		courseDto.setDescription("This course is good for advance java student. "
				+ "It will teach effective use JMS, Asynchronous Programming, Thead "
				+ "handling and testing using JUNIT, Mockito, PowerMock, user Sonar Qube "
				+ "and Code Coverage");
		courseDto.setDomainName("Java");
		courseDto.setDurationInHours(800);
		courseDto.setLastUpdateDate(LocalDate.of(2020, 4, 28));
		courseDto.setPrice(500D);
		courseDto.setRating(5D);
		coursesList.add(courseDto);
		
		courseDto = new CourseDto();	  
		courseDto.setChapters(500);
		courseDto.setCourseKey("67a7b6e00304c3605df81c1d578ff6f7f8ce63bc5b9acb");
		courseDto.setCourseName("Java Design Pattern and UML");
		courseDto.setCreationtDate(LocalDate.of(2020, 1, 29));
		courseDto.setDescription("It teaches you design patterns, Design pattern in "
				+ "Java programming and UML designing");
		courseDto.setDomainName("Java");
		courseDto.setDurationInHours(200);
		courseDto.setLastUpdateDate(null);
		courseDto.setPrice(420D);
		courseDto.setRating(5D);
		coursesList.add(courseDto);
		
		courseDto = new CourseDto();	  
		courseDto.setChapters(200);
		courseDto.setCourseKey("2e552bb07890a68f4563bc2beaa1a8227aae55106"
				+ "f202494d570cb5ab8ce5fb0");
		courseDto.setCourseName("Microservice Zero to Hero");
		courseDto.setCreationtDate(LocalDate.of(2018, 6, 10));
		courseDto.setDescription("Take you from beggining to Expert level quickly");
		courseDto.setDomainName("Java");
		courseDto.setDurationInHours(500);
		courseDto.setLastUpdateDate(LocalDate.of(2020, 1, 1));
		courseDto.setPrice(420D);
		courseDto.setRating(5.8D);
		coursesList.add(courseDto);
		
		courseDto = new CourseDto();	  
		courseDto.setChapters(150);
		courseDto.setCourseKey("7a84d7b03398b0978e9bcf23e3a7a102e397b1236"
				+ "141ee71d9b5c8ecda6d9ad6");
		courseDto.setCourseName("AWS Deployment");
		courseDto.setCreationtDate(LocalDate.of(2020, 1, 1));
		courseDto.setDescription("Course is build for Bignner, and Expert and make you "
				+ "confident DWAPS");
		courseDto.setDomainName("dev");
		courseDto.setDurationInHours(300);
		courseDto.setLastUpdateDate(LocalDate.of(2020, 10, 30));
		courseDto.setPrice(500D);
		courseDto.setRating(4D);
		coursesList.add(courseDto);
		
		return coursesList;
	}
	
	
	public static StudentDto getStudentByEmail() {
		
		CourseOrderDto courseOrderDto = new CourseOrderDto();
		List<CourseOrderDto> courseOrderDtoList = new ArrayList<CourseOrderDto>();
		CourseDto courseDto = new CourseDto();
		StudentDto studentByEmail = new StudentDto();
		
		//1
		courseDto.setChapters(200);
		courseDto.setCourseId(101L);
		courseDto.setCourseKey("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
		courseDto.setCourseName("Microservice Zero to Hero");
		courseDto.setCreationtDate(LocalDate.of(2018, 06, 10));
		courseDto.setDescription("Take you from beggining to Expert level quickly");
		courseDto.setDomainName("Java");
		courseDto.setDurationInHours(500);
		courseDto.setPrice(420D);
		courseDto.setLastUpdateDate(LocalDate.of(2020, 1, 01));
		courseDto.setRating(5.8D);
		courseOrderDto.setCourse(courseDto);
		courseOrderDto.setRating(5.8D);
		courseOrderDto.setCreationDate(LocalDate.of(2020, 2, 28));
		courseOrderDto.setLastUpdateDate(LocalDate.of(2020, 1, 1));
		courseOrderDto.setDiscount(null);
		courseOrderDto.setCourseOrderKey("bbb9f61ed51461fde2400c6aee189d1ef5f90c832514ddc"
				+ "6cf490f9f8c9fd6e8");
		courseOrderDto.setStudent(null);
		courseOrderDtoList.add(courseOrderDto);
		
		/////2
		courseDto = new CourseDto();
		courseDto.setChapters(180);
		courseDto.setCourseId(102L);
		courseDto.setCourseKey("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
		courseDto.setCourseName("Restful Web Service with Spring boot");
		courseDto.setCreationtDate(LocalDate.of(2016, 9, 28));
		courseDto.setDescription("Give you hands on experience on creating project with Restful "
				+ "WebService, JDK8 and Spring Boot");
		courseDto.setDomainName("Java");
		courseDto.setDurationInHours(200);
		courseDto.setPrice(420D);
		courseDto.setLastUpdateDate(LocalDate.of(2020, 1, 01));
		courseDto.setRating(5D);
		
		courseOrderDto = new CourseOrderDto();
		courseOrderDto.setCourse(courseDto);
		courseOrderDto.setRating(5D);
		courseOrderDto.setCreationDate(LocalDate.of(2020,05,02));
		courseOrderDto.setLastUpdateDate(null);
		courseOrderDto.setDiscount(null);
		courseOrderDto.setCourseOrderKey("30ef27b8c959123ec2971a4fcfaf36ff6c46d1090b40592fb02f3"
				+ "a8cafd4221e");
		courseOrderDto.setStudent(null);
		courseOrderDtoList.add(courseOrderDto);
		
		studentByEmail.setCourseOrders(courseOrderDtoList);
		studentByEmail.setActive(false);
		studentByEmail.setCity("Bangalore");
		studentByEmail.setCountry("India");
		studentByEmail.setCreationtDate(LocalDate.of(2020, 04,28));
		studentByEmail.setEmail("farhan@gmail.com");
		studentByEmail.setEncryptedPassword("x1cK$1nt");
		studentByEmail.setFirstName("Farhan");
		studentByEmail.setLastName("Quazi");
		studentByEmail.setPassword("x1cK$1nt");
		studentByEmail.setPhone("1234567869");
		studentByEmail.setState("KA");
		studentByEmail.setStudentId(null);
		studentByEmail.setStudentKey("12301f3b04f21a9a8f5d507b246c3b84f20cd8aead3b06214caa128cca0e65c");
		studentByEmail.setLastUpdateDate(LocalDate.of(2020, 05, 02));
		
		return studentByEmail;
		
	}
	
	public static StudentEntity getStudentEntityForCreation() {
		
		StudentEntity studentEntity = new StudentEntity();
		
		studentEntity.setActive(true);
		studentEntity.setCity("Bangalore");
		studentEntity.setCountry("Indai");
		studentEntity.setCreationtDate(LocalDate.now());
		studentEntity.setEmail("farhan@gmail.com");
		studentEntity.setPhone("9999999999");
		studentEntity.setState("KA");
		studentEntity.setEncryptedPassword("Itis1Secret&HideIt");
		studentEntity.setFirstName("Guddu");
		studentEntity.setLastName("Ali");
		
		Set<RoleEntity> roles = new HashSet<>();
		RoleEntity role = new RoleEntity();
		role.setName("ROLE_USER");
		role.setRoleId(2L);
		
		Set<AuthorityEntity> authorities = new HashSet<>();
		
		AuthorityEntity authorityEntity = new AuthorityEntity();
		authorityEntity.setAuthorityId(1L);
		authorityEntity.setName("READ_AUTHORITY");
		
		Set<RoleEntity> roleEntities = new HashSet<>();
		roleEntities.add(role);
		authorityEntity.setRoleEntities(roleEntities);
		authorities.add(authorityEntity);
		
		authorityEntity = new AuthorityEntity();
		authorityEntity.setAuthorityId(2L);
		authorityEntity.setName("WRITE_AUTHORITY");
		roleEntities = new HashSet<>();
		roleEntities.add(role);
		authorityEntity.setRoleEntities(roleEntities);
		authorities.add(authorityEntity);
		authorityEntity.setRoleEntities(roleEntities);
		
		authorityEntity = new AuthorityEntity();
		authorityEntity.setAuthorityId(3L);
		authorityEntity.setName("DELETE_AUTHORITY");
		roleEntities = new HashSet<>();
		roleEntities.add(role);
		authorities.add(authorityEntity);
		
		authorityEntity.setRoleEntities(roleEntities);
		
		role.setAuthorities(authorities);
		roles.add(role);
		studentEntity.setRoles(roles);
		return studentEntity;
		
	}
	
	public static StudentEntity getStudentEntityForCreation2() {
		
		StudentEntity studentEntity = new StudentEntity();
		
		studentEntity.setActive(true);
		studentEntity.setCity("Bangalore");
		studentEntity.setCountry("Indai");
		studentEntity.setCreationtDate(LocalDate.now());
		studentEntity.setEmail("farhan@gmail.com");
		studentEntity.setPhone("9999999999");
		studentEntity.setState("KA");
		studentEntity.setEncryptedPassword("Itis1Secret&HideIt");
		studentEntity.setFirstName("Guddu");
		studentEntity.setLastName("Ali");
		
		Set<RoleEntity> roles = new HashSet<>();
		RoleEntity role = new RoleEntity();
		role.setName("ROLE_USER");
		
		Set<AuthorityEntity> authorities = new HashSet<>();
		
		AuthorityEntity authorityEntity = new AuthorityEntity();
		authorityEntity.setName("READ_AUTHORITY");
		
		Set<RoleEntity> roleEntities = new HashSet<>();
		roleEntities.add(role);
		authorityEntity.setRoleEntities(roleEntities);
		authorities.add(authorityEntity);
		
		authorityEntity = new AuthorityEntity();
		authorityEntity.setName("WRITE_AUTHORITY");
		roleEntities = new HashSet<>();
		roleEntities.add(role);
		authorityEntity.setRoleEntities(roleEntities);
		authorities.add(authorityEntity);
		authorityEntity.setRoleEntities(roleEntities);
		
		authorityEntity = new AuthorityEntity();
		authorityEntity.setName("DELETE_AUTHORITY");
		roleEntities = new HashSet<>();
		roleEntities.add(role);
		authorities.add(authorityEntity);
		
		authorityEntity.setRoleEntities(roleEntities);
		
		role.setAuthorities(authorities);
		roles.add(role);
		studentEntity.setRoles(roles);
		return studentEntity;
		
	}
	
	public static StudentEntity getStudentEntityForCreation3() {
		
		StudentEntity studentEntity = new StudentEntity();
		
		studentEntity.setActive(true);
		studentEntity.setCity("Bangalore");
		studentEntity.setCountry("Indai");
		studentEntity.setCreationtDate(LocalDate.now());
		studentEntity.setEmail("farhan@gmail.com");
		studentEntity.setPhone("9999999999");
		studentEntity.setState("KA");
		studentEntity.setEncryptedPassword("Itis1Secret&HideIt");
		studentEntity.setFirstName("Guddu");
		studentEntity.setLastName("Ali");
		
		Set<RoleEntity> roles = new HashSet<>();
		RoleEntity role = new RoleEntity();
		role.setName("ROLE_USER");
		
		Set<AuthorityEntity> authorities = new HashSet<>();
		
		AuthorityEntity authorityEntity = new AuthorityEntity();
		authorityEntity.setName("READ_AUTHORITY");
		
		Set<RoleEntity> roleEntities = new HashSet<>();
		roleEntities.add(role);
		authorityEntity.setRoleEntities(roleEntities);
		authorities.add(authorityEntity);
		
		authorityEntity = new AuthorityEntity();
		authorityEntity.setName("WRITE_AUTHORITY");
		roleEntities = new HashSet<>();
		roleEntities.add(role);
		authorityEntity.setRoleEntities(roleEntities);
		authorities.add(authorityEntity);
		authorityEntity.setRoleEntities(roleEntities);
		
		authorityEntity = new AuthorityEntity();
		authorityEntity.setName("DELETE_AUTHORITY");
		roleEntities = new HashSet<>();
		roleEntities.add(role);
		authorities.add(authorityEntity);
		
		authorityEntity.setRoleEntities(roleEntities);
		
		role.setAuthorities(authorities);
		roles.add(role);
		studentEntity.setRoles(roles);
		
		CourseEntity courseEntity = new CourseEntity();
		CourseOrderEntity courseOrderEntity = new CourseOrderEntity();
		List<CourseOrderEntity> courseOrders = new ArrayList<>();
		
		courseEntity.setChapters(100);
		courseEntity.setCourseId(101L);
		courseEntity.setCourseKey("ea699a8b09c3c99a9d67a7b6e00304c3605df81c1d578ff6f7f8ce63bc5b9acb");
		courseEntity.setCourseName("Microservice Zero to Hero");
		courseEntity.setCreationtDate(LocalDate.of(2020, 5, 20));
		courseEntity.setDescription("Best course");
		courseEntity.setDomainName("Java");
		courseEntity.setDurationInHours(1000);
		courseEntity.setLastUpdateDate(LocalDate.of(2020, 5, 25));
		courseEntity.setRating(5D);
		courseEntity.setPrice(200D);
		
		courseOrderEntity.setCourseOrderId(101L);
		courseOrderEntity.setCourseOrderKey("bbb9f61ed51461fde2400c6aee189d1ef5f90c832514ddc6cf490f9f8c9fd6e8");
		courseOrderEntity.setCreationDate(LocalDate.now());
		courseOrderEntity.setLastUpdateDate(LocalDate.now());
		courseOrderEntity.setRating(5D);
		courseOrderEntity.setStudent(studentEntity);
		courseOrderEntity.setCourse(courseEntity);
		courseOrderEntity.setStudent(studentEntity);
		courseOrders.add(courseOrderEntity);
		studentEntity.setCourseOrders(courseOrders);
		
		return studentEntity;
		
	}
	
	public static StudentEntity getStudentEntityForUpdate() {
		
		StudentEntity studentEntity = new StudentEntity();
		
		studentEntity.setActive(true);
		studentEntity.setCity("Bangalore");
		studentEntity.setCountry("Indai");
		studentEntity.setLastUpdateDate(LocalDate.now());
		studentEntity.setEmail("chacha@gmail.com");
		studentEntity.setPhone("9876543211");
		studentEntity.setState("KA");
		studentEntity.setEncryptedPassword("x1cK$1nt");
		studentEntity.setFirstName("chacha");
		studentEntity.setLastName("kaka");
		studentEntity.setStudentId(101L);
		studentEntity.setStudentKey(CustomUtils.getSHA256());
		return studentEntity;
		
	}


	public static String getStudentEmailId() {
		return "chacha@gmail.com";
	}


	public static UserPrincipal getUserPrincipal() {
		
		StudentEntity studentEntity = getStudentEntityForUpdate();
		UserPrincipal userPrincipal = new UserPrincipal(studentEntity);
		return userPrincipal;
	}
	
	
	public static List<CourseEntity> getCourseEntities() {
		
		List<CourseEntity> courseEntityList = new ArrayList<>();
		
		CourseEntity courseEntity =  new CourseEntity();
		courseEntity.setCourseId(101L);
		courseEntity.setChapters(300);
		courseEntity.setCourseKey("ea699a8b09c3c99a9d67a7b6e00304c3605df81c1d578ff6f7f8ce63bc5b9acb");
		courseEntity.setCourseName("J2EE programming");
		courseEntity.setCreationtDate(LocalDate.of(2018, 10,12));
		courseEntity.setDescription("Covers all Java essential and additonal topics and very comprahensive book");
		courseEntity.setDomainName("Java");
		courseEntity.setDurationInHours(1000);
		courseEntity.setLastUpdateDate(LocalDate.of(2018, 12, 30));
		courseEntity.setPrice(700D);
		courseEntity.setRating(5D);
		courseEntityList.add(courseEntity);
		
		courseEntity =  new CourseEntity();
		courseEntity.setCourseId(102L);
		courseEntity.setChapters(200);
		courseEntity.setCourseKey("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
		courseEntity.setCourseName("Linux");
		courseEntity.setCreationtDate(LocalDate.of(2019, 4,10));
		courseEntity.setDescription("Teach you basic commands then writing scripts and other stuff");
		courseEntity.setDomainName("OS");
		courseEntity.setDurationInHours(500);
		courseEntity.setLastUpdateDate(LocalDate.of(2020, 4, 6));
		courseEntity.setPrice(430D);
		courseEntity.setRating(5D);
		courseEntityList.add(courseEntity);
		
		courseEntity =  new CourseEntity();
		courseEntity.setCourseId(103L);
		courseEntity.setChapters(150);
		courseEntity.setCourseKey("7a84d7b03398b0978e9bcf23e3a7a102e397b1236141ee71d9b5c8ecda6d9ad6");
		courseEntity.setCourseName("Angular 9");
		courseEntity.setCreationtDate(LocalDate.of(2019, 4,10));
		courseEntity.setDescription("After going through this book, You will be able to work as professional");
		courseEntity.setDomainName("UI");
		courseEntity.setDurationInHours(350);
		courseEntity.setLastUpdateDate(LocalDate.of(2019, 12, 26));
		courseEntity.setPrice(420D);
		courseEntity.setRating(5D);
		courseEntityList.add(courseEntity);
		
		
		return courseEntityList;
	}

}
