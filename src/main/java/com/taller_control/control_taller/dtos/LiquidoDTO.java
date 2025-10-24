package com.taller_control.control_taller.dtos;



public class LiquidoDTO {
	
	private String id;
	private String nombre;
	private String cantidad; 
	private String precioLitro;
	
	public LiquidoDTO(String id, String nombre, String cantidad, String precioLitro) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precioLitro = precioLitro;
	}


	public LiquidoDTO() {
		super();
	}
	
	
	
	
	public String getId() {
		return id;
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
