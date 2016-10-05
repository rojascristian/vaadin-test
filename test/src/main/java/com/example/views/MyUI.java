package com.example.views;

import java.util.Date;
import java.util.Locale;

import javax.servlet.annotation.WebServlet;

import com.example.dao.implementations.EntityManagerFactory;
import com.example.dao.implementations.hibernate.PersonaDAOHib;
import com.example.dao.interfaces.EntityManager;
import com.example.modelo.Usuario;
import com.example.views.PersonaView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	
	Navigator navigator;
	final String MAINVIEW = "main";
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {

    	// Establecer el idioma por defecto de la UI
    	UI.getCurrent().setLocale(new Locale("es"));
    	
        getPage().setTitle("Navigation Example");
        
        // Create a navigator to control the views
        navigator = new Navigator(this, this);
        
        // Create and register the views
        navigator.addView("", new LoginView());
        navigator.addView(LoginView.NAME, new LoginView());
        navigator.addView(MainView.NAME, new MainView());
        
        navigator.navigateTo(LoginView.NAME);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}