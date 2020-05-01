package com.learn.online.dtos;

import java.time.LocalDate;

public class CourseOrderDto {

	private Long courseOrderId;
	private String CourseOrderKey;
	private StudentDto student;
	private CourseDto course;
	private Double rating;
	private LocalDate creationDate;
	private LocalDate lastUpdateDate;
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

	public StudentDto getStudent() {
		return student;
	}

	public void setStudent(StudentDto student) {
		this.student = student;
	}

	public CourseDto getCourse() {
		return course;
	}

	public void setCourse(CourseDto course) {
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
		CourseOrderDto other = (CourseOrderDto) obj;
		if (courseOrderId == null) {
			if (other.courseOrderId != null)
				return false;
		} else if (!courseOrderId.equals(other.courseOrderId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CourseOrderDto [courseOrderId=" + courseOrderId + ", CourseOrderKey=" + CourseOrderKey + ", student="
				+ student + ", course=" + course + ", rating=" + rating + ", creationDate=" + creationDate
				+ ", lastUpdateDate=" + lastUpdateDate + ", discount=" + discount + "]";
	}

}
