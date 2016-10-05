package com.example.dao.interfaces;

public interface EntityManager {
	
	public void beginTransaction();
	
	public void commit();
	
	public void rollback();
}
