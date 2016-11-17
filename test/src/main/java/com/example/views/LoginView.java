package com.example.views;

import com.example.event.MainEvent.UserLoginRequestedEvent;
import com.example.event.MainEventBus;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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
		
//		Button btnRegistrarse = new Button("Registrarse");
//		loginForm.addComponent(btnRegistrarse);
		
//		btnLogin.addClickListener(e -> {
//			UI.getCurrent().getNavigator().navigateTo(MainView.NAME);
//		});
		
		btnLogin.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
            public void buttonClick(final ClickEvent event) {
                MainEventBus.post(new UserLoginRequestedEvent(tfUsuario.getValue(), pfPassword.getValue()));
            }
        });
		
//		btnRegistrarse.addClickListener(e -> {
//			UI.getCurrent().getNavigator().navigateTo(RegistrarseView.NAME);
//		});
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
