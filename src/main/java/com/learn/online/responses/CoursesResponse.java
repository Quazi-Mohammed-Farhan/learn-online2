package com.learn.online.responses;

import java.time.LocalDate;

public class CoursesResponse {

	private String courseKey;
	private String domainName;
	private String courseName;
	private String description;
	private Integer chapters;
	private Integer durationInHours;
	private Double price;
	private Double rating;
	private LocalDate creationtDate;
	private LocalDate lastUpdateDate;
	private String url;

	/**
	 * @return the courseKey
	 */
	public String getCourseKey() {
		return courseKey;
	}

	/**
	 * @param courseKey the courseKey to set
	 */
	public void setCourseKey(String courseKey) {
		this.courseKey = courseKey;
	}

	/**
	 * @return the domainName
	 */
	public String getDomainName() {
		return domainName;
	}

	/**
	 * @param domainName the domainName to set
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the chapters
	 */
	public Integer getChapters() {
		return chapters;
	}

	/**
	 * @param chapters the chapters to set
	 */
	public void setChapters(Integer chapters) {
		this.chapters = chapters;
	}

	/**
	 * @return the durationInHours
	 */
	public Integer getDurationInHours() {
		return durationInHours;
	}

	/**
	 * @param durationInHours the durationInHours to set
	 */
	public void setDurationInHours(Integer durationInHours) {
		this.durationInHours = durationInHours;
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

	/**
	 * @return the rating
	 */
	public Double getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(Double rating) {
		this.rating = rating;
	}

	/**
	 * @return the creationtDate
	 */
	public LocalDate getCreationtDate() {
		return creationtDate;
	}

	/**
	 * @param creationtDate the creationtDate to set
	 */
	public void setCreationtDate(LocalDate creationtDate) {
		this.creationtDate = creationtDate;
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "AvaileCoursesResponse [courseKey=" + courseKey + ", domainName=" + domainName + ", courseName="
				+ courseName + ", description=" + description + ", chapters=" + chapters + ", durationInHours="
				+ durationInHours + ", price=" + price + ", rating=" + rating + ", creationtDate=" + creationtDate
				+ ", lastUpdateDate=" + lastUpdateDate + ", url=" + url + "]";
	}

}
