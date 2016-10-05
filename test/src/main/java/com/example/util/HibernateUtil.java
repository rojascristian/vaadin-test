package com.example.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	private static final StandardServiceRegistry serviceRegistry;
	
	protected static String ENVIRONMENT = "dev";// dev, prod

	static {
		try {
			Configuration config = new Configuration().configure(ENVIRONMENT + "/hibernate.cfg.xml");
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			sessionFactory = config.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	
	public static Session getCurrentSession() {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession;
	}
	
}
