package com.taller_control.control_taller.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class ReparacionDTO {
	
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private String descripcion;
	private List<MaterialDTO> materiales;
	private List<LiquidoDTO> liquidos;
	
	
	public ReparacionDTO(LocalDateTime fechaInicio, LocalDateTime fechaFin, String descripcion, List<MaterialDTO> materials, List<LiquidoDTO> liquids) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
		this.materiales = materials;
		this.liquidos = liquids;
	}
	public ReparacionDTO() {
		super();
	}
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDateTime getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<MaterialDTO> getMateriales() {
		return materiales;
	}
	public void setMateriales(List<MaterialDTO> materiales) {
		this.materiales = materiales;
	}
	public List<LiquidoDTO> getLiquidos() {
		return liquidos;
	}
	public void setLiquidos(List<LiquidoDTO> liquidos) {
		this.liquidos = liquidos;
	}
	

}
