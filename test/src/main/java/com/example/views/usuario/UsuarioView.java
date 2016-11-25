package com.example.views.usuario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.example.dao.implementations.EntityManagerFactory;
import com.example.dao.implementations.hibernate.UsuarioDAOHib;
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
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Grid.SingleSelectionModel;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.DateRenderer;

public class UsuarioView extends VerticalLayout implements View {
	
	private static final String DATEFORMAT = "%1$td/%1$tm/%1$tY";
	
	private static final long serialVersionUID = 358082736884749316L;
	public static String NAME = "usuario";
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
	private HeaderRow filterRow;
	
	public UsuarioView(){
		
		addComponent(generarVista());
		configurarAcciones();
		updateGrid();
		
	}

	private void updateGrid() {
		em.beginTransaction();
		UsuarioDAOHib pdh = new UsuarioDAOHib();
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
	
	public VerticalLayout generarVista(){
		mainContent = new VerticalLayout();
		mainContent.setSizeFull();
		
		mainContent.addComponent(generarComponentesFiltros());
		mainContent.addComponent(generarComponentesBody());
		mainContent.addComponent(generarComponentesAcciones());
		
		return mainContent;
	}
	
	public HorizontalLayout generarComponentesFiltros(){
		panelFiltros = new Panel("Filtros");
		
		FormLayout content = new FormLayout();
		tfNombre = new TextField("Nombre");
		tfNombre.setNullSettingAllowed(true);
		tfNombre.setNullRepresentation("");
		content.addComponent(tfNombre);
		
		tfApellido = new TextField("Apellido");
		tfApellido.setNullSettingAllowed(true);
		tfApellido.setNullRepresentation("");
		content.addComponent(tfApellido);
		
		tfEmail = new TextField("Email");
		tfEmail.setNullSettingAllowed(true);
		tfEmail.setNullRepresentation("");
		content.addComponent(tfEmail);
		
		dfFechaNacimiento = new DateField("Fecha Nacimiento");
		content.addComponent(dfFechaNacimiento);
		
		content.setSizeUndefined();
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
		
		return new HorizontalLayout(panelFiltros);
	}
	
	public HorizontalLayout generarComponentesBody(){
		
		HorizontalLayout hlGrid = new HorizontalLayout();
		hlGrid.setSizeFull();
		
		grid = new Grid();
		grid.addColumn("nombre");
		grid.addColumn("apellido");
		grid.addColumn("email");
		grid.addColumn("fechaNacimiento");
		
		hlGrid.setMargin(true);
		hlGrid.setSpacing(true);
		hlGrid.addComponent(grid);
		hlGrid.setExpandRatio(grid, 1);
		
		return hlGrid;
	}
	
	public HorizontalLayout generarComponentesAcciones(){
    	hlAccionesGrid = new HorizontalLayout();
    	btnNuevo = new Button("Nuevo");
    	btnModificar = new Button("Modificar");
    	btnBorrar = new Button("Borrar");
    	btnModificar.setEnabled(false);
    	btnBorrar.setEnabled(false);
    	hlAccionesGrid.addComponents(btnNuevo, btnModificar, btnBorrar);
    	hlAccionesGrid.setSpacing(true);
    	hlAccionesGrid.setMargin(true);
    	
    	return hlAccionesGrid;
	}
	
	public void configurarAcciones(){
		btnBuscar.addClickListener(e -> {
			em.beginTransaction();
			UsuarioDAOHib pdh = new UsuarioDAOHib();
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
    		Usuario usuarioSeleccionado = (Usuario) ((SingleSelectionModel) grid.getSelectionModel()).getSelectedRow();
    		pams = new UsuarioAltaModificarSub("Modificar usuario", usuarioSeleccionado);
    		
    		pams.setWidth("500px");
    		pams.setHeight("400px");
    		UI.getCurrent().addWindow(pams);
    		pams.addCloseListener(event -> {
    			updateGrid();
    		});
    	});
    	
    	btnBorrar.addClickListener(e -> {
    		em.beginTransaction();
    		UsuarioDAOHib pdh = new UsuarioDAOHib();
    		Usuario personaGrid = (Usuario)grid.getSelectedRow();
    		Usuario usuario = pdh.findById(personaGrid.getId());
    		pdh.delete(usuario);
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
	}

}
