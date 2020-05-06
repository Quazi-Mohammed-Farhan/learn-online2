package com.learn.online.services.impls;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.online.daos.CourseEntityDao;
import com.learn.online.dtos.CourseDto;
import com.learn.online.enums.ErrorMessagesEnum;
import com.learn.online.exceptions.CourseServiceException;
import com.learn.online.services.CourseService;
import com.learn.online.utils.CustomUtils;

@Service
public class CoursesServiceImpl implements CourseService {

	
	private static Logger LOGGER = LogManager.getLogger(CoursesServiceImpl.class);
	
	@Autowired
	private CourseEntityDao courseEntityDao;
	
	@Override
	public List<CourseDto> findCoursesByTechnology(String technologyName) {
		
		LOGGER.info("CoursesServiceImpl::findCoursesByTechnology() Started");
		
		LOGGER.info("Input: TechnologyName/Domain: " + technologyName);
		
		LOGGER.info("Search those courses by technology/domain and fetch them all");
		
		LOGGER.info("CoursesServiceImpl::findCoursesByTechnology() Completed");
		
		return CustomUtils.convertToCourseDtoList(courseEntityDao
				  .findCoursesByTechnology(technologyName)
				  .orElseThrow(()-> new CourseServiceException(
				   ErrorMessagesEnum.REQUESTED_COURSES_NOT_FOUND.getMessage())));
	
	
	}

	@Override
	public List<CourseDto> findByName(String courseName) {
		
		LOGGER.info("CoursesServiceImpl::findByName() Started");
		
		LOGGER.info("Input: Course name: " + courseName);
		
		LOGGER.info("Search course by name");
		
		LOGGER.info("CoursesServiceImpl::findByName() Completed");
		
		return CustomUtils.convertToCourseDtoList(courseEntityDao
				  .findByName(courseName)
				  .orElseThrow(()-> new CourseServiceException(
				   ErrorMessagesEnum.REQUESTED_COURSES_NOT_FOUND.getMessage())));
	}

	@Override
	public List<CourseDto> findCoursesByKey(List<String> courseKeyList) {
		
		LOGGER.info("CoursesServiceImpl::findCoursesByKey() Started");
		
		LOGGER.info("Input: Course Keys: " + courseKeyList);
		
		LOGGER.info("Search courses their course keys");
		
		LOGGER.info("CoursesServiceImpl::findByName() Completed");
		
		return CustomUtils.convertToCourseDtoList(courseEntityDao
				  .findCoursesByKey(courseKeyList)
				  .orElseThrow(()-> new CourseServiceException(
				   ErrorMessagesEnum.REQUESTED_COURSES_NOT_FOUND.getMessage())));
	}

	@Override
	public Map<String, List<CourseDto>> findAllCoursesGroupByDomain() {
		
		LOGGER.info("CoursesServiceImpl::findAllCoursesGroupByDomain() Started");
		
		LOGGER.info("Input: empty");
		
		LOGGER.info("CoursesServiceImpl::findByName() Completed");
		
		return findAllCoursesWithoutIds().orElseThrow(
					()-> new CourseServiceException(ErrorMessagesEnum.EMPTY_COURSES_LIST.getMessage()))
					.stream().collect(Collectors.toList()).stream()
						.collect(Collectors.groupingBy(CourseDto::getDomainName));
		
	}

	@Override
	public Map<String, Map<Double, List<CourseDto>>> findAllCoursesGroupByDomainAndRating() {
		
		LOGGER.info("CoursesServiceImpl::findAllCoursesGroupByDomainAndRating() Started");
		
		LOGGER.info("Input: empty");
		
		LOGGER.info("CoursesServiceImpl::findAllCoursesGroupByDomainAndRating() Completed");
		
		return findAllCoursesWithoutIds().orElseThrow
				(()-> new CourseServiceException(ErrorMessagesEnum.EMPTY_COURSES_LIST.getMessage()))
					.stream().collect(Collectors.groupingBy(CourseDto::getDomainName, 
							Collectors.groupingBy(CourseDto::getRating)));
	}

	@Override
	public Optional<List<CourseDto>> findAllCourses() {
		
		LOGGER.info("CoursesServiceImpl::findAllCourses() Started");
		
		LOGGER.info("Input: empty");
		
		LOGGER.info("CoursesServiceImpl::findAllCourses() Completed");
		
		return Optional.of( courseEntityDao.findAll().stream()
				.map(CustomUtils::convertToCourseDto).collect(Collectors.toList()));
	}

	@Override
	public Optional<List<CourseDto>> findAllCoursesWithoutIds() {
		
		LOGGER.info("CoursesServiceImpl::findAllCoursesWithoutIds() Started");
		
		LOGGER.info("Input: empty");
		
		LOGGER.info("CoursesServiceImpl::findAllCoursesWithoutIds() Completed");
		
		return Optional.of( courseEntityDao.findAll().stream()
				.map(CustomUtils::convertToCourseDtoWithoutIds).collect(Collectors.toList()));
	}
	
	

}