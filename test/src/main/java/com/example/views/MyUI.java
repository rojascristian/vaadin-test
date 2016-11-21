package com.example.views;

import java.util.Date;
import java.util.Locale;

import javax.servlet.annotation.WebServlet;

import com.example.event.MainEvent.UserLoginRequestedEvent;
import com.example.event.MainEvent.UserLogoutRequestedEvent;
import com.example.dao.implementations.EntityManagerFactory;
import com.example.dao.implementations.hibernate.UsuarioDAOHib;
import com.example.dao.interfaces.EntityManager;
import com.example.event.MainEventBus;
import com.example.modelo.Usuario;
import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	
	private final MainEventBus mainEventbus = new MainEventBus();
	private EntityManager em = EntityManagerFactory.getInstance(EntityManagerFactory.MYSQL);
	
	final String MAINVIEW = "main";
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {

    	// Establecer el idioma por defecto de la UI
    	UI.getCurrent().setLocale(new Locale("es"));
    	
        getPage().setTitle("Navigation Example");
        
        MainEventBus.register(this);
        
        actualizarContenido();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
    private void actualizarContenido(){
    	Usuario usuario = obtenerUsuario();
    	if(usuario != null){
    		setContent(new MainView());
    	} else {
    		setContent(new LoginView());
    	}
    }
    
    private Usuario obtenerUsuario(){
        Usuario usuario = (Usuario) VaadinSession.getCurrent().getAttribute(Usuario.class.getName());
        return usuario;
    }
    
    @Subscribe
    public void userLoginRequested(final UserLoginRequestedEvent event) {
    	em.beginTransaction();
    	UsuarioDAOHib pdh = new UsuarioDAOHib();
    	Usuario usuario = pdh.findByEmail(event.getEmail());
    	
        VaadinSession.getCurrent().setAttribute(Usuario.class.getName(), usuario);
    	actualizarContenido();
    }
    
    @Subscribe
    public void userLogoutRequested(final UserLogoutRequestedEvent event){
    	VaadinSession.getCurrent().close();
    	Page.getCurrent().reload();
    }
    
    public static MainEventBus getMainEventbus() {
        return ((MyUI) getCurrent()).mainEventbus;
    }
}
