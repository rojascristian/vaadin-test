package com.example.views;

import com.example.MainNavigator;
import com.example.event.MainEventBus;
import com.example.event.MainEvent.UserLogoutRequestedEvent;
import com.example.modelo.Usuario;
import com.example.views.rol.RolView;
import com.example.views.usuario.UsuarioView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class MainView extends VerticalLayout implements View {
	private static final long serialVersionUID = -1065352167160385868L;
	public static String NAME = "main";
	
	Navigator mainNavigator;
	private VerticalLayout bodyContent;
	
    public MainView() {
    	
    	
    	addComponent(generarHeader());
    	addComponent(generarBody());
    	addComponent(generarFooter());
    	
    	mainNavigator = new MainNavigator(bodyContent);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    	
    }
    
    public HorizontalLayout generarHeader(){
    	HorizontalLayout hlHeader = new HorizontalLayout();
    	hlHeader.setSizeFull();
    	Button btnLogout = new Button("Log-out", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				MainEventBus.post(new UserLogoutRequestedEvent());
			}
		});
    	hlHeader.addComponent(btnLogout);
    	hlHeader.setComponentAlignment(btnLogout, Alignment.TOP_RIGHT);
    	return hlHeader;
    }
    
    public HorizontalLayout generarFooter(){
    	return new HorizontalLayout();   	
    }
    
    public HorizontalLayout generarBody(){
    	HorizontalLayout bodyLayout = new HorizontalLayout();
    	VerticalLayout toolbar = generarToolbar();
    	bodyContent = new VerticalLayout();
    	
    	bodyLayout.addComponent(toolbar);
    	bodyLayout.addComponent(bodyContent);
    	
    	bodyLayout.setSizeFull();
    	
    	addComponent(bodyLayout);
    	bodyLayout.setExpandRatio(toolbar, 1);
    	bodyLayout.setExpandRatio(bodyContent, 9);
    	
    	return bodyLayout;
    }
    
    public void setupNavigator(){
    	
    }
    
    public VerticalLayout generarToolbar(){
    	VerticalLayout toolbar = new VerticalLayout();
    	toolbar.addComponent(new Button("Usuarios", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				mainNavigator.navigateTo(UsuarioView.NAME);
			}
    	  }));
    	toolbar.addComponent(new Button("Roles", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				mainNavigator.navigateTo(RolView.NAME);
			}
    	  }));    	
    	return toolbar;
    }
}