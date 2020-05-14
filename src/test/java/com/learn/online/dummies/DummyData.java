package com.learn.online.dummies;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.learn.online.beans.StudentEntity;
import com.learn.online.dtos.CourseDto;
import com.learn.online.dtos.CourseOrderDto;
import com.learn.online.dtos.StudentDto;
import com.learn.online.utils.CustomUtils;

public interface DummyData {
	
	public final static String STUDENT_JSON_INPUT = "{\"firstName\":\"Amjad\","
			+ "\"lastName\":\"Quazi\",\"email\":\"Amjad@gmail.com\","
			+ "\"reemail\":\"Amjad@gmail.com\",\"password\":\"SomeSecret2Password#\","
			+ "\"repassword\":\"SomeSecret2Password#\",\"active\":\"true\",\"phone\":\"7878489761\","
			+ "\"city\":\"Patur\",\"state\":\"MH\",\"country\":\"India\","
			+ "\"studentKey\":\"12301f3b04f21a9a8f5d507b246c3b84f20cd8aead3b06214caa128cca0e65c\"}";
	
	public final static String STUDENT_JSON_CREATE_OUTPUT = 
			"{\"responseDetail\":{\"studentKey\":\"12301f3b04f21a9a8f5d507b246c3b84f20cd8ae"
			+ "ad3b06214caa128cca0e65c\"},\"message\":\"Student added/signup is successfully\","
			+ "\"status\":\"SUCCESS\"}";
	
	public final static String STUDENT_JSON_UPDATE_OUTPUT = 
			"{\"responseDetail\":{\"studentKey\":\"12301f3b04f21a9a8f5d507b246c3b84f20cd8aead3b"
			+ "06214caa128cca0e65c\"},\"message\":\"Student update is successfull\","
			+ "\"status\":\"SUCCESS\"}";
	
	public final static String COURSES_TO_BUY_JSON_INPUT =
			"{\"studentEmail\": \"farhan@gmail.com\",\"courseKeys\": [\"2e552bb07890a68f4563bc"
			+ "2beaa1a8227aae55106f202494d570cb5ab8ce5fb0\","
			+ "\"3e633d515cb7e087749d52f2f95567ded8b326928c7ad876e32939a26717046e\"]}";
	
	public final static String PURCHASED_COURSES_EXPECTED_RESPONSE = 
			"{\"responseDetail\":{\"studentKey\":\"12301f3b04f21a9a8f5d507b246c3b84f20cd"
			+ "8aead3b06214caa128cca0e65c\"},\"message\":\"Your courses purchased order have been "
			+ "placed successfully\",\"status\":\"SUCCESS\"}";
	
	public final static String COURSES_TO_CANCEL_JSON_INPUT = 
			"{\"studentEmail\": \"farhan@gmail.com\",\"courseKeys\": [\"2e552bb07890a68f4563bc2bea"
			+ "a1a8227aae55106f202494d570cb5ab8ce5fb0\","
			+ "\"3e633d515cb7e087749d52f2f95567ded8b326928c7ad876e32939a26717046e\"]}";
	
	public final static String CANCELLED_COURSES_EXPECTED_RESPONSE = 
			"{\"responseDetail\":{\"studentKey\":\"12301f3b04f21a9a8f5d507b246c3b84f20cd8aea"
			+ "d3b06214caa128cca0e65c\"},\"message\":\"Courses canncellation request proccessed "
			+ "sucessfully\",\"status\":\"SUCCESS\"}";
	
	
	
