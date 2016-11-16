package com.example.accesorios.usuario;

import com.vaadin.data.util.IndexedContainer;

public class ContainerUsuarios {

	public ContainerUsuarios() {
		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty("nombre", String.class, null);
		container.addContainerProperty("apellido", String.class, null);
		container.addContainerProperty("email", Integer.class, null);
		container.addContainerProperty("fechaNacimiento", Integer.class, null);
	}
}
