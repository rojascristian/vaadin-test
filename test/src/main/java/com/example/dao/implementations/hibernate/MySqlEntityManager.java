package com.example.dao.implementations.hibernate;

import org.hibernate.Session;

import com.example.dao.interfaces.EntityManager;
import com.example.util.HibernateUtil;

public class MySqlEntityManager implements EntityManager {
	
	public void beginTransaction() {
		Session currentSession = HibernateUtil.getCurrentSession();
		if (!currentSession.getTransaction().isActive()) {
			currentSession.beginTransaction();
		}
	}
	
	public void commit() {
		HibernateUtil.getCurrentSession().getTransaction().commit();
	}
	
	public void rollback() {
		HibernateUtil.getCurrentSession().getTransaction().rollback();
	}


}
