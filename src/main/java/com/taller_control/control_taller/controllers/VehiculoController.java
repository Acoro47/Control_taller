package com.taller_control.control_taller.controllers;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Vehiculo;
import com.taller_control.control_taller.services.VehiculoService;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
	
	private Logger logger = LoggerFactory.getLogger(VehiculoController.class);
	private final VehiculoService vService;
	
	public VehiculoController(VehiculoService serV) {
		this.vService = serV;
	}
	
	
	@PostMapping("/registrar")
	public ResponseEntity<VehiculoDTO> registrarCoche(
			@RequestBody VehiculoDTO vDto) {
		logger.info("Registrando vehiculo nuevo: {}", vDto.getMatricula());
		
		Vehiculo v = vService.crearVehiculoDesdeDTO(vDto);
		
		vService.guardarVehiculo(v);
				
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(vDto);
		
	}
	
	
	@GetMapping("/{matricula}")
	public ResponseEntity<VehiculoDTO> obtenerVehiculosSinDetalles(@PathVariable String matricula){
		logger.info("Buscando vehiculo con matricula: {}", matricula);
		Vehiculo v = vService.buscarPorMatricula(matricula.toUpperCase());
		logger.info("El vehiculo es: {}", v);
		if (v == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El vehiculo no se ha encontrado");
		
		VehiculoDTO vdto = vService.mapearEntidadVehiculoADTO(v);
		return ResponseEntity.ok(vdto);
	}
	
		
	@GetMapping("/{id}/detalles")
	public ResponseEntity<VehiculoDTO> obtenerVehiculoConDetalles(@PathVariable Long id){
		Vehiculo v = vService.buscarVehiculoPorId(id);
		if (v == null) return ResponseEntity.notFound().build();
		
		VehiculoDTO vDto = vService.mapearEntidadVehiculoConDetallesADTO(v);
		return ResponseEntity.ok(vDto);
	}

	
	@GetMapping("/all")
	public ResponseEntity<List<VehiculoDTO>> listarVehiculos() {
		List<VehiculoDTO> dtos = new ArrayList<>();
		List<Vehiculo> vehiculos = new ArrayList<>();
		vehiculos = vService.listar();
		vehiculos.forEach(v -> {
			var dto = vService.mapearEntidadVehiculoADTO(v);
			dtos.add(dto);
		});
		
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/totalVehiculos")
	public ResponseEntity<String> obtenerTotalVehiculos() {
		String total = vService.buscarTotalVehiculos();
		logger.info("El total de vehiculos es: {}", total);
		
		return ResponseEntity.ok(total);
	}
}
