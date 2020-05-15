TheymeLeaf with Restful web
https://javabycode.com/sf/spring-boot-tutorial/spring-boot-thymeleaf-ajax-example.html
https://mkyong.com/spring-boot/spring-boot-ajax-example/

Login page tutorial
https://www.youtube.com/watch?v=3vauM7axnRs


HOW TO TEST JPA REPO
https://www.javaguides.net/2018/09/spring-data-jpa-repository-testing-using-spring-boot-datajpatest.html

ONLINE DIAGRAMS
https://www.youtube.com/watch?v=YgW0Oiwp9U4
https://online.visual-paradigm.com/
https://www.smartdraw.com/sequence-diagram/examples/
https://app.genmymodel.com/

https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-two-crud/

How to genrate shah 256 value
https://www.geeksforgeeks.org/sha-256-hash-in-java/

HOW TO TEST SPRING MVC CONTROLLER IN JUNIT AND MOCKITO
https://reflectoring.io/spring-boot-web-controller-test/
https://reflectoring.io/bean-validation-with-spring-boot/

LOg4j2 logging
https://www.boraji.com/log4j-2-rollingfileappender-example

Logging
https://springframework.guru/using-logback-spring-boot/
https://examples.javacodegeeks.com/enterprise-java/logback/logback-rollingfileappender-example/
https://www.tutorialcup.com/spring-boot/spring-boot-logging-configurations.htm
https://howtodoinjava.com/spring-boot2/logging/spring-boot-logging-configurations/
https://www.javadevjournal.com/spring-boot/spring-boot-logging/


Got Error Helps
https://stackoverflow.com/questions/44647630/validation-failed-for-query-for-method-jpql

HANDLING VALIDATION
https://examples.javacodegeeks.com/spring-boot-bean-validation-example/
Annotation based validation using javax.validation and hibernate validation part of JSR 330
https://mkyong.com/spring-boot/spring-rest-validation-example/
https://examples.javacodegeeks.com/enterprise-java/java-ee-bean-validation-example/

Providing validation messages from properties files
https://www.baeldung.com/spring-custom-validation-message-source

How to compare two fields in validation 
https://memorynotfound.com/field-matching-bean-validation-annotation-example/
https://stackoverflow.com/questions/7239897/spring-3-annotation-based-validation-password-and-confirm-password

ADDTIONAL VALIDATION Using regex FOR @Email annotation
https://stackoverflow.com/questions/9978199/annotation-regex 

YOU CAN USE VALIDATION TOKEN IN COLLECTION AND MAP DECLEARATION
https://www.baeldung.com/javax-validation


/*****
Using following resource I created password validation functionality
https://memorynotfound.com/custom-password-constraint-validator-annotation/
************/


Group By Sing field and multiple fields example Using Stream
   //Shows you single and multiple fields 
   https://www.technicalkeeda.com/java-8-tutorials/java-8-stream-grouping
   //Shows you by single field			
   https://mkyong.com/java8/java-8-collectors-groupingby-and-mapping-example/

   
 LOGGING RND
<<<<<<< HEAD
 https://stackify.com/logging-logback/  
 
 
 JUNIT TESTING
 https://blog.codeleak.pl/2019/09/spring-boot-testing-with-junit-5.html
 
 
 JSON FORMATTING AND ESCAPING AND USCAPING
 https://codebeautify.org/json-escape-unescape
 https://jsoneditoronline.org/#left=local.loruno&right=local.ranezi
 
 
 ###TESTING SERVICE LAYER 
 https://www.youtube.com/watch?v=kuF1PkU3-c8
 
 
 
 *************************************
 
 StudentServiceKURDImplTest
   @Test 
	  public void purchaseCoursesTest() {
	  
		  //Dummy student entity going to buy courses
		  StudentEntity studentEntity = DummyData.getStudentEntityForUpdate();
		  
		  //Converting dummy student entity to DTO for comparison with
		  //retrieved studentDto after saving this student entity
		  StudentDto studentDto = CustomUtils.convertToStudentDto(studentEntity);

		  //Input Arraylist of courses id to buy
		  List<String> keysList = new ArrayList<>();
		  keysList.add("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
		  keysList.add("d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
		  
		  /*
		  CourseEntity courseEntity = new CourseEntity();
		  courseEntity.setChapters(400);
		  courseEntity.setCourseId(111L);
		  courseEntity.setCourseKey("2e552bb07890a68f4563bc2beaa1a8227aae55106f202494d570cb5ab8ce5fb0");
		  courseEntity.setCourseName("Master Java Programming");
		  courseEntity.setCreationtDate(LocalDate.of(2018, 9, 7));
		  courseEntity.setDescription("This book teach you real time skills");
		  courseEntity.setDomainName("Java"); courseEntity.setDurationInHours(500);
		  courseEntity.setLastUpdateDate(LocalDate.of(2020, 1, 1));
		  courseEntity.setPrice(500D); 
		  courseEntity.setRating(5D);
		  
		  List<CourseOrderEntity> courseOrderEntityList = new ArrayList<>();
		  CourseOrderEntity courseOrderEntity = new CourseOrderEntity();
		  courseOrderEntity.setCourse(courseEntity);
		  courseOrderEntity.setCourseOrderKey(
		  "bbb9f61ed51461fde2400c6aee189d1ef5f90c832514ddc6cf490f9" + "f8c9fd6e8");
		  
		  courseOrderEntity.setCreationDate(LocalDate.now());
		  //courseOrderEntity.setRating(5D); 
		  courseOrderEntity.setStudent(studentEntity);
		  //courseOrderEntityList.add(courseOrderEntity);
		  
		  courseEntity = new CourseEntity(); courseEntity.setChapters(200);
		  courseEntity.setCourseId(112L); courseEntity.setCourseKey(
		  "d483bf6fad787dd0210d1ebd8dc8cd17651d673996f561f1173a578e4e6a9a4e");
		  courseEntity.setCourseName("Unix In Action");
		  courseEntity.setCreationtDate(LocalDate.of(2019, 1, 10));
		  courseEntity.setDescription("Unix Cook book of experts");
		  courseEntity.setDomainName("OS"); courseEntity.setDurationInHours(340);
		  courseEntity.setLastUpdateDate(LocalDate.of(2019, 11, 10));
		  courseEntity.setPrice(400D); courseEntity.setRating(5D);
		  //courseEntityList.add(courseEntity);
		  
		  courseOrderEntity = new CourseOrderEntity();
		  courseOrderEntity.setCourse(courseEntity);
		  courseOrderEntity.setCourseOrderKey(
		  "30ef27b8c959123ec2971a4fcfaf36ff6c46d1090b40592fb02" + "f3a8cafd4221e");
		  
		  courseOrderEntity.setCreationDate(LocalDate.now());
		  courseOrderEntity.setRating(5D); 
		  //courseOrderEntity.setStudent(studentEntity);
		  //courseOrderEntityList.add(courseOrderEntity);
		  
		  */
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


https://stackify.com/logging-logback/  

