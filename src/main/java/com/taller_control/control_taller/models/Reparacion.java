package com.taller_control.control_taller.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Reparacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private LocalDateTime fechaInicio;
	
	private LocalDateTime fechaFin;
	
	private LocalDateTime fechaPausa;
	
	private LocalDateTime fechaReinicio;
	
	private Materiales materiales;
	
	private Liquidos liquidos;
	
	private String descripcion;
	
	private String totalHoras;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@ManyToOne
	private Vehiculo vehiculo;

	public Reparacion(Long id, LocalDateTime fechaInicio, LocalDateTime fechaFin, String descripcion,
			String totalHoras, Estado estado, Vehiculo vehiculo) {
		super();
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
		this.totalHoras = totalHoras;
		this.estado = estado;
		this.vehiculo = vehiculo;
	}

	public Reparacion() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTotalHoras() {
		return totalHoras;
	}

	public void setTotalHoras(String totalHoras) {
		this.totalHoras = totalHoras;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	

}
