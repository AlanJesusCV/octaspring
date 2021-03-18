package com.octaspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="course_activity")
public class CourseActivity {
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="duration")
	private String duration;
	
	@Column(name="file")
	private String file;
	
	@Column(name="video")
	private String video;
	
	@Column(name="course")
	private Course course;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public CourseActivity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseActivity(String title, String description, String duration, String file, String video, Course course) {
		super();
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.file = file;
		this.video = video;
		this.course = course;
	}
	
	
}
