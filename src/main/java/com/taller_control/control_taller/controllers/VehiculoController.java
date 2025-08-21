package com.taller_control.control_taller.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller_control.control_taller.models.Vehiculo;
import com.taller_control.control_taller.services.VehiculoService;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
	
	private final Logger logger = LoggerFactory.getLogger(VehiculoController.class);
	
	private final VehiculoService service;
	
	public VehiculoController(VehiculoService serv) {
		this.service = serv;
	}
	
	@GetMapping("/{id}")
	public Vehiculo obtenerVehiculoPorId(@PathVariable Long id) {
		return null;
	}

}
