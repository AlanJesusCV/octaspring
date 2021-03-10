package com.octaspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRole {
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="role")
	private Role role;
	
	@Column(name="user_person")
	private UserPerson user_person;
	
	

	public UserPerson getUser_person() {
		return user_person;
	}

	public void setUser_person(UserPerson user_person) {
		this.user_person = user_person;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserRole(Role role, UserPerson user_person) {
		super();
		this.role = role;
		this.user_person = user_person;
	}

	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
}
