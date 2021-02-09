package com.octaspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subcategory")
public class Subcategory {

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="subtitle")
	private String subtitle;
	
	@Column(name="description")
	private String description;
	
	@Column(name="status")
	private int status;
	
	@Column(name="category")
	private Category category;

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

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Subcategory( String title, String subtitle, String description, int status, Category category) {
		super();
		this.title = title;
		this.subtitle = subtitle;
		this.description = description;
		this.status = status;
		this.category = category;
	}

	public Subcategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
