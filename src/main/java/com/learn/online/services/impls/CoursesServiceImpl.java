package com.learn.online.services.impls;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.online.beans.CourseEntity;
import com.learn.online.daos.CourseEntityDao;
import com.learn.online.dtos.CourseDto;
import com.learn.online.enums.ErrorMessagesEnum;
import com.learn.online.exceptions.CourseServiceException;
import com.learn.online.services.CourseService;
import com.learn.online.utils.CustomUtils;

/*
 * TODO: Logging part is remaining
 */
@Service
public class CoursesServiceImpl implements CourseService {

	
	@Autowired
	private CourseEntityDao courseEntityDao;
	
	@Override
	public List<CourseDto> findCoursesByTechnology(String technologyName) {
		
		return CustomUtils.convertToCourseDtoList(courseEntityDao
				  .findCoursesByTechnology(technologyName)
				  .orElseThrow(()-> new CourseServiceException(
				   ErrorMessagesEnum.REQUESTED_COURSES_NOT_FOUND.getMessage())));
	}

	@Override
	public List<CourseDto> findByName(String courseName) {
		return CustomUtils.convertToCourseDtoList(courseEntityDao
				  .findByName(courseName)
				  .orElseThrow(()-> new CourseServiceException(
				   ErrorMessagesEnum.REQUESTED_COURSES_NOT_FOUND.getMessage())));
	}

	@Override
	public List<CourseDto> findCoursesByKey(List<String> courseKeyList) {
		return CustomUtils.convertToCourseDtoList(courseEntityDao
				  .findCoursesByKey(courseKeyList)
				  .orElseThrow(()-> new CourseServiceException(
				   ErrorMessagesEnum.REQUESTED_COURSES_NOT_FOUND.getMessage())));
	}

	@Override
	public Map<String, List<CourseDto>> findAllCoursesGroupByDomain() {
		return findAllCoursesWithoutIds().orElseThrow(
					()-> new CourseServiceException(ErrorMessagesEnum.EMPTY_COURSES_LIST.getMessage()))
					.stream().collect(Collectors.toList()).stream()
						.collect(Collectors.groupingBy(CourseDto::getDomainName));
		
	}

	@Override
	public Map<String, Map<Double, List<CourseDto>>> findAllCoursesGroupByDomainAndRating() {
		
		return findAllCoursesWithoutIds().orElseThrow
				(()-> new CourseServiceException(ErrorMessagesEnum.EMPTY_COURSES_LIST.getMessage()))
					.stream().collect(Collectors.groupingBy(CourseDto::getDomainName, 
							Collectors.groupingBy(CourseDto::getRating)));
	}

	@Override
	public Optional<List<CourseDto>> findAllCourses() {
		
		return Optional.of( courseEntityDao.findAll().stream()
				.map(CustomUtils::convertToCourseDto).collect(Collectors.toList()));
	}

	@Override
	public Optional<List<CourseDto>> findAllCoursesWithoutIds() {
		
		return Optional.of( courseEntityDao.findAll().stream()
				.map(CustomUtils::convertToCourseDtoWithoutIds).collect(Collectors.toList()));
	}
	
	

}