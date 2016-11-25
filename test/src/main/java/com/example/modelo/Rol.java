package com.example.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol {

	private Long id;
//	private long id;
	private String descripcion;
	private String nombreVista;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(name = "nombre_vista")
	public String getNombreVista(){
		return nombreVista;
	}
	
	public void setNombreVista(String nombreVista){
		this.nombreVista = nombreVista;
	}
	
	public Rol() {}

	public boolean equals(Object o){
		if(o == null)                return false;
//		if(!(o instanceof) Rol) return false;
	
		Rol other = (Rol) o;
		return this.id == other.id;
	}
	
	public int hashCode(){
		return (int) id.intValue();
	}
	
}
