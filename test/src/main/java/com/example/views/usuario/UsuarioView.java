package com.example.views.usuario;

import com.example.dao.implementations.EntityManagerFactory;
import com.example.dao.implementations.hibernate.PersonaDAOHib;
import com.example.dao.interfaces.EntityManager;
import com.example.modelo.Usuario;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SingleSelectionModel;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class UsuarioView extends VerticalLayout implements View {
	private static final long serialVersionUID = 358082736884749316L;
	public static String NAME = "persona";
	private VerticalLayout mainContent;
	
	private Panel panelFiltros;
	
	private TextField tfNombre;
	private TextField tfApellido;
	private TextField tfEmail;
	private DateField dfFechaNacimiento;
	private Button btnBuscar;
	private Button btnLimpiar;
	
	private Grid grid;
	
	private UsuarioAltaModificarSub pams;

	private HorizontalLayout hlAccionesGrid;
	private Button btnNuevo;
	private Button btnModificar;

	private Button btnBorrar;

	private EntityManager em = EntityManagerFactory.getInstance(EntityManagerFactory.MYSQL);
	
	public UsuarioView(){
		
		mainContent = new VerticalLayout();
		mainContent.setSizeFull();
		
		grid = new Grid();
		
		panelFiltros = new Panel("Filtros");
		
		// Create the content
		FormLayout content = new FormLayout();
		tfNombre = new TextField("Nombre");
		tfApellido = new TextField("Apellido");
		tfEmail = new TextField("Email");
		dfFechaNacimiento = new DateField("Fecha Nacimiento");
		content.addComponents(tfNombre, tfApellido, tfEmail, dfFechaNacimiento);
		content.setSizeUndefined(); // Shrink to fit
		content.setMargin(true);
		btnBuscar = new Button("Buscar");
		btnLimpiar = new Button("Limpiar");
		content.addComponent(btnBuscar);
		HorizontalLayout hlAccionesFiltros = new HorizontalLayout();
		hlAccionesFiltros.addComponents(btnBuscar, btnLimpiar);
		hlAccionesFiltros.setSpacing(true);
		content.addComponent(hlAccionesFiltros);
		content.setComponentAlignment(hlAccionesFiltros, Alignment.BOTTOM_RIGHT);
		panelFiltros.setContent(content);
		
		btnBuscar.addClickListener(e -> {
			em.beginTransaction();
			PersonaDAOHib pdh = new PersonaDAOHib();
			Usuario persona = new Usuario();
			persona.setNombre(tfNombre.getValue());
			persona.setApellido(tfApellido.getValue());
			persona.setEmail(tfEmail.getValue());
			persona.setFechaNacimiento(dfFechaNacimiento.getValue());
	    	BeanItemContainer<Usuario> personaData = new BeanItemContainer<Usuario>(Usuario.class, pdh.find(persona));
	    	grid.setContainerDataSource(personaData);
		});
		
		btnLimpiar.addClickListener(e ->{
			limpiarFiltros();
		});
		
		mainContent.addComponent(panelFiltros);
		
		HorizontalLayout hlGrid = new HorizontalLayout();
		hlGrid.setSizeFull();
		grid.setColumns("id", "nombre", "apellido", "email", "fechaNacimiento");
		hlGrid.setMargin(true);
		hlGrid.setSpacing(true);
		hlGrid.addComponent(grid);
		hlGrid.setExpandRatio(grid, 1);
		
    	updateGrid();
    	
    	mainContent.addComponent(hlGrid);
    	
    	hlAccionesGrid = new HorizontalLayout();
    	btnNuevo = new Button("Nuevo");
    	btnModificar = new Button("Modificar");
    	btnBorrar = new Button("Borrar");
    	btnModificar.setEnabled(false);
    	btnBorrar.setEnabled(false);
    	hlAccionesGrid.addComponents(btnNuevo, btnModificar, btnBorrar);
    	hlAccionesGrid.setSpacing(true);
    	hlAccionesGrid.setMargin(true);
    	mainContent.addComponent(hlAccionesGrid);
    	
    	btnNuevo.addClickListener(e -> {
    		pams = new UsuarioAltaModificarSub("Alta de usuario", new Usuario());
    		pams.setWidth("400px");
    		pams.setHeight("400px");
    		UI.getCurrent().addWindow(pams);
    		pams.addCloseListener(event -> {
    			updateGrid();
    		});
    	});
    	
    	btnModificar.addClickListener(e -> {
//    		pams = new UsuarioAltaModificarSub("Modificar persona");
    		Usuario usuarioSeleccionado = (Usuario) ((SingleSelectionModel) grid.getSelectionModel()).getSelectedRow();
    		pams = new UsuarioAltaModificarSub("Modificar usuario", usuarioSeleccionado);
    		pams.setWidth("400px");
    		pams.setHeight("400px");
    		UI.getCurrent().addWindow(pams);
    		pams.addCloseListener(event -> {
    			updateGrid();
    		});
    	});
    	
    	btnBorrar.addClickListener(e -> {
    		em.beginTransaction();
    		PersonaDAOHib pdh = new PersonaDAOHib();
    		Usuario persona = (Usuario)grid.getSelectedRow();
    		pdh.delete(persona);
    		em.commit();
    		updateGrid();
    	});
    	
    	grid.addSelectionListener(e -> {
    	    // Get selection from the selection model
    	    Object selected = ((SingleSelectionModel) grid.getSelectionModel()).getSelectedRow();
    	    if (selected != null) {
    	    	btnModificar.setEnabled(true);
    	    	btnBorrar.setEnabled(true);
    	    } else {
    	    	btnModificar.setEnabled(false);
    	    	btnBorrar.setEnabled(false);
    	    }
    	});
		
		addComponent(mainContent);
	}


	private void updateGrid() {
		em.beginTransaction();
		PersonaDAOHib pdh = new PersonaDAOHib();
    	BeanItemContainer<Usuario> personaData = new BeanItemContainer<Usuario>(Usuario.class, pdh.findAll());
    	grid.setContainerDataSource(personaData);
	}


	private void limpiarFiltros() {
		tfNombre.setValue("");
		tfApellido.setValue("");
		tfEmail.setValue("");
		dfFechaNacimiento.setValue(null);
	}


	@Override
	public void enter(ViewChangeEvent event) {
		getUI().getNavigator().navigateTo(UsuarioView.NAME);
	}

}
