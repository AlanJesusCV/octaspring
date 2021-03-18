package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.CourseActivity;

public interface CourseActivityInterface {
	public void save(CourseActivity courseActivity);
	
	public void update (CourseActivity courseActivity);
	
	public void delete (long id);
	
	public List<CourseActivity> findByAll(long id);
	
	public CourseActivity findById(long id);
	
	public List<CourseActivity> findByc(int id);

}
