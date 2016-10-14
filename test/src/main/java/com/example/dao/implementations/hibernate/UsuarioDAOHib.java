package com.example.dao.implementations.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.example.dao.interfaces.UsuarioDAO;
import com.example.modelo.Usuario;

//@Repository("personaDao")
public class UsuarioDAOHib extends AbstractDAOHib implements UsuarioDAO {

	public UsuarioDAOHib() {
		super();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> findAll() {
		Criteria criteria = getSession().createCriteria(Usuario.class);
		List<Usuario> persons = (List<Usuario>) criteria.list();
		return persons;
	}


	@Override
	public Usuario findByNombre(String nombre) {
		Criteria criteria = getSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		return (Usuario) criteria.uniqueResult();
	}


	@Override
	public void save(Usuario usuario) {
		getSession().save(usuario);
	}

	@Override
	public void delete(Usuario usuario) {
		getSession().delete(usuario);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> find(Usuario usuario) {
		Criteria criteria = getSession().createCriteria(Usuario.class);
		if(usuario.getNombre() != "" && usuario.getNombre() != null){
			criteria.add(Restrictions.eq("nombre", usuario.getNombre()));
		}
		if(usuario.getApellido() != "" && usuario.getApellido() != null){
			criteria.add(Restrictions.eq("apellido", usuario.getApellido()));
		}
		if(usuario.getEmail() != "" && usuario.getEmail() != null){
			criteria.add(Restrictions.eq("nombre", usuario.getEmail()));
		}
		List<Usuario> personas = (List<Usuario>) criteria.list();
		return personas;
	}
	
	public Usuario findById(Long id) {
		Criteria criteria = getSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("id", id));
		return (Usuario) criteria.uniqueResult();
	}

}
