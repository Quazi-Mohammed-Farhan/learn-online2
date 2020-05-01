package com.learn.online.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "course_orders")
public class CourseOrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_order_id")
	private Long courseOrderId;

	@Column(name = "course_order_key")
	private String CourseOrderKey;

	@ManyToOne
	@JoinColumn(name = "f_student_id")
	private StudentEntity student;

	@ManyToOne
	@JoinColumn(name = "f_course_id")
	private CourseEntity course;

	@Column(name = "rating")
	private Double rating;

	@Column(name = "creation_date")
	private LocalDate creationDate;

	@Column(name = "last_update_date")
	private LocalDate lastUpdateDate;

	@Column(name = "discount")
	private Double discount;

	
	public Long getCourseOrderId() {
		return courseOrderId;
	}

	
	public void setCourseOrderId(Long courseOrderId) {
		this.courseOrderId = courseOrderId;
	}

	
	public String getCourseOrderKey() {
		return CourseOrderKey;
	}

	
	public void setCourseOrderKey(String courseOrderKey) {
		CourseOrderKey = courseOrderKey;
	}


	public StudentEntity getStudent() {
		return student;
	}

	
	public void setStudent(StudentEntity student) {
		this.student = student;
	}


	public CourseEntity getCourse() {
		return course;
	}

	
	public void setCourse(CourseEntity course) {
		this.course = course;
	}

	
	public Double getRating() {
		return rating;
	}

	
	public void setRating(Double rating) {
		this.rating = rating;
	}

	
	public LocalDate getCreationDate() {
		return creationDate;
	}

	
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}


	public LocalDate getLastUpdateDate() {
		return lastUpdateDate;
	}

	
	public void setLastUpdateDate(LocalDate lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	
	public Double getDiscount() {
		return discount;
	}

	
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseOrderId == null) ? 0 : courseOrderId.hashCode());
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
		CourseOrderEntity other = (CourseOrderEntity) obj;
		if (courseOrderId == null) {
			if (other.courseOrderId != null)
				return false;
		} else if (!courseOrderId.equals(other.courseOrderId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "CourseOrderEntity [courseOrderId=" + courseOrderId + ", CourseOrderKey=" + CourseOrderKey + ", student="
				+ student + ", course=" + course + ", rating=" + rating + ", creationDate=" + creationDate
				+ ", lastUpdateDate=" + lastUpdateDate + ", discount=" + discount + "]";
	}
	
	

}