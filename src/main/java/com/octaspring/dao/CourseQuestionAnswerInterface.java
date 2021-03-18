package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.CourseQuestionAnswer;

public interface CourseQuestionAnswerInterface {
	public void save(CourseQuestionAnswer courseQuestionAnswer);
	
	public void update (CourseQuestionAnswer courseQuestionAnswer);
	
	public void delete (long id);
	
	public List<CourseQuestionAnswer> findByAll();
	
	public List<CourseQuestionAnswer> findById(long id);
}
