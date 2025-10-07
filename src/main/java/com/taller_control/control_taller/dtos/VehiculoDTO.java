package com.taller_control.control_taller.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class VehiculoDTO {
	
	private String id;
	private String matricula;
	private String marca;
	private String modelo;
	private String anio;
	private String km;
	private String valorCompra;
	private String valorVenta;
	@JsonManagedReference
	private List<ReparacionDTO> reparaciones;
	
	public VehiculoDTO(String id,String matricula, String marca, String modelo, String anio,String km,String valorCompra, String valorVenta, List<ReparacionDTO> repa) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.anio = anio;
		this.km = km;
		this.valorCompra = valorCompra;
		this.valorVenta = valorVenta;
		this.reparaciones = repa;
		
	}

	public VehiculoDTO() {
		super();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}
	
	public String getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(String valorCompra) {
		this.valorCompra = valorCompra;
	}
	
	public String getValorVenta() {
		return valorVenta;
	}
	
	public void setValorVenta(String valorVenta) {
		this.valorVenta = valorVenta;
	}

	public List<ReparacionDTO> getReparaciones() {
		return reparaciones;
	}

	public void setReparaciones(List<ReparacionDTO> reparaciones) {
		this.reparaciones = reparaciones;
	}

	public String getKm() {
		return km;
	}

	public void setKm(String km) {
		this.km = km;
	}
	
}
