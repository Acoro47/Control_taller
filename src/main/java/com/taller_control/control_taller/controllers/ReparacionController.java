package com.taller_control.control_taller.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.models.Vehiculo;
import com.taller_control.control_taller.services.ReparacionServiceImpl;
import com.taller_control.control_taller.services.VehiculoReparacionServiceImpl;
import com.taller_control.control_taller.services.VehiculoServiceImpl;

@RestController
@RequestMapping("/api/reparaciones")
public class ReparacionController {
	
	private final Logger logger = LoggerFactory.getLogger(ReparacionController.class);
	
	private final ReparacionServiceImpl rService;
	private final VehiculoServiceImpl vService;
	private final VehiculoReparacionServiceImpl vehiculoReparacionService;
	
	public ReparacionController(ReparacionServiceImpl serv, VehiculoServiceImpl vehiculoServ,VehiculoReparacionServiceImpl vehiculoReparacionService ) {
		this.rService = serv;
		this.vService = vehiculoServ;
		this.vehiculoReparacionService = vehiculoReparacionService;
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<ReparacionDTO> registrarReparacion(
			@RequestBody ReparacionDTO dto) {
		
		ReparacionDTO rDto = vehiculoReparacionService.agregarVehiculoAReparacion(dto);
		
				
		return ResponseEntity.ok(null);
	}
	
	
	@GetMapping("/{id}")
	public Reparacion obtenerReparación(@PathVariable Long id) {
		//return rService.buscarPorId(id);
		return null;
	}
	
	@GetMapping("/{matricula}")
	public List<ReparacionDTO> listarReparacionesPorMatricula(@PathVariable String matricula) {
		return rService.buscarPorVehiculo(matricula);
	}
	
	@GetMapping("/all")
	public List<ReparacionDTO> listar(){
		List<ReparacionDTO> reparacionesDTO = rService.listarReparaciones();
		
		return rService.listarReparaciones();
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		rService.eliminarReparacion(id);
	}

}
