package com.taller_control.control_taller.dtos;

import java.util.List;

public class VehiculoDTO {
	
	private String matricula;
	private String marca;
	private String modelo;
	private Integer anio;
	private List<ReparacionDTO> reparaciones;
	
	public VehiculoDTO(String matricula, String marca, String modelo, Integer anio, List<ReparacionDTO> repa) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.anio = anio;
		this.reparaciones = repa;
	}

	public VehiculoDTO() {
		super();
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

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public List<ReparacionDTO> getReparaciones() {
		return reparaciones;
	}

	public void setReparaciones(List<ReparacionDTO> reparaciones) {
		this.reparaciones = reparaciones;
	}
	
	
	
		

}
