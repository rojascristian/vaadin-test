package com.example.dao.implementations;

import com.example.dao.implementations.hibernate.MySqlEntityManager;
import com.example.dao.interfaces.EntityManager;

public class EntityManagerFactory {
	public static final Class<MySqlEntityManager> MYSQL = MySqlEntityManager.class;  
	
	
    public static EntityManager getInstance(Class<?> em) {  
        try {  
            return (EntityManager)em.newInstance();  
        } catch (Exception ex) {  
            throw new RuntimeException("no se pudo crear: " + em);  
        }  
    }  
    


}
