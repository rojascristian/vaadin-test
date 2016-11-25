package com.example.views.usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.jpa.event.internal.jpa.ListenerCallback;

import com.example.dao.implementations.EntityManagerFactory;
import com.example.dao.implementations.hibernate.RolDAOHib;
import com.example.dao.implementations.hibernate.UsuarioDAOHib;
import com.example.dao.interfaces.EntityManager;
import com.example.modelo.Rol;
import com.example.modelo.Usuario;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.Window;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;

// Define a sub-window by inheritance
class UsuarioAltaModificarSub extends Window {
	
    private TextField tfNombre;
	private TextField tfApellido;
	private TextField tfEmail;
	private DateField dfFechaNacimiento;
	private Button btnGuardar;
	private Button btnCerrar;
	
	private EntityManager em = EntityManagerFactory.getInstance(EntityManagerFactory.MYSQL);
	private RolDAOHib rdh = new RolDAOHib();
	private UsuarioDAOHib udh = new UsuarioDAOHib();
	private TwinColSelect select;
	
	public UsuarioAltaModificarSub(String captionWindow, Usuario usuario) {
        super(captionWindow); // Set window caption
        center();
        
        em.beginTransaction();
        List<Rol> roles = rdh.findAll();
        BeanItemContainer<Rol> rolesBean = new BeanItemContainer<Rol>(Rol.class, roles);
        
        setContent(generarVista(rolesBean));
	    
	    initUsuario(usuario);
	        
        guardarAction(usuario);
        
    }

	private FormLayout generarVista(BeanItemContainer<Rol> rolesBean) {
		// Some basic content for the window
        FormLayout content = new FormLayout();
        tfNombre = new TextField("Nombre :");
        tfApellido = new TextField("Apellido :");
        tfEmail = new TextField("Email :");
        dfFechaNacimiento = new DateField();
        dfFechaNacimiento.setCaption("Fecha de nacimiento: ");
        
        HorizontalLayout hlRoles = new HorizontalLayout();
        
        select = new TwinColSelect("Asignación de roles");
        
//        select.addValueChangeListener(new ValueChangeListener() {
//			
//			@Override
//			public void valueChange(ValueChangeEvent event) {
//				System.out.println("ENTRO ACA!!!");
//			}
//		});
        
    	// Set the column captions (optional)
    	select.setLeftColumnCaption("Roles");
    	select.setRightColumnCaption("Roles Asignados");
    	select.setContainerDataSource(rolesBean);
    	select.setItemCaptionMode(TwinColSelect.ITEM_CAPTION_MODE_PROPERTY);
		select.setItemCaptionPropertyId("descripcion");            
		select.setMultiSelect(true);
		
    	hlRoles.addComponent(select);
    	
        content.addComponents(tfNombre, tfApellido, tfEmail, dfFechaNacimiento, hlRoles);
        
        content.setMargin(true);

        // Disable the close button
        setClosable(false);

        HorizontalLayout hlAcciones = new HorizontalLayout();
        
	        btnCerrar = new Button("Cerrar");
	        btnCerrar.addClickListener(e -> {
	        	close();
	        });
	        btnGuardar = new Button("Guardar");
	        
        hlAcciones.addComponents(btnGuardar, btnCerrar);
        content.addComponent(hlAcciones);
        setModal(true);
        
        return content;
	}

	private void initUsuario(Usuario usuario) {
		if(usuario.getId() != null){
			tfNombre.setValue(usuario.getNombre());
			tfApellido.setValue(usuario.getApellido());
			tfEmail.setValue(usuario.getEmail());
			dfFechaNacimiento.setValue(usuario.getFechaNacimiento());
			select.setValue(usuario.getRoles());
		}
	}

	private void guardarAction(Usuario usuario) {
		btnGuardar.addClickListener(e -> {
			Usuario user;
			em.beginTransaction();
			if(usuario.getId() != null){
				user = udh.findById(usuario.getId());
			} else {
				user = usuario;
			}
			
			user.setNombre(tfNombre.getValue());
			user.setApellido(tfApellido.getValue());
			user.setEmail(tfEmail.getValue());
			user.setFechaNacimiento(dfFechaNacimiento.getValue());
			List<Rol> roles = new ArrayList<Rol>();
			
//			Set<Rol> hs = (Set<Rol>) select.getValue();
			
			user.setRoles(roles);
			
			udh.save(user);
			
			em.commit();
			close();
		});
	}
	
}