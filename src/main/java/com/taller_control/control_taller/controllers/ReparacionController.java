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
import com.taller_control.control_taller.services.VehiculoServiceImpl;

@RestController
@RequestMapping("/api/reparaciones")
public class ReparacionController {
	
	private final Logger logger = LoggerFactory.getLogger(ReparacionController.class);
	
	private final ReparacionServiceImpl rService;
	private final VehiculoServiceImpl vService;
	
	public ReparacionController(ReparacionServiceImpl serv, VehiculoServiceImpl vehiculoServ) {
		this.rService = serv;
		this.vService = vehiculoServ;
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<ReparacionDTO> registrarReparacion(
			@RequestBody ReparacionDTO dto) {
		logger.info("Guardando reparación desde el ReparacionController");
		VehiculoDTO vDto = vService.buscarPorMatricula(dto.getMatricula());
		logger.info("Buscando vehiculo: {}", vDto);
		if (vDto == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"El vehiculo no existe");
		dto.setVdto(vDto);
		logger.info("Añadiendo vehiculo a reparacion: {}", vDto);
		Vehiculo v = vService.mapearDTOAVehiculo(vDto);
		Reparacion repa = rService.mapearDTOAReparacion(dto);
		repa.setVehiculo(v);
		Reparacion guardada = rService.crearReparacion(repa);
		dto.setId(guardada.getId().toString());
		
		return ResponseEntity.ok(dto);
	}
	
	
	@GetMapping("/{id}")
	public Reparacion obtenerReparación(@PathVariable Long id) {
		return rService.buscarPorId(id);
	}
	
	@GetMapping
	public List<Reparacion> listar(){
		return rService.listarReparaciones();
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		rService.eliminarReparacion(id);
	}

}
