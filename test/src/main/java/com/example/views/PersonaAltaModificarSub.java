package com.example.views;

import java.util.Date;

import com.example.dao.implementations.EntityManagerFactory;
import com.example.dao.implementations.hibernate.PersonaDAOHib;
import com.example.dao.interfaces.EntityManager;
import com.example.modelo.Usuario;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

// Define a sub-window by inheritance
class PersonaAltaModificarSub extends Window {
//	class PersonaAltaModificarSub extends Window {
    private TextField tfNombre;
	private TextField tfApellido;
	private TextField tfEmail;
	private DateField dfFechaNacimiento;
	private Button btnGuardar;
	private Button btnCerrar;
	
	public PersonaAltaModificarSub(String captionWindow) {
        super(captionWindow); // Set window caption
        center();

        // Some basic content for the window
        FormLayout content = new FormLayout();
        tfNombre = new TextField("Nombre :");
        tfApellido = new TextField("Apellido :");
        tfEmail = new TextField("Email :");
        dfFechaNacimiento = new DateField();
        content.addComponents(tfNombre, tfApellido, tfEmail, dfFechaNacimiento);
        
        content.setMargin(true);
        setContent(content);

        // Disable the close button
        setClosable(false);

        HorizontalLayout hlAcciones = new HorizontalLayout();
        
	        btnCerrar = new Button("Cerrar");
	        btnCerrar.addClickListener(e -> {
	        	close();
	        });
	        
        guardarAction();
        
        hlAcciones.addComponents(btnGuardar, btnCerrar);
        
        content.addComponent(hlAcciones);
        setModal(true);
    }

	private void guardarAction() {
		btnGuardar = new Button("Guardar");
		btnGuardar.addClickListener(e -> {
			Usuario persona = new Usuario();
			persona.setNombre(tfNombre.getValue());
			persona.setApellido(tfApellido.getValue());
			persona.setEmail(tfEmail.getValue());
			persona.setFechaNacimiento(dfFechaNacimiento.getValue());
			EntityManager em = EntityManagerFactory.getInstance(EntityManagerFactory.MYSQL);
			em.beginTransaction();
			PersonaDAOHib pdh = new PersonaDAOHib();
			pdh.save(persona);
			em.commit();
			close();
		});
	}
	
}