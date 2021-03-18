package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.PurchaseCourse;

public interface PurchaseCourseInterface {
	public void save(PurchaseCourse purchaseCourse);
	
	public void update (PurchaseCourse purchaseCourse);
	
	public void delete (long id);
	
	public List<PurchaseCourse> findByAll();
	
	public List<PurchaseCourse> findById(long id);
}
