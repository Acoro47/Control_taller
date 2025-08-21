package com.taller_control.control_taller.models;

public class Materiales {
	
	private Long id;
	
	private String nombre;
	
	private Integer coste;

	public Materiales(Long id, String nombre, Integer coste) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.coste = coste;
	}

	public Materiales() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCoste() {
		return coste;
	}

	public void setCoste(Integer coste) {
		this.coste = coste;
	}
	
	

}
