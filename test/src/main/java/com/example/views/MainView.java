package com.example.views;

import com.example.views.rol.RolView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
//import com.vaadin.ui.declarative.Design;

public class MainView extends VerticalLayout implements View {
	private static final long serialVersionUID = -1065352167160385868L;
	public static String NAME = "main";
	
	Navigator mainViewNavigator;
	
    public MainView() {
    	
    	HorizontalLayout hlHeader = new HorizontalLayout();
    	hlHeader.setSizeFull();
    	Button btnLogout = new Button("Log-out");
    	hlHeader.addComponent(btnLogout);
    	hlHeader.setComponentAlignment(btnLogout, Alignment.TOP_RIGHT);
    	addComponent(hlHeader);
    	
    	HorizontalLayout menuAndContent = new HorizontalLayout();
    	Panel panelMenu = new Panel();
    	Tree menuTree = new Tree("Contenido");
    	panelMenu.setContent(menuTree);
    	
    	// TODO: AGREGAR ACCIONES DE ACUERDO A LOS ROLES DEL USUARIO
    	// Couple of childless root items
    	menuTree.addItem("Usuarios");
    	menuTree.setChildrenAllowed("Usuarios", false);
    	menuTree.addItem("Roles");
    	menuTree.setChildrenAllowed("Roles", false);
    	menuAndContent.addComponent(panelMenu);
    	
    	Panel panelContent = new Panel();
    	panelContent.setContent(new PersonaView());
    	menuAndContent.addComponent(panelContent);
    	
    	addComponent(menuAndContent);
    	menuAndContent.setExpandRatio(panelMenu, 2);
    	menuAndContent.setExpandRatio(panelContent, 8);
    	
    	HorizontalLayout hlFooter = new HorizontalLayout();
    	addComponent(hlFooter);
    	
    	btnLogout.addClickListener(e -> {
    		getSession().close();
    		UI.getCurrent().getNavigator().navigateTo(LoginView.NAME);
    	});
    	
    	// TODO: VER SI SE PUEDE USAR CON NAVIGATION
    	menuTree.addValueChangeListener(event -> {
    	    if (event.getProperty() != null && event.getProperty().getValue() != null) {
    	            if(event.getProperty().getValue() == "Usuarios"){
    	            	panelContent.setContent(new PersonaView());
    	            }
    	            if(event.getProperty().getValue() == "Roles"){
    	            	panelContent.setContent(new RolView());
    	            }
    	    }
    	});
    }

    @Override
    public void enter(ViewChangeEvent event) {
    	
    }
}