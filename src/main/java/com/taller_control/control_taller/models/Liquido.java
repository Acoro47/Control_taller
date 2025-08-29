package com.taller_control.control_taller.models;



import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "liquidos")
public class Liquido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	@Positive(message = "La cantidad debe ser mayor que cero")
	private Float cantidad;
	
	@PositiveOrZero(message = "El precio por litro no puede ser negativo")
	private Float precioLitro;
	
	@ManyToOne
	@JoinColumn(name = "reparacion_id")
	@JsonBackReference
	private Reparacion reparacion;

	public Liquido(Long id, String nombre, Float cantidad, Float precioLitro, Reparacion reparacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precioLitro = precioLitro;
		this.reparacion = reparacion;
	}

	public Liquido() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getCantidad() {
		return cantidad;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

	public Float getPrecioLitro() {
		return precioLitro;
	}

	public void setPrecioLitro(Float precioLitro) {
		this.precioLitro = precioLitro;
	}

	public Reparacion getReparacion() {
		return reparacion;
	}

	public void setReparacion(Reparacion reparacion) {
		this.reparacion = reparacion;
	}


}
