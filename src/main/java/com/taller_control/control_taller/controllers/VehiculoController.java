package com.taller_control.control_taller.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Vehiculo;
import com.taller_control.control_taller.services.VehiculoService;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
	
	
	private final VehiculoService vService;
	
	public VehiculoController(VehiculoService serV) {
		this.vService = serV;
	}
	
		
	@GetMapping("/{id}")
	public ResponseEntity<VehiculoDTO> obtenerVehiculo(@PathVariable Long id){
		Vehiculo v = vService.buscarVehiculoPorId(id);
		if (v == null) return ResponseEntity.notFound().build();
		
		VehiculoDTO vDto = vService.mapearEntidadVehiculo(v);
		return ResponseEntity.ok(vDto);
	}

}
