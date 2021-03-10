package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.Subcategory;

public interface SubcategoryInterface {
	public void save(Subcategory subCategory);
	
	public void update (Subcategory subCategory);
	
	public void delete (long id);
	
	public List<Subcategory> findByAll();
	
	public Subcategory findById(long id);

	 void updateStatus(Subcategory subcategory);

}
