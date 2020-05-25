package com.learn.online.responses;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learn.online.dtos.CourseOrderDto;
import com.learn.online.dtos.StudentDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDetailResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String studentKey;
	private String firstName;
	private String lastName;
	private String email;
	private String encryptedPassword;
	private String phone;
	private String country;
	private String state;
	private List<CourseOrderDto> courseOrders;
	private boolean active;
	private LocalDate creationtDate;
	private LocalDate lastUpdateDate;

	public StudentDetailResponse() {
		super();
	}

	public StudentDetailResponse(StudentDto studentDto) {
		setStudentKey(studentDto.getStudentKey());
		setFirstName(studentDto.getFirstName());
		setLastName(studentDto.getLastName());
		setEncryptedPassword("**********");
		setEmail(studentDto.getEmail());
		setPhone(studentDto.getPhone());
		setCountry(studentDto.getCountry());
		setState(studentDto.getState());
		
		setCourseOrders(studentDto.getCourseOrders().stream().map(courseOrderDto->{
			CourseOrderDto tempCourseOrderDto = new CourseOrderDto();
			
			tempCourseOrderDto.setCourseOrderKey(courseOrderDto.getCourseOrderKey());
			tempCourseOrderDto.setCreationDate(courseOrderDto.getCreationDate());
			tempCourseOrderDto.setDiscount(courseOrderDto.getDiscount());
			tempCourseOrderDto.setLastUpdateDate(courseOrderDto.getLastUpdateDate());
			tempCourseOrderDto.setRating(courseOrderDto.getRating());

			courseOrderDto.getCourse().setCourseId(0L); 
			tempCourseOrderDto.setCourse(courseOrderDto.getCourse());
			
			/*	
			courseOrderDto.getStudent().setStudentId(0L);
			tempCourseOrderDto.setStudent(courseOrderDto.getStudent());
			*/
			
			return tempCourseOrderDto;
		}).collect(Collectors.toList()));
		
		setActive(studentDto.isActive());
		setCreationtDate(studentDto.getCreationtDate());
		setLastUpdateDate(studentDto.getLastUpdateDate());
		hideSensitveInfo();
	}

	private void hideSensitveInfo() {
		
		courseOrders.forEach(courseOrder->{
			courseOrder.setCourseOrderId(0L);
			
			if(courseOrder.getCourse() != null) {
				courseOrder.getCourse().setCourseId(0L);
			}
			
		});
	}
	
	public String getStudentKey() {
		return studentKey;
	}

	public void setStudentKey(String studentKey) {
		this.studentKey = studentKey;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<CourseOrderDto> getCourseOrders() {
		return courseOrders;
	}

	public void setCourseOrders(List<CourseOrderDto> courseOrders) {
		this.courseOrders = courseOrders;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDate getCreationtDate() {
		return creationtDate;
	}

	public void setCreationtDate(LocalDate creationtDate) {
		this.creationtDate = creationtDate;
	}

	public LocalDate getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(LocalDate lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Override
	public String toString() {
		return "StudentDetailResponse [studentKey=" + studentKey + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", encryptedPassword=" + encryptedPassword + ", phone=" + phone + ", country="
				+ country + ", state=" + state + ", courseOrders=" + courseOrders + ", active=" + active
				+ ", creationtDate=" + creationtDate + ", lastUpdateDate=" + lastUpdateDate + "]";
	}

}
