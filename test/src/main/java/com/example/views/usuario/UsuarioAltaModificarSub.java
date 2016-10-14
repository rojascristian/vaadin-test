package com.example.views.usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.dao.implementations.EntityManagerFactory;
import com.example.dao.implementations.hibernate.RolDAOHib;
import com.example.dao.implementations.hibernate.UsuarioDAOHib;
import com.example.dao.interfaces.EntityManager;
import com.example.modelo.Rol;
import com.example.modelo.Usuario;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

// Define a sub-window by inheritance
class UsuarioAltaModificarSub extends Window {
//	class PersonaAltaModificarSub extends Window {
    private TextField tfNombre;
	private TextField tfApellido;
	private TextField tfEmail;
	private DateField dfFechaNacimiento;
	private Button btnGuardar;
	private Button btnCerrar;
	private ComboBox cbbRoles;
	
	private EntityManager em = EntityManagerFactory.getInstance(EntityManagerFactory.MYSQL);
	private RolDAOHib rdh = new RolDAOHib();
	
//	public UsuarioAltaModificarSub(String captionWindow) {
	public UsuarioAltaModificarSub(String captionWindow, Usuario usuario) {
        super(captionWindow); // Set window caption
        center();
        
        em.beginTransaction();
        List<Rol> roles = rdh.findAll();
        BeanItemContainer<Rol> rolesBean = new BeanItemContainer<Rol>(Rol.class, roles);
        

        // Some basic content for the window
        FormLayout content = new FormLayout();
        tfNombre = new TextField("Nombre :");
        tfApellido = new TextField("Apellido :");
        tfEmail = new TextField("Email :");
        dfFechaNacimiento = new DateField();
        cbbRoles = new ComboBox("Asignar Roles", rolesBean);
        cbbRoles.setItemCaptionPropertyId("descripcion");
        content.addComponents(tfNombre, tfApellido, tfEmail, dfFechaNacimiento, cbbRoles);
        
        content.setMargin(true);
        setContent(content);

        // Disable the close button
        setClosable(false);

        HorizontalLayout hlAcciones = new HorizontalLayout();
        
	        btnCerrar = new Button("Cerrar");
	        btnCerrar.addClickListener(e -> {
	        	close();
	        });
	        btnGuardar = new Button("Guardar");
	    
	    initUsuario(usuario);
	        
	    //TODO: terminar de implementar
        guardarAction(usuario);
        
        hlAcciones.addComponents(btnGuardar, btnCerrar);
        
        content.addComponent(hlAcciones);
        setModal(true);
    }

	private void initUsuario(Usuario usuario) {
		if(usuario.getId() != null){
			tfNombre.setValue(usuario.getNombre());
			tfApellido.setValue(usuario.getApellido());
			tfEmail.setValue(usuario.getEmail());
			dfFechaNacimiento.setValue(usuario.getFechaNacimiento());
		}
	}

	private void guardarAction(Usuario usuario) {
		btnGuardar.addClickListener(e -> {
			EntityManager em = EntityManagerFactory.getInstance(EntityManagerFactory.MYSQL);
			em.beginTransaction();
			if(usuario.getId() == null){
				usuario.setNombre(tfNombre.getValue());
				usuario.setApellido(tfApellido.getValue());
				usuario.setEmail(tfEmail.getValue());
				usuario.setFechaNacimiento(dfFechaNacimiento.getValue());
				List<Rol> roles = new ArrayList<Rol>();
				roles.add((Rol) cbbRoles.getValue());
				usuario.setRoles(roles);
			}
			UsuarioDAOHib pdh = new UsuarioDAOHib();
			pdh.save(usuario);
			em.commit();
			close();
		});
	}
	
}