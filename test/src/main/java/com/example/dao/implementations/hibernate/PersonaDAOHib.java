package com.example.dao.implementations.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.example.dao.interfaces.PersonaDAO;
import com.example.modelo.Usuario;

//@Repository("personaDao")
public class PersonaDAOHib extends AbstractDAOHib implements PersonaDAO {

	public PersonaDAOHib() {
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
	public void save(Usuario persona) {
		getSession().save(persona);
	}

	@Override
	public void delete(Usuario persona) {
		getSession().delete(persona);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> find(Usuario persona) {
		Criteria criteria = getSession().createCriteria(Usuario.class);
		if(persona.getNombre() != "" && persona.getNombre() != null){
			criteria.add(Restrictions.eq("nombre", persona.getNombre()));
		}
		if(persona.getApellido() != "" && persona.getApellido() != null){
			criteria.add(Restrictions.eq("apellido", persona.getApellido()));
		}
		if(persona.getEmail() != "" && persona.getEmail() != null){
			criteria.add(Restrictions.eq("nombre", persona.getEmail()));
		}
		List<Usuario> personas = (List<Usuario>) criteria.list();
		return personas;
	}

}
