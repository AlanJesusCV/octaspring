package com.octaspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="course_has_question_answer")
public class CourseQuestionAnswer {
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="answer")
	private String answer;
	
	@Column(name="user_person")
	private UserPerson user_person;
	
	@Column(name="question")
	private CourseQuestion question;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public UserPerson getOwner() {
		return user_person;
	}

	public void setOwner(UserPerson user_person) {
		this.user_person = user_person;
	}

	public CourseQuestion getQuestion() {
		return question;
	}

	public void setQuestion(CourseQuestion question) {
		this.question = question;
	}

	public CourseQuestionAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseQuestionAnswer(String answer, UserPerson user_person, CourseQuestion question) {
		super();
		this.answer = answer;
		this.user_person = user_person;
		this.question = question;
	}
	
	
}
