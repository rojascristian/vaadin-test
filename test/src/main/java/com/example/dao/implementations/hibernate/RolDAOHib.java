package com.example.dao.implementations.hibernate;

import java.util.List;

import org.hibernate.Criteria;

import com.example.dao.interfaces.RolDAO;
import com.example.modelo.Rol;

public class RolDAOHib extends AbstractDAOHib implements RolDAO {

	public RolDAOHib() {
		super();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Rol> findAll() {
		Criteria criteria = getSession().createCriteria(Rol.class);
		List<Rol> rol = (List<Rol>) criteria.list();
		return rol;
	}

	@Override
	public void save(Rol rol) {
		getSession().save(rol);		
	}

	@Override
	public void delete(Rol rol) {
		getSession().delete(rol);
	}

}
