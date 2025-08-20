package com.taller_control.control_taller.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
	
	private int valor_adquisicion;
	
	private int valor_venta; 

	public Vehiculo(Long id, String modelo, String marca, String matricula, int valor_adquisicion, int valor_venta) {
		
		this.id = id;
		this.modelo = modelo;
		this.marca = marca;
		this.matricula = matricula;
		this.valor_adquisicion = valor_adquisicion;
		this.valor_venta = valor_venta;
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

	public int getValor_adquisicion() {
		return valor_adquisicion;
	}

	public void setValor_adquisicion(int valor_adquisicion) {
		this.valor_adquisicion = valor_adquisicion;
	}
	
	public int getValor_venta() {
		return valor_venta;
	}
	
	public void setValor_venta(int valor_venta) {
		this.valor_venta = valor_venta;
	}
}
