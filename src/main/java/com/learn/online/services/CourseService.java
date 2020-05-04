package com.learn.online.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.learn.online.dtos.CourseDto;

public interface CourseService {
	
	public List<CourseDto> findCoursesByTechnology(String technologyName);
	public List<CourseDto> findByName(String courseName);
	public Optional<List<CourseDto>> findAllCourses();
	public Optional<List<CourseDto>> findAllCoursesWithoutIds();
	public List<CourseDto> findCoursesByKey(List<String> courseKeyList);
	public Map<String, List<CourseDto>> findAllCoursesGroupByDomain();
	public Map<String, Map<Double, List<CourseDto>>> findAllCoursesGroupByDomainAndRating();
	
}
