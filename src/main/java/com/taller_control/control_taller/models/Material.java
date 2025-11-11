package com.taller_control.control_taller.models;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "materiales")
public class Material {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "El nombre no puede estar vacío")
	private String nombre;
	
	@PositiveOrZero(message = "El coste no puede ser negativo")
	private Float coste;
	
	@ManyToMany(mappedBy="materiales")
	@JsonBackReference
	private List<Reparacion> reparaciones = new ArrayList<>();

	public Material(Long id, String nombre, Float coste, List<Reparacion> reparaciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.coste = coste;
		this.reparaciones = reparaciones;
	}

	
	public Material() {
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

	public Float getCoste() {
		return coste;
	}

	public void setCoste(Float coste) {
		this.coste = coste;
	}

	public List<Reparacion> getReparaciones() {
		return reparaciones;
	}

	public void setReparaciones(List<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}


}