	public static final String COURSES_BY_DOMAIN_AND_RATING_EXPECTED_VALUE = 
			"{\"responseDetail\":{\"Java\":{\"5.0\":"
	 		+ "[{\"courseId\":null,\"courseKey\":\"d483bf6fad787dd0210d1ebd8dc8cd17651d673996f"
	 		+ "561f1173a578e4e6a9a4e\",\"domainName\":\"Java\",\"courseName\":\"Restful Web "
	 		+ "Service with Spring boot\",\"description\":\"Give you hands on experience on "
	 		+ "creating project with Restful WebService, "
	 		+ "JDK8 and Spring Boot\",\"chapters\":180,\"durationInHours\":200,\"price\":420,"
	 		+ "\"rating\":5,\"creationtDate\":\"2016-09-28\",\"lastUpdateDate\":\"2019-08-28\"},"
	 		+ "{\"courseId\":null,\"courseKey\":\"3e633d515cb7e087749d52f2f95567ded8b3269"
	 		+ "28c7ad876e32939a26717046e\",\"domainName\":\"Java\",\"courseName\":\"JMS,"
	 		+ " RabiitMQ, ActiveMQ, Kubernate, Multi Theading\",\"description\":\"This course "
	 		+ "is good for advance java student. It will teach effective use JMS, Asynchronous "
	 		+ "Programming, Thead handling and testing using JUNIT, Mockito, PowerMock, user "
	 		+ "Sonar Qube and Code Coverage\",\"chapters\":200,\"durationInHours\":800,"
	 		+ "\"price\":500,\"rating\":5,\"creationtDate\":\"2020-02-20\","
	 		+ "\"lastUpdateDate\":\"2020-04-28\"},{\"courseId\":null,\"courseKey\":\"67a7b6e00"
	 		+ "304c3605df81c1d578ff6f7f8ce63bc5b9acb\",\"domainName\":\"Java\","
	 		+ "\"courseName\":\"Java Design Pattern and UML\",\"description\":\"It teaches you "
	 		+ "design patterns, Design pattern in Java programming and UML designing\","
	 		+ "\"chapters\":500,\"durationInHours\":200,\"price\":420,\"rating\":5,"
	 		+ "\"creationtDate\":\"2020-01-29\",\"lastUpdateDate\":null}],"
	 		+ "\"5.8\":[{\"courseId\":null,\"courseKey\":\"2e552bb07890a68f4563bc2beaa1a8227a"
	 		+ "ae55106f202494d570cb5ab8ce5fb0\",\"domainName\":\"Java\",\"courseName\":"
	 		+ "\"Microservice Zero to Hero\",\"description\":\"Take you from beggining to "
	 		+ "Expert level quickly\",\"chapters\":200,\"durationInHours\":500,\"price\":420,"
	 		+ "\"rating\":5.8,\"creationtDate\":\"2018-06-10\",\"lastUpdateDate\""
	 		+ ":\"2020-01-01\"}]},\"dev\":{\"4.0\":[{\"courseId\":null,\"courseKey\""
	 		+ ":\"7a84d7b03398b0978e9bcf23e3a7a102e397b1236141ee71d9b5c8ecda6d9ad6\","
	 		+ "\"domainName\":\"dev\",\"courseName\":\"AWS Deployment\",\"description\""
	 		+ ":\"Course is build for Bignner, and Expert and make you confident DWAPS\","
	 		+ "\"chapters\":150,\"durationInHours\":300,\"price\":500,\"rating\":4,"
	 		+ "\"creationtDate\":\"2020-01-01\",\"lastUpdateDate\":\"2020-10-30\"}]}},"
	 		+ "\"message\":\"Courses search by domain and rating is successful\","
	 		+ "\"status\":\"SUCCESS\"}";
		 
	
	public final static String COURSES_BY_DOMAIN_EXPECTED_VALUE = "{\"responseDetail\":{\"Java\""
			+ ":[{\"courseId\":null,\"courseKey\":\"2e552bb07890a68f4563bc2beaa1a8227aae55106f20"
			+ "2494d570cb5ab8ce5fb0\",\"domainName\":\"Java\",\"courseName\":\"Microservice Zero to "
			+ "Hero\",\"description\":\"Take you from beggining to Expert level quickly\","
			+ "\"chapters\":200,\"durationInHours\":500,\"price\":420,\"rating\":5.8,"
			+ "\"creationtDate\":\"2018-06-10\",\"lastUpdateDate\":\"2020-01-01\"},{\"courseId"
			+ "\":null,\"courseKey\":\"d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173"
			+ "a578e4e6a9a4e\",\"domainName\":\"Java\",\"courseName\":\"Restful Web Service with "
			+ "Spring boot\",\"description\":\"Give you hands on experience on creating project "
			+ "with Restful WebService, JDK8 and Spring Boot\",\"chapters\":180,"
			+ "\"durationInHours\":200,\"price\":420,\"rating\":5,\"creationtDate\":\"2016-09-28\","
			+ "\"lastUpdateDate\":\"2019-08-28\"},{\"courseId\":null,\"courseKey\":\"3e633d515cb"
			+ "7e087749d52f2f95567ded8b326928c7ad876e32939a26717046e\",\"domainName\":\"Java\","
			+ "\"courseName\":\"JMS, RabiitMQ, ActiveMQ, Kubernate, Multi Theading\","
			+ "\"description\":\"This course is good for advance java student. It will teach "
			+ "effective use JMS, Asynchronous Programming, Thead handling and testing using "
			+ "JUNIT, Mockito, PowerMock, user Sonar Qube and Code Coverage\",\"chapters\":200,"
			+ "\"durationInHours\":800,\"price\":500,\"rating\":5,\"creationtDate\":\"2020-02-20\","
			+ "\"lastUpdateDate\":\"2020-04-28\"},{\"courseId\":null,\"courseKey\":\"67a7b6e"
			+ "00304c3605df81c1d578ff6f7f8ce63bc5b9acb\",\"domainName\":\"Java\","
			+ "\"courseName\":\"Java Design Pattern and UML\",\"description\":"
			+ "\"It teaches you design patterns, Design pattern in Java programming and UML "
			+ "designing\",\"chapters\":500,\"durationInHours\":200,\"price\":420,\"rating\":5,"
			+ "\"creationtDate\":\"2020-01-29\",\"lastUpdateDate\":null}],\"dev\""
			+ ":[{\"courseId\":null,\"courseKey\":\"7a84d7b03398b0978e9bcf23e3a7a102e397b"
			+ "1236141ee71d9b5c8ecda6d9ad6\",\"domainName\":\"dev\",\"courseName\""
			+ ":\"AWS Deployment\",\"description\":\"Course is build for Bignner, and Expert and "
			+ "make you confident DWAPS\",\"chapters\":150,\"durationInHours\":300,\"price\":500,"
			+ "\"rating\":4,\"creationtDate\":\"2020-01-01\",\"lastUpdateDate\":\"2020-10-30\"}]},"
			+ "\"message\":\"Courses search by domain and rating is successful\","
			+ "\"status\":\"SUCCESS\"}";
	
