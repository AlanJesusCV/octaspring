package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.CourseActivity;

public interface CourseActivityInterface {
	public void save(CourseActivity courseActivity);
	
	public void update (CourseActivity courseActivity);
	
	public void delete (long id);
	
	public List<CourseActivity> findByAll();
	
	public List<CourseActivity> findById(long id);
}
