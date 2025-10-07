package com.taller_control.control_taller.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class ReparacionDTO {
	
	private String id;
	private String matricula;
	private String fechaInicio;
	private String fechaFin;
	private String fechaInicioPausa;
	private String fechaFinPausa;
	private String descripcion;
	private String estado;
	private String fechaCreacion;
	private List<MaterialDTO> materiales;
	private List<LiquidoDTO> liquidos;
	private VehiculoDTO vDto;
	private String totalHoras;
	
	public ReparacionDTO(String id, String matricula, String fechaInicio, String fechaFin, String fechaInicioPausa, String fechaFinPausa ,String descripcion,String estado, String createdAt,
			List<MaterialDTO> materiales, List<LiquidoDTO> liquidos, VehiculoDTO dtoV, String totalHoras) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaInicioPausa = fechaInicioPausa;
		this.fechaFinPausa = fechaFinPausa;
		this.descripcion = descripcion;
		this.estado = estado;
		this.fechaCreacion = createdAt;
		this.materiales = materiales;
		this.liquidos = liquidos;
		this.vDto = dtoV;
		this.totalHoras = totalHoras;
	}

	public ReparacionDTO() {
		super();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public String getFechaInicioPausa() {
		return fechaInicioPausa;
	}

	public void setFechaInicioPausa(String fechaInicioPausa) {
		this.fechaInicioPausa = fechaInicioPausa;
	}

	public String getFechaFinPausa() {
		return fechaFinPausa;
	}

	public void setFechaFinPausa(String fechaFinPausa) {
		this.fechaFinPausa = fechaFinPausa;
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

	public VehiculoDTO getVdto() {
		return vDto;
	}

	@JsonBackReference
	public void setVdto(VehiculoDTO vDto) {
		this.vDto = vDto;
	}
	
	public void setTotalHoras(String horas) {
		this.totalHoras = horas;
	}
	
	public String getTotalHoras() {
		return totalHoras;
	}

	
	
	
	
}
