package com.example.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout implements View {

	private static final long serialVersionUID = 6228556386923863151L;
	public static String NAME = "login";

	public LoginView() {
		FormLayout loginForm = new FormLayout();
		addComponent(loginForm);
		
		TextField tfUsuario = new TextField("Usuario: ");
		tfUsuario.setRequired(true);
		loginForm.addComponent(tfUsuario);
		
		PasswordField pfPassword = new PasswordField("Contraseña :");
		pfPassword.setRequired(true);
		pfPassword.setNullRepresentation("");
		loginForm.addComponent(pfPassword);
		
		Button btnLogin = new Button("Login");
		loginForm.addComponent(btnLogin);
		
		btnLogin.addClickListener(e -> {
			UI.getCurrent().getNavigator().navigateTo(MainView.NAME);
		});
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
