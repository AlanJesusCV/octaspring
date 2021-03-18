package com.octaspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lang")
public class Lang {

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="lang")
	private String lang;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	
	public Lang() {
		super();
	}
	
	public Lang(String lang) {
		super();
		this.lang= lang;
	}
}
