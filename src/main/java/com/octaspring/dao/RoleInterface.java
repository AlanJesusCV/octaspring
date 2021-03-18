package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.Role;

public interface RoleInterface {
	public void save(Role role);
	
	public void update (Role role);
	
	public void delete (long id);
	
	public List<Role> findByAll();
	
	public Role findById(long id);

	public void deleteLogical(long id, int status);

	public List<Role> findRolesNotAdmin();

}
