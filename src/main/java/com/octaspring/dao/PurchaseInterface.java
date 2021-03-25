package com.octaspring.dao;

import java.util.List;
import java.util.Set;

import com.octaspring.entity.Course;
import com.octaspring.entity.Purchase;

public interface PurchaseInterface {
	
	public void save(Set<Course> student_cart2);
	
	public void update (Purchase purchase);
	
	public void delete (long id);
	
	public List<Purchase> findByAll();
	
	public List<Purchase> findById(long id);
}
