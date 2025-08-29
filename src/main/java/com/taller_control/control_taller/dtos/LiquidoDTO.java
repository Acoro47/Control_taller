package com.taller_control.control_taller.dtos;



public class LiquidoDTO {
	
	private String nombre;
	private String cantidad; 
	private String precioLitro;
	
	public LiquidoDTO(String nombre, String cantidad, String precioLitro) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
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
	
	public String getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getPrecioLitro() {
		return precioLitro;
	}
	
	public void setPrecioLitro(String precioLitro) {
		this.precioLitro = precioLitro;
	}
	
	

}
