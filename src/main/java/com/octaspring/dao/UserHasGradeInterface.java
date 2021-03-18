package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.UserHasGrade;

public interface UserHasGradeInterface {
	public void save(UserHasGrade userHasGrade);
	
	public void update (UserHasGrade userHasGrade);
	
	public void delete (long id);
	
	public List<UserHasGrade> findByAll();
	
	public List<UserHasGrade> findById(long id);
}
