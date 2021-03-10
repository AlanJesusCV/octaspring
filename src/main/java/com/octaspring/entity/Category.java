package com.octaspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="category")
public class Category {
	
	//@ANNOTAATION (parametros... message="mensaje")
	
	//@NotBlank no debe estar vacio
	//@NotNull
	//@Size (min=x, max=x)
	//@Email
	//@Pattern(regexp = "exp"
	//@AssertTrue
	//@URL
	
	
	@Id
	private Long id;
	
	@NotBlank(message="Ingresar el nombre de la categoria")
	@Size(min=5,max=50, message="El nombre debe tener de 5 a 50 caracteres")
	@Column(name="name")
	private String name;
	
	@NotBlank(message="Ingresar el nombre de la descripcion")
	@Column(name="description")
	private String description;
	
	@Column(name="image")
	private String image;
	
	@Column(name="status")
	private int status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public Category() {
		super();
	}
	
	public Category(String name,String description, String image, int status) {
		super();
		this.name= name;
		this.description = description;
		this.image = image;
		this.status = status;


	}
}
