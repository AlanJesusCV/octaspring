package com.octaspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="course_question")
public class CourseQuestion {

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="question")
	private String question;
	
	
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


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public UserPerson getOwner() {
		return user_person;
	}


	public void setOwner(UserPerson user_person) {
		this.user_person = user_person;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public CourseQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CourseQuestion(String question, UserPerson user_person, Course course) {
		super();
		this.question = question;
		this.user_person = user_person;
		this.course = course;
	}
	
	
}
