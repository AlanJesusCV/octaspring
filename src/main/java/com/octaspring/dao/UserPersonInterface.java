package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.UserPerson;

public interface UserPersonInterface {
	public void save(UserPerson userPerson, int role);
	
	public void update (UserPerson userPerson);
	
	public void delete (long id);
	
	public List<UserPerson> findByAll();
	
	public List<UserPerson> findById(long id);

	public UserPerson findByEmail(String email);

	
}
