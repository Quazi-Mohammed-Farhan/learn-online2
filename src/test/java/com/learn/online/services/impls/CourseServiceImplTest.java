package com.learn.online.services.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.AssertTrue;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.learn.online.beans.CourseEntity;
import com.learn.online.daos.CourseEntityDao;
import com.learn.online.dtos.CourseDto;
import com.learn.online.dummies.DummyData;
import com.learn.online.utils.CustomUtils;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CourseServiceImplTest {

	@Mock
	private CourseEntityDao courseEntityDao;

	@InjectMocks
	private CoursesServiceImpl courseService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findCoursesByTechnology() {
		
		List<CourseEntity> courseEntityList = DummyData.getCourseEntities();
		when(courseEntityDao.findCoursesByTechnology(anyString()))
			.thenReturn(Optional.of(courseEntityList));
		
		String domainName = "Java";
		List<CourseDto> courseDtoList = courseService.findCoursesByTechnology(domainName);
		
		assertEquals(courseEntityList.size(), courseDtoList.size());
		
		int courseDtoListSize = courseDtoList.size();
		CourseDto courseDtoToRemove = null;
		for(CourseEntity courseEntity : courseEntityList) {
			
			for(CourseDto courseDto : courseDtoList) {
				
				if(courseDto.getCourseId().equals(courseEntity.getCourseId())
					&& courseDto.getChapters().equals(courseEntity.getChapters())
					&& courseDto.getCourseKey().equals(courseEntity.getCourseKey())
					&& courseDto.getCourseName().equals(courseEntity.getCourseName())
					&& courseDto.getDomainName().equals(courseEntity.getDomainName())
					&& courseDto.getDurationInHours().equals(courseEntity.getDurationInHours())
					&& courseDto.getPrice().equals(courseEntity.getPrice())
					&& courseDto.getRating().equals(courseEntity.getRating())) {
					
					courseDtoToRemove = courseDto;
					break;
				}
			 }
			
			courseDtoList.remove(courseDtoToRemove);
		 }
		
		verify(courseEntityDao, times(1)).findCoursesByTechnology(domainName);
		
		assertTrue(courseEntityList.size() == courseDtoListSize 
				&& courseDtoList.size() ==0);

	}
	
	@Test
	public void findByName() {
		
		String courseName = "Restful Web Service with Spring boot";
		
		List<CourseDto> courseDtoList = DummyData.getAllCourses();
		
		when(courseEntityDao.findByName(courseName))
			.thenReturn(Optional.of(CustomUtils
					.convertToCourseEntityList(courseDtoList)));
		
		List<CourseDto> rtCourseDtoList = courseService.findByName(courseName);
		
		Long count = rtCourseDtoList.stream().filter(courseDto->{
			return courseName.equalsIgnoreCase(courseDto.getCourseName());
		}).collect(Collectors.counting());
		
		verify(courseEntityDao, times(1)).findByName(courseName);
		
		assertTrue(count > 0);
	}
	
	@Test
	public void findAllCourses() {
		
		List<CourseEntity> courseEntityList = DummyData.getCourseEntities(); 
		when(courseEntityDao.findAll()).thenReturn(courseEntityList);
		
		Optional<List<CourseDto>> rtCourseDtoList = courseService.findAllCourses();
		
		verify(courseEntityDao, times(1)).findAll();
		
		assertNotNull(rtCourseDtoList);
		assertTrue(rtCourseDtoList.isPresent());
	}
	
	
	@Test
	public void findAllCoursesWithoutIds() {
		
		List<CourseEntity> courseEntityList = DummyData.getCourseEntities(); 
		
		when(courseEntityDao.findAll()).thenReturn(courseEntityList);
		
		Optional<List<CourseDto>> courseDtoList = courseService.findAllCoursesWithoutIds();
		
		verify(courseEntityDao, times(1)).findAll();
		
		assertNotNull(courseDtoList);
		assertTrue(courseDtoList.isPresent());
	}
	
	@Test
	public void findCoursesByKey() {
		
		Optional<List<CourseEntity>> courseEntityList = Optional.of(DummyData.getCourseEntities());
		List<String> courseKeyList = courseEntityList.get()
				.stream().map(courseEntity->courseEntity.getCourseKey())
				.collect(Collectors.toList());
		
		when(courseEntityDao.findCoursesByKey(courseKeyList)).thenReturn(courseEntityList);
		
		List<CourseDto> courseDtoList = courseService.findCoursesByKey(courseKeyList);
		
		verify(courseEntityDao, times(1)).findCoursesByKey(courseKeyList);
		
		assertNotNull(courseDtoList);
		assertTrue(courseDtoList.size() != 0);
		assertTrue(courseDtoList.equals(CustomUtils.convertToCourseDtoList(courseEntityList.get())));
		
	}
	
	
	@Test
	public void findAllCoursesGroupByDomain() {
		
		List<CourseEntity> courseEntityList = DummyData.getCourseEntities(); 
		
		when(courseEntityDao.findAll()).thenReturn(courseEntityList);
		
		Map<String, List<CourseDto>> coursesByDomain = courseService.findAllCoursesGroupByDomain();
		
		List<CourseDto> allCourses = new ArrayList<>();
		coursesByDomain.values().forEach(courseDtoList->{
			courseDtoList.forEach(courseDto -> {
				allCourses.add(courseDto);
			});
		});
		
		int courseDtoListSize = allCourses.size();
		CourseDto courseDtoToRemove = null;
		for(CourseEntity courseEntity : courseEntityList) {
			
			for(CourseDto courseDto : allCourses) {
				
				if(courseDto.getChapters().equals(courseEntity.getChapters())
					&& courseDto.getCourseKey().equals(courseEntity.getCourseKey())
					&& courseDto.getCourseName().equals(courseEntity.getCourseName())
					&& courseDto.getDomainName().equals(courseEntity.getDomainName())
					&& courseDto.getDurationInHours().equals(courseEntity.getDurationInHours())
					&& courseDto.getPrice().equals(courseEntity.getPrice())
					&& courseDto.getRating().equals(courseEntity.getRating())) {
					
					courseDtoToRemove = courseDto;
					break;
				}
			 }
			
			allCourses.remove(courseDtoToRemove);
		 }
		
		verify(courseEntityDao, times(1)).findAll();
		
		assertTrue(courseEntityList.size() == courseDtoListSize 
				&& allCourses.size() ==0);
		 
	}
	
	
	public void findAllCoursesGroupByDomainAndRating() {
		
		List<CourseEntity> courseEntityList = DummyData.getCourseEntities(); 
		
		when(courseEntityDao.findAll()).thenReturn(courseEntityList);
		
		Map<String, Map<Double, List<CourseDto>>> coursesByDomainAndRating = 
				courseService.findAllCoursesGroupByDomainAndRating();
		
		System.out.println(coursesByDomainAndRating);
		
		List<CourseDto> allCourses = new ArrayList<>();
		coursesByDomainAndRating.values().forEach(courseMapByRating->{
			courseMapByRating.values()
				.forEach(courseDtoList->{
					courseDtoList.forEach(courseDto->{
						allCourses.add(courseDto);
					});});
		});	
		
		int courseDtoListSize = allCourses.size();
		CourseDto courseDtoToRemove = null;
		for(CourseEntity courseEntity : courseEntityList) {
			
			for(CourseDto courseDto : allCourses) {
				
				if(courseDto.getChapters().equals(courseEntity.getChapters())
					&& courseDto.getCourseKey().equals(courseEntity.getCourseKey())
					&& courseDto.getCourseName().equals(courseEntity.getCourseName())
					&& courseDto.getDomainName().equals(courseEntity.getDomainName())
					&& courseDto.getDurationInHours().equals(courseEntity.getDurationInHours())
					&& courseDto.getPrice().equals(courseEntity.getPrice())
					&& courseDto.getRating().equals(courseEntity.getRating())) {
					
					courseDtoToRemove = courseDto;
					break;
				}
			 }
			
			allCourses.remove(courseDtoToRemove);
		 }
		
		verify(courseEntityDao, times(1)).findAll();
		
		assertTrue(courseEntityList.size() == courseDtoListSize 
				&& allCourses.size() ==0);
	}

}
