package com.taller_control.control_taller.dtos;

import java.math.BigDecimal;

public class MaterialDTO {
	
	private String nombre;
	private BigDecimal coste;
	public MaterialDTO(String nombre, BigDecimal coste) {
		super();
		this.nombre = nombre;
		this.coste = coste;
	}
	public MaterialDTO() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigDecimal getCoste() {
		return coste;
	}
	public void setCoste(BigDecimal coste) {
		this.coste = coste;
	}
	
	

}
