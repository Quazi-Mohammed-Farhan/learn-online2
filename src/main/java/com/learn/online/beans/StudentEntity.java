package com.learn.online.beans;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "students")
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long studentId;

	@Column(name = "student_key")
	private String studentKey;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "encrypted_password")
	private String encryptedPassword;

	@Column(name = "phone")
	private String phone;

	@Column(name = "country")
	private String country;

	@Column(name = "state")
	private String state;
	
	@Column(name = "city")
	private String city;
	

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER, orphanRemoval = true)
	private List<CourseOrderEntity> courseOrders;

	@Column(name = "active")
	private boolean active;

	@Column(name = "creation_date")
	private LocalDate creationtDate;

	@Column(name = "last_update_date")
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

	public List<CourseOrderEntity> getCourseOrders() {
		return courseOrders;
	}

	public void setCourseOrders(List<CourseOrderEntity> courseOrders) {
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

	public StudentEntity addCourseOrder(CourseOrderEntity courseOrdersEntity) {
		courseOrders.add(courseOrdersEntity);
		courseOrdersEntity.setStudent(this);
		return this;
	}
	
	public StudentEntity addCourseOrders(List<CourseOrderEntity> courseOrdersEntity) {
		
		for(CourseOrderEntity courseOrderEntity : courseOrdersEntity) {
			courseOrderEntity.setStudent(this);
		}	
		courseOrders.addAll(courseOrdersEntity);
		return this;
	}
	
	public StudentEntity removeCourseOrder(CourseOrderEntity courseOrdersEntity) {
		courseOrders.remove(courseOrdersEntity);
		courseOrdersEntity.setStudent(null);
		return this;
	}
	
	public StudentEntity removeCourseOrders(List<CourseOrderEntity> courseOrdersEntityList) {
		courseOrdersEntityList.forEach(courseOrdersEntity -> {courseOrdersEntity.setStudent(null);});
		courseOrders.removeAll(courseOrdersEntityList);
		return this;
	}
	
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		StudentEntity other = (StudentEntity) obj;
		if (studentId == null) {
			if (other.studentId != null)
				return false;
		} else if (!studentId.equals(other.studentId))
			return false;
		return true;
	}

}