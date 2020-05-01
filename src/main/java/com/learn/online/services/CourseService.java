package com.learn.online.services;

import java.util.List;

import com.learn.online.dtos.CourseDto;

public interface CourseService {
	
	public List<CourseDto> findCoursesByTechnology(String technologyName);
	public List<CourseDto> findByName(String courseName);
	public List<CourseDto> findCoursesByKey(List<String> courseKeyList);
	
}
