package com.example.views.rol;

import com.example.dao.implementations.EntityManagerFactory;
import com.example.dao.implementations.hibernate.RolDAOHib;
import com.example.dao.interfaces.EntityManager;
import com.example.modelo.Rol;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class RolView extends VerticalLayout implements View {

	private static final long serialVersionUID = -5013370014671349339L;
	public static String NAME = "rol";
	private EntityManager em = EntityManagerFactory.getInstance(EntityManagerFactory.MYSQL);
	private Grid gridRoles;
	private RolNuevoView rnv;
	
	public RolView() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSizeFull();
		addComponent(layout);
		
		gridRoles = new Grid();
		gridRoles.setColumns("descripcion");
		layout.addComponent(gridRoles);
		
		FormLayout formRoles = new FormLayout();
		layout.addComponent(formRoles);
		
		Button btnNuevo = new Button("Nuevo");
		formRoles.addComponent(btnNuevo);
		
		layout.setExpandRatio(gridRoles, 6);
		layout.setExpandRatio(formRoles, 4);
		
		updateGrid();
		
    	btnNuevo.addClickListener(e -> {
    		rnv = new RolNuevoView("Alta de persona");
    		rnv.setWidth("400px");
    		rnv.setHeight("400px");
    		UI.getCurrent().addWindow(rnv);
    		rnv.addCloseListener(event -> {
    			updateGrid();
    		});
    	});
		
	}

	private void updateGrid() {
		em.beginTransaction();
		RolDAOHib rdh = new RolDAOHib();
    	BeanItemContainer<Rol> rolData = new BeanItemContainer<Rol>(Rol.class, rdh.findAll());
    	gridRoles.setContainerDataSource(rolData);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
