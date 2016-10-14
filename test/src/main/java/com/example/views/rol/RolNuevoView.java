package com.example.views.rol;

import com.example.dao.implementations.EntityManagerFactory;
import com.example.dao.implementations.hibernate.UsuarioDAOHib;
import com.example.dao.implementations.hibernate.RolDAOHib;
import com.example.dao.interfaces.EntityManager;
import com.example.modelo.Rol;
import com.example.modelo.Usuario;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

// Define a sub-window by inheritance
class RolNuevoView extends Window {
	
	private static final long serialVersionUID = 6131124999627691459L;
	//	class PersonaAltaModificarSub extends Window {
    private TextField tfNombre;
	private TextField tfApellido;
	private TextField tfEmail;
	private DateField dfFechaNacimiento;
	private Button btnGuardar;
	private Button btnCerrar;
	
	private EntityManager em = EntityManagerFactory.getInstance(EntityManagerFactory.MYSQL);
	
	public RolNuevoView(String captionWindow) {
        super(captionWindow); // Set window caption
        center();

        // Some basic content for the window
        FormLayout content = new FormLayout();
        tfNombre = new TextField("Nombre :");
        content.addComponents(tfNombre);
        
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
			Rol rol = new Rol();
			rol.setDescripcion(tfNombre.getValue());
			em.beginTransaction();
			RolDAOHib rdh = new RolDAOHib();
			rdh.save(rol);
			em.commit();
			close();
		});
	}
	
}