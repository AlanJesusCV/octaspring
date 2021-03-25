package com.octaspring.dao;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;

import com.octaspring.entity.Course;

public interface CourseInterface {
	
	public void save(Course course);
	
	public void update (Course course);
	
	public void delete (long id);
	
	public List<Course> findByAll();
	
	public Course findById(long id);

	void updateStatus(int status,int id);

	public List<Course> findCoursesByStatus(int status);
	
	public List<Course> findCoursesByCategory(int category);
	
	public List<Course> findAllByOwnwer(int owner);

	public boolean verifyUserHasCourse(long idCourse);

	public void adduserHasCourse(long idCourse);

	public List <Course> findAllBySearch(String searchword);

	Page<Course> findByAllPagination(Pageable pageable);
		
}
