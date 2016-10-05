package com.example.dao.interfaces;

import java.util.List;

import com.example.modelo.Rol;

public interface RolDAO {
	public List<Rol> findAll();

	public void save(Rol rol);
	
	public void delete(Rol rol);
}
