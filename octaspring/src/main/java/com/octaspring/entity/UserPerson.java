package com.octaspring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_person")
public class UserPerson {
	
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;

	@Column(name="lastname")
	private String lastname;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="registered")
	private Date registered;
	
	@Column(name="status")
	private int status;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="photo")
	private String photo;

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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public UserPerson( String name, String lastname, String email, String password, Date registered,
			int status, String gender, String photo) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.registered = registered;
		this.status = status;
		this.gender = gender;
		this.photo = photo;
	}

	public UserPerson() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
