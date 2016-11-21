package com.example.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class RegistrarseView extends VerticalLayout implements View {
	private static final long serialVersionUID = -5261963818080111454L;
	public static String NAME = "registrarse";
	private TextField tfUsuario;
	private TextField tfEmail;
	private PasswordField pfPassword;
	private PasswordField pfConfirmarPassword;
	private Button btnRegistrarse;
	private FormLayout registrarseForm;
	
	public RegistrarseView() {
		registrarseForm = new FormLayout();
		addComponent(registrarseForm);
		
		tfUsuario = new TextField("Usuario: ");
		tfUsuario.setRequired(true);
		registrarseForm.addComponent(tfUsuario);
		
		tfEmail = new TextField("Email: ");
		tfEmail.setRequired(true);
		registrarseForm.addComponent(tfEmail);
		
		pfPassword = new PasswordField("Contraseña :");
		pfPassword.setRequired(true);
		pfPassword.setNullRepresentation("");
		registrarseForm.addComponent(pfPassword);
		
		pfConfirmarPassword = new PasswordField("Confirmar contraseña:");
		pfConfirmarPassword.setRequired(true);
		pfConfirmarPassword.setNullRepresentation("");
		registrarseForm.addComponent(pfConfirmarPassword);
		
		btnRegistrarse = new Button("Registrarse");
		registrarseForm.addComponent(btnRegistrarse);
		
		btnRegistrarse.addClickListener(e -> {
			validarDatos();
		});
	}
	
    private void validarDatos() {
    	if(coincidenPassword() && mailValido()){
    	}
	}

	private boolean mailValido() {
		return true;
	}

	private boolean coincidenPassword() {
		return pfPassword.getValue() == pfConfirmarPassword.getValue();
	}

	@Override
    public void enter(ViewChangeEvent event) {
    	
    }
}