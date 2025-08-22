package com.taller_control.control_taller.dtos;

import java.math.BigDecimal;

public class LiquidoDTO {
	
	private String nombre;
	private BigDecimal precioLitro;
	public LiquidoDTO(String nombre, BigDecimal precioLitro) {
		super();
		this.nombre = nombre;
		this.precioLitro = precioLitro;
	}
	public LiquidoDTO() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigDecimal getPrecioLitro() {
		return precioLitro;
	}
	public void setPrecioLitro(BigDecimal precioLitro) {
		this.precioLitro = precioLitro;
	}
	
	

}
