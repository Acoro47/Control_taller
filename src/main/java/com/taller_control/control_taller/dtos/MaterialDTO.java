package com.taller_control.control_taller.dtos;


public class MaterialDTO {
	
	private String id;
	private String nombre;
	private String coste;
	
	public MaterialDTO(String id, String nombre, String coste) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.coste = coste;
	}
	
	public MaterialDTO() {
		super();
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCoste() {
		return coste;
	}
	public void setCoste(String coste) {
		this.coste = coste;
	}
	
	

}
