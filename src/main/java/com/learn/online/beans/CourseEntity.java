package com.learn.online.beans;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***********************************************************************************************************
 * <h1>CourseEntity!</h1>																					 
 *This class is mapped to courses table in DB. It fetches available courses in db.
 *information of student
 *                                                                                                                 
 * @author  Quazi Mohammed Farhan Ali.                                                                             
 * @version 1.0           
 * @Purpose PIP Assignment to employee by Cognizant                                                                                           
 * @since   2020-05-29                                                                                                                                                                                                                  
 ************************************************************************************************************/


@Entity
@Table(name = "courses")
public class CourseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private Long courseId;

	@Column(name = "course_key")
	private String courseKey;

	@Column(name = "domain")
	private String domainName;

	@Column(name = "course_name")
	private String courseName;

	@Column(name = "description")
	private String description;

	@Column(name = "chapters")
	private Integer chapters;

	@Column(name = "duration_in_hrs")
	private Integer durationInHours;

	@Column(name = "price")
	private Double price;

	@Column(name = "rating")
	private Double rating;

	@Column(name = "creation_date")
	private LocalDate creationtDate;

	@Column(name = "last_update_date")
	private LocalDate lastUpdateDate;

	public Long getCourseId() {
		return courseId;
	}


	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}


	public String getCourseKey() {
		return courseKey;
	}


	public void setCourseKey(String courseKey) {
		this.courseKey = courseKey;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getChapters() {
		return chapters;
	}


	public void setChapters(Integer chapters) {
		this.chapters = chapters;
	}

	
	public Integer getDurationInHours() {
		return durationInHours;
	}

	
	public void setDurationInHours(Integer durationInHours) {
		this.durationInHours = durationInHours;
	}

	
	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getRating() {
		return rating;
	}


	public void setRating(Double rating) {
		this.rating = rating;
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
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
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
		CourseEntity other = (CourseEntity) obj;
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CourseEntity [courseId=" + courseId + ", courseKey=" + courseKey + ", domainName=" + domainName
				+ ", courseName=" + courseName + ", description=" + description + ", chapters=" + chapters
				+ ", durationInHours=" + durationInHours + ", price=" + price + ", rating=" + rating
				+ ", creationtDate=" + creationtDate + ", lastUpdateDate=" + lastUpdateDate + "]";
	}

}
