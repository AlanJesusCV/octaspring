package com.octaspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lang")
public class UserHasGrade {
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="grade")
	private int grade;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="course")
	private Course course;
	
	@Column(name="user_person")
	private UserPerson user_person;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public UserPerson getUser_person() {
		return user_person;
	}

	public void setUser_person(UserPerson user_person) {
		this.user_person = user_person;
	}

	public UserHasGrade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserHasGrade( int grade, String comment, Course course, UserPerson user_person) {
		super();
		this.grade = grade;
		this.comment = comment;
		this.course = course;
		this.user_person = user_person;
	}
	
	
}
