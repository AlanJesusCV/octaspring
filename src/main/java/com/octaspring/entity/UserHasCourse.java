package com.octaspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_has_course")
public class UserHasCourse {
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="user_person")
	private UserPerson user_person;

	@Column(name="course")
	private Course course;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserPerson getUser_person() {
		return user_person;
	}

	public void setUser_person(UserPerson user_person) {
		this.user_person = user_person;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public UserHasCourse(UserPerson user_person, Course course) {
		super();
		this.user_person = user_person;
		this.course = course;
	}

	public UserHasCourse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
