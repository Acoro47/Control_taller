package com.taller_control.control_taller.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "reparaciones")
public class Reparacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TIMESTAMP")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fechaInicio;
	
	@Column(columnDefinition = "TIMESTAMP")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fechaFin;
	
	@Column(columnDefinition = "TIMESTAMP")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fechaInicioPausa;
	
	@Column(columnDefinition = "TIMESTAMP")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fechaFinPausa;
	
	private String descripcion;
	
	private Double totalHoras;	
	
	@OneToMany(mappedBy = "reparacion", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Material> materiales = new ArrayList<>();
	
	@OneToMany(mappedBy = "reparacion", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Liquido> liquidos = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.PENDIENTE;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "vehiculo_id")
	@NotNull(message = "El vehiculo es obligatorio")
	@JsonBackReference
	private Vehiculo vehiculo;
	
	private LocalDateTime fechaCreacion;

	public Reparacion(Long id, LocalDateTime fechaInicio, LocalDateTime fechaFin, LocalDateTime fechaInicioPausa,
			LocalDateTime fechaFinPausa, String descripcion, Double totalHoras, List<Material> materiales,
			List<Liquido> liquidos, Estado estado, Vehiculo vehiculo, LocalDateTime createAt) {
		super();
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaInicioPausa = fechaInicioPausa;
		this.fechaFinPausa = fechaFinPausa;
		this.descripcion = descripcion;
		this.totalHoras = totalHoras;
		this.materiales = materiales != null ? materiales : new ArrayList<>();
		this.liquidos = liquidos != null ? liquidos : new ArrayList<>();
		this.estado = estado;
		this.vehiculo = vehiculo;
		this.fechaCreacion = createAt;
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

	public LocalDateTime getFechaInicioPausa() {
		return fechaInicioPausa;
	}

	public void setFechaInicioPausa(LocalDateTime fechaPausa) {
		this.fechaInicioPausa = fechaPausa;
	}

	public LocalDateTime getFechaFinPausa() {
		return fechaFinPausa;
	}

	public void setFechaFinPausa(LocalDateTime fechaReinicio) {
		this.fechaFinPausa = fechaReinicio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getTotalHoras() {
		return totalHoras;
	}

	public void setTotalHoras(Double totalHoras) {
		this.totalHoras = totalHoras;
	}

	public List<Material> getMateriales() {
		return materiales;
	}

	public void setMateriales(List<Material> materiales) {
		this.materiales = materiales;
	}

	public List<Liquido> getLiquidos() {
		return liquidos;
	}

	public void setLiquidos(List<Liquido> liquidos) {
		this.liquidos = liquidos;
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
	
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	
		
	
	// Mantener la integridad de las relaciones Material y Liquido
	
	
	

	public void addMaterial(Material material) {
		materiales.add(material);
		material.setReparacion(this);
	}
	
	public void removeMaterial(Material material) {
		materiales.remove(material);
		material.setReparacion(null);
	}
	
	public void addLiquido(Liquido liquido) {
		liquidos.add(liquido);
		liquido.setReparacion(this);
	}
	
	public void removeLiquido(Liquido liquido) {
		liquidos.remove(liquido);
		liquido.setReparacion(null);
	}
	
}
