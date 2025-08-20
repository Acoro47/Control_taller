package com.taller_control.control_taller.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Vehiculo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String modelo;
	
	@Column(nullable = false)
	private String marca;
	
	@Column(nullable = false, unique = true, length = 10)
	private String matricula;
	
	private int valorAdquisicion;
	
	private int valorVenta;
	
	@OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reparacion> reparaciones = new ArrayList<>();

	public Vehiculo(Long id, String modelo, String marca, String matricula, int valorAdquisicion, int valorVenta, List<Reparacion> reparaciones) {
		
		this.id = id;
		this.modelo = modelo;
		this.marca = marca;
		this.matricula = matricula;
		this.valorAdquisicion = valorAdquisicion;
		this.valorVenta = valorVenta;
		this.reparaciones = reparaciones;
	}
	
	public Vehiculo(Long id, String matricula) {
		
		this.id = id;
		this.matricula = matricula;
	}

	public Vehiculo() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getValorAdquisicion() {
		return valorAdquisicion;
	}

	public void setValorAdquisicion(int valorAdquisicion) {
		this.valorAdquisicion = valorAdquisicion;
	}
	
	public int getValorVenta() {
		return valorVenta;
	}
	
	public void setValorVenta(int valorVenta) {
		this.valorVenta = valorVenta;
	}

	public List<Reparacion> getReparaciones() {
		return reparaciones;
	}

	public void setReparaciones(List<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}
	
	
	public void addReparacion(Reparacion reparacion) {
		this.reparaciones.add(reparacion);
		reparacion.setVehiculo(this);
	}
	
	public void removeReparacion(Reparacion reparacion) {
		this.reparaciones.remove(reparacion);
		reparacion.setVehiculo(null);
	}
	
	
}
