package com.taller_control.control_taller.dtos;

import java.util.List;

public class ReparacionDTO {
	
	private String fechaInicio;
	private String fechaFin;
	private String fechaPausa;
	private String fechaReinicio;
	private String descripcion;
	private String estado;
	private List<MaterialDTO> materiales;
	private List<LiquidoDTO> liquidos;
	
	public ReparacionDTO(String fechaInicio, String fechaFin, String fechaPausa, String fechaReinicio ,String descripcion,String estado,
			List<MaterialDTO> materiales, List<LiquidoDTO> liquidos) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaPausa = fechaPausa;
		this.fechaReinicio = fechaReinicio;
		this.descripcion = descripcion;
		this.estado = estado;
		this.materiales = materiales;
		this.liquidos = liquidos;
	}

	public ReparacionDTO() {
		super();
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getFechaPausa() {
		return fechaPausa;
	}

	public void setFechaPausa(String fechaPausa) {
		this.fechaPausa = fechaPausa;
	}

	public String getFechaReinicio() {
		return fechaReinicio;
	}

	public void setFechaReinicio(String fechaReinicio) {
		this.fechaReinicio = fechaReinicio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
