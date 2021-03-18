package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.CourseQuestion;

public interface CourseQuestionInterface {
	public void save(CourseQuestion courseQuestion);
	
	public void update (CourseQuestion courseQuestion);
	
	public void delete (long id);
	
	public List<CourseQuestion> findByAll();
	
	public List<CourseQuestion> findById(long id);
}
