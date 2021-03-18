package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.UserRole;

public interface UserRoleInterface {
	public void save(UserRole userRole);
	
	public void update (UserRole userRole);
	
	public void delete (long id);
	
	public List<UserRole> findByAll();
	
	public List<UserRole> findById(long id);
}
