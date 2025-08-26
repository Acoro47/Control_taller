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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "vehiculos", uniqueConstraints = {
		@UniqueConstraint(columnNames = "matricula")
})
public class Vehiculo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank(message = "La marca no puede estar vacía")
	private String modelo;
	
	@Column(nullable = false)
	@NotBlank(message = "La marca no puede estar vacía")
	private String marca;
	
	@Column(nullable = false, unique = true, length = 10)
	@NotBlank(message = "La matrícula no puede estar vacía")
	@Size(max = 10, message = "La matrícula no puede tener mas de 10 carácteres")
	private String matricula;
	
	private Integer anio;
	
	private Float km;
	
	@Min(value = 0, message = "El valor de compra debe ser positivo")
	private Integer valorAdquisicion;
	
	@Min(value = 0, message = "El valor de venta debe ser positivo")
	private Integer valorVenta;
	
	@OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reparacion> reparaciones = new ArrayList<>();

	public Vehiculo(Long id, String modelo, String marca, String matricula, Integer anio, Float km, Integer valorAdquisicion, Integer valorVenta, List<Reparacion> reparaciones) {
		
		this.id = id;
		this.modelo = modelo;
		this.marca = marca;
		this.matricula = matricula;
		this.valorAdquisicion = valorAdquisicion;
		this.valorVenta = valorVenta;
		this.reparaciones = reparaciones;
		this.anio = anio;
		this.km = km;
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
		this.modelo = modelo.toUpperCase();
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca.toUpperCase();;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer año) {
		this.anio = año;
	}

	public Float getKm() {
		return km;
	}

	public void setKm(Float km) {
		this.km = km;
	}

	public Integer getValorAdquisicion() {
		return valorAdquisicion;
	}

	public void setValorAdquisicion(Integer valorAdquisicion) {
		this.valorAdquisicion = valorAdquisicion;
	}
	
	public Integer getValorVenta() {
		return valorVenta;
	}
	
	public void setValorVenta(Integer valorVenta) {
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
