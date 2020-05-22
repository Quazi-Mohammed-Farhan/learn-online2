package com.learn.online.responses;

import java.io.Serializable;
import java.time.LocalDate;

public class CourseOrderResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String CourseOrderKey;
	private String courseName;
	private LocalDate creationDate;
	private LocalDate lastUpdateDate;
	private Double price;

	/**
	 * @return the courseOrderKey
	 */
	public String getCourseOrderKey() {
		return CourseOrderKey;
	}

	/**
	 * @param courseOrderKey the courseOrderKey to set
	 */
	public void setCourseOrderKey(String courseOrderKey) {
		CourseOrderKey = courseOrderKey;
	}

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the creationDate
	 */
	public LocalDate getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the lastUpdateDate
	 */
	public LocalDate getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * @param lastUpdateDate the lastUpdateDate to set
	 */
	public void setLastUpdateDate(LocalDate lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CourseOrderResponse [CourseOrderKey=" + CourseOrderKey + ", courseName=" + courseName
				+ ", creationDate=" + creationDate + ", lastUpdateDate=" + lastUpdateDate + ", price=" + price + "]";
	}

}
