package com.example;

import com.example.modelo.Rol;
import com.example.modelo.Usuario;
import com.example.views.LoginView;
import com.example.views.MainView;
import com.example.views.RegistrarseView;
import com.example.views.rol.RolView;
import com.example.views.usuario.UsuarioView;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

public class MainNavigator extends Navigator {

    public MainNavigator(final ComponentContainer container) {
        super(UI.getCurrent(), container);
        
        // TODO: obtener las vistas del usuario de acuerdo al rol y luego agregarlas
    	this.addView(UsuarioView.NAME, new UsuarioView());
    	this.addView(RolView.NAME, new RolView());
        
	}
	
}
