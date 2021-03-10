package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.UserHasCourse;

public interface UserHasCourseInterface {
	public void save(UserHasCourse userHasCoruse);
	
	public void update (UserHasCourse userHasCoruse);
	
	public void delete (long id);
	
	public List<UserHasCourse> findByAll();
	
	public List<UserHasCourse> findById(long id);
}
