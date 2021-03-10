package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.Lang;
//Interface dao
public interface LangInterface {

	public void save(Lang lang);
	
	public void update (Lang lang);
	
	public void delete (long id);
	
	public List<Lang> findByAll();
	
	public Lang findById(long id);
	
}
