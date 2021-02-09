package com.octaspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {


	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="subtitle")
	private String subtitle;
	
	@Column(name="description")
	private String description;

	@Column(name="price")
	private Double price;
	
	@Column(name="status")
	private int status;
	
	@Column(name="published")
	private String published;
	
	@Column(name="image")
	private String image;
	
	@Column(name="lang")
	private Lang lang;
	
	@Column(name="owner")
	private UserPerson owner;
	
	@Column(name="category")
	private Category category;
	
	@Column(name="subcategory")
	private Subcategory subcategory;

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Lang getLang() {
		return lang;
	}

	public void setLang(Lang lang) {
		this.lang = lang;
	}

	public UserPerson getOwner() {
		return owner;
	}

	public void setOwner(UserPerson owner) {
		this.owner = owner;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public Course( String title, String subtitle, String description, Double price, int status,
			String published, String image, Lang lang, UserPerson owner, Category category, Subcategory subcategory) {
		super();
		this.title = title;
		this.subtitle = subtitle;
		this.description = description;
		this.price = price;
		this.status = status;
		this.published = published;
		this.image = image;
		this.lang = lang;
		this.owner = owner;
		this.category = category;
		this.subcategory = subcategory;
	}
	
	public Course() {
		super();
	}


}
