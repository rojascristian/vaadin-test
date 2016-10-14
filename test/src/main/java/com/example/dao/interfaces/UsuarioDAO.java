package com.example.dao.interfaces;

import java.util.List;
import com.example.modelo.Usuario;

public interface UsuarioDAO {
	public List<Usuario> findAll();

	public Usuario findByNombre(String cuil);

	public void save(Usuario persona);
	
	public void delete(Usuario persona);
	
	public List<Usuario> find(Usuario persona);
}
