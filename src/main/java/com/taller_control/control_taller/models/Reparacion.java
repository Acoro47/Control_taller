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
	
	private LocalDateTime fecha_inicio;
	
	private LocalDateTime fecha_fin;
	
	private String descripcion;
	
	private long total_horas;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@ManyToOne
	private Vehiculo vehiculo;

}
