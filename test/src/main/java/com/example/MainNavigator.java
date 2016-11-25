package com.example;

import java.lang.reflect.Constructor;

import com.example.modelo.Rol;
import com.example.modelo.Usuario;
import com.example.util.MenuViewType;
import com.example.views.rol.RolView;
import com.example.views.usuario.UsuarioView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

public class MainNavigator extends Navigator {

    public MainNavigator(final ComponentContainer container) {
        super(UI.getCurrent(), container);
        
        Usuario usuario = (Usuario) VaadinSession.getCurrent().getAttribute(Usuario.class.getName());

        try {
        	for(Rol rol: usuario.getRoles()){
        		MenuViewType view = MenuViewType.getByViewName(rol.getNombreVista());
        		Class<?> clazz = Class.forName(view.getViewClass().getName());
        		View instance = (View)clazz.newInstance();
        		this.addView(rol.getNombreVista(), instance);        	
        	}
        } catch(Exception ex){
        	System.out.println(ex.getMessage());
        }
	}
	
}
