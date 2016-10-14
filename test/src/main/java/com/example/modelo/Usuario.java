package com.example.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private Date fechaNacimiento;
	
	private List<Rol> roles;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
//    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @OneToMany
    @JoinTable(name="usuario_rol",
    			joinColumns=@JoinColumn(name="usuario_id"),
    			inverseJoinColumns=@JoinColumn(name="rol_id")
    )  
	public List<Rol> getRoles(){
		return this.roles;
	}
	
	public List<Rol> setRoles(List<Rol> roles){
		return this.roles = roles;
	}

	
	@Column(name = "nombre", length = 20)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "apellido", length = 20)
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@Column(name = "email", length = 30)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "fecha_nacimiento")
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public Usuario() {}
	public Usuario(String nombre, String apellido, String email, Date fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}
	
}