	public final static String STUDENT_BY_EMAIL = "{\"responseDetail\":"
			+ "{\"studentKey\":\"12301f3b04f21a9a8f5d507b246c3b84f20cd8aead3b06214caa128cca0e65c\","
			+ "\"firstName\":\"Farhan\",\"lastName\":\"Quazi\",\"email\":\"farhan@gmail.com\","
			+ "\"encryptedPassword\":\"x1cK$1nt\",\"phone\":\"1234567869\",\"country\":\"India\","
			+ "\"state\":\"KA\",\"courseOrders\":[{\"courseOrderId\":0,\"student\":null,\"course\":"
			+ "{\"courseId\":0,\"courseKey\":\"2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5"
			+ "ab8ce5fb0\",\"domainName\":\"Java\",\"courseName\":\"Microservice Zero to Hero\","
			+ "\"description\":\"Take you from beggining to Expert level quickly\",\"chapters\":200,"
			+ "\"durationInHours\":500,\"price\":420.0,\"rating\":5.8,\"creationtDate\":\"2018-06-10\","
			+ "\"lastUpdateDate\":\"2020-01-01\"},\"rating\":5.8,\"creationDate\":\"2020-02-28\","
			+ "\"lastUpdateDate\":\"2020-01-01\",\"discount\":null,\"courseOrderKey\":\"bbb9f61ed51"
			+ "461fde2400c6aee189d1ef5f90c832514ddc6cf490f9f8c9fd6e8\"},{\"courseOrderId\":0,"
			+ "\"student\":null,\"course\":{\"courseId\":0,\"courseKey\":\"d483bf6fad787dd0210d1ebd8d"
			+ "c8cd17651d673996f561f1173a578e4e6a9a4e\",\"domainName\":\"Java\",\"courseName\":"
			+ "\"Restful Web Service with Spring boot\",\"description\":\"Give you hands on "
			+ "experience on creating project with Restful WebService, JDK8 and Spring Boot\","
			+ "\"chapters\":180,\"durationInHours\":200,\"price\":420.0,\"rating\":5.0,"
			+ "\"creationtDate\":\"2016-09-28\",\"lastUpdateDate\":\"2020-01-01\"},\"rating\":5.0,"
			+ "\"creationDate\":\"2020-05-02\",\"lastUpdateDate\":null,\"discount\":null,"
			+ "\"courseOrderKey\":\"30ef27b8c959123ec2971a4fcfaf36ff6c4"
			+ "6d1090b40592fb02f3a8cafd4221e\"}],"
			+ "\"active\":false,\"creationtDate\":\"2020-04-28\",\"lastUpdateDate\":\"2020-05-02\"},"
			+ "\"message\":\"Data Found\",\"status\":\"SUCCESS\"}";
	
	
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
		studentDto.setEmail("farhan@gmail.com");
		studentDto.setEncryptedPassword("x1cK$1nt");
		studentDto.setFirstName("Farhan");
		studentDto.setLastName("Quazi");
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
		return studentEntity;
		
	}
	
	public static StudentEntity getStudentEntityForUpdate() {
		
		StudentEntity studentEntity = new StudentEntity();
		
		studentEntity.setActive(true);
		studentEntity.setCity("Bangalore");
		studentEntity.setCountry("Indai");
		studentEntity.setLastUpdateDate(LocalDate.now());
		studentEntity.setEmail("farhan@gmail.com");
		studentEntity.setPhone("9999999999");
		studentEntity.setState("KA");
		studentEntity.setEncryptedPassword("Itis1Secret&HideIt");
		studentEntity.setFirstName("Guddu");
		studentEntity.setLastName("Ali");
		studentEntity.setStudentId(101L);
		studentEntity.setStudentKey(CustomUtils.getSHA256());
		return studentEntity;
		
	}

}
