package com.taller_control.control_taller.models;

public class Liquidos {
	
	private Long id;
	
	private String tipo;
	
	private Long cantidad;
	
	private Integer precioLitro;

	public Liquidos(Long id, String tipo, Long cantidad, Integer precioLitro) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.cantidad = cantidad;
		this.precioLitro = precioLitro;
	}

	public Liquidos() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getPrecioLitro() {
		return precioLitro;
	}

	public void setPrecioLitro(Integer precioLitro) {
		this.precioLitro = precioLitro;
	}
	
	

}
