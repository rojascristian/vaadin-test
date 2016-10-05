package com.example.dao.implementations.hibernate;

import org.hibernate.Session;

import com.example.util.HibernateUtil;

public class AbstractDAOHib {
	
	public AbstractDAOHib() {
	}
	
	public Session getSession(){
		return HibernateUtil.getCurrentSession();
	}
	


}
