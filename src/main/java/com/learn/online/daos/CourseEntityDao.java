package com.learn.online.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learn.online.beans.CourseEntity;

@Repository
public interface CourseEntityDao extends JpaRepository<CourseEntity, Long> {

	@Query("from CourseEntity c where c.domainName = :technologyName")
	public Optional<List<CourseEntity>> findCoursesByTechnology(
			@Param("technologyName") String technologyName);
	
	@Query("from CourseEntity c where c.courseName in(:courseName)") 
	public Optional<List<CourseEntity>> findByName(
			@Param("courseName") String courseName);
	
	@Query("from CourseEntity c where c.courseKey in(:courseKeyList)") 
	public Optional<List<CourseEntity>> findCoursesByKey(
			@Param("courseKeyList") List<String> courseKeyList);

}