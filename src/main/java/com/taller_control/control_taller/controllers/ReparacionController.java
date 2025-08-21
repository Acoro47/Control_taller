package com.taller_control.control_taller.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.services.ReparacionService;

@RestController
@RequestMapping("/api/reparaciones")
public class ReparacionController {
	
	private final Logger logger = LoggerFactory.getLogger(ReparacionController.class);
	
	private final ReparacionService service;
	
	public ReparacionController(ReparacionService serv) {
		this.service = serv;
	}
	
	@PostMapping
	public Reparacion crear(@RequestBody Reparacion repa) {
		logger.info("Guardando reparación desde el ReparacionController");
		return service.guardarReparacion(repa);
	}
	
	@GetMapping("/{id}")
	public Reparacion obtenerReparación(@PathVariable Long id) {
		return service.buscarPorId(id);
	}
	
	@GetMapping
	public List<Reparacion> listar(){
		return service.listarReparaciones();
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		service.eliminarReparacion(id);
	}

}
