package com.octaspring.dao;

import java.util.List;

import com.octaspring.entity.Purchase;

public interface PurchaseInterface {
	
	public void save(Purchase purchase);
	
	public void update (Purchase purchase);
	
	public void delete (long id);
	
	public List<Purchase> findByAll();
	
	public List<Purchase> findById(long id);
}
