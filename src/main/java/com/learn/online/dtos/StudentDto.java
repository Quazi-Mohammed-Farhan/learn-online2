package com.learn.online.dtos;

import java.time.LocalDate;
import java.util.List;

public class StudentDto {

	private Long studentId;
	private String studentKey;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String encryptedPassword;
	private String phone;
	private String city;
	private String country;
	private String state;
	private List<CourseOrderDto> courseOrders;
	private boolean active;
	private LocalDate creationtDate;
	private LocalDate lastUpdateDate;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDto other = (StudentDto) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (studentId == null) {
			if (other.studentId != null)
				return false;
		} else if (!studentId.equals(other.studentId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentDto [studentId=" + studentId + ", studentKey=" + studentKey + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", encryptedPassword=" + encryptedPassword
				+ ", phone=" + phone + ", city=" + city + ", country=" + country + ", state=" + state
				+ ", courseOrders=" + courseOrders + ", active=" + active + ", creationtDate=" + creationtDate
				+ ", lastUpdateDate=" + lastUpdateDate + "]";
	}

}
