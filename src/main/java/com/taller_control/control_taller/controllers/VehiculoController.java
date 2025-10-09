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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Vehiculo;
import com.taller_control.control_taller.services.MapperService;
import com.taller_control.control_taller.services.VehiculoService;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
	
	private Logger logger = LoggerFactory.getLogger(VehiculoController.class);
	private final VehiculoService vService;
	private final MapperService mapperService;
	
	public VehiculoController(VehiculoService vService, MapperService mapperService) {
		this.vService = vService;
		this.mapperService = mapperService;
	}
	
	
	@PostMapping("/registrar")
	public ResponseEntity<VehiculoDTO> registrarCoche(
			@RequestBody VehiculoDTO vDto) {
		logger.info("Registrando vehiculo nuevo: {}", vDto.getMatricula());
		
		Vehiculo v = vService.crearVehiculoDesdeDTO(vDto);
		
		VehiculoDTO guardado = mapperService.toVehiculoDTO(vService.guardarVehiculo(v));
				
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(guardado);
		
	}
	
	
	@GetMapping("/{matricula}")
	public ResponseEntity<VehiculoDTO> obtenerVehiculosSinDetalles(@PathVariable String matricula){
		logger.info("Buscando vehiculo con matricula: {}", matricula);
		VehiculoDTO vDto = mapperService.toVehiculoDTO(vService.buscarPorMatricula(matricula.toUpperCase()));
		logger.info("El vehiculo es: {}", vDto.getId());
		
		return ResponseEntity.ok(vDto);
	}
	
		
	@GetMapping("/{id}/detalles")
	public ResponseEntity<VehiculoDTO> obtenerVehiculoConDetalles(@PathVariable Long id){
		Vehiculo v = vService.buscarVehiculoPorId(id);
		if (v == null) return ResponseEntity.notFound().build();
		
		VehiculoDTO vDto = mapperService.toVehiculoDTO(v);
		return ResponseEntity.ok(vDto);
	}

	
	@GetMapping("/all")
	public ResponseEntity<List<VehiculoDTO>> listarVehiculos() {
		List<VehiculoDTO> dtos = mapperService.forEachVehiculo(vService.listar());
		return ResponseEntity.ok(dtos);
		
	}
	
	@GetMapping("/totalVehiculos")
	public ResponseEntity<Integer> obtenerTotalVehiculos() {
		int total = vService.buscarTotalVehiculos();
		logger.info("El total de vehiculos es: {}", total);
		
		return ResponseEntity.ok(total);
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<VehiculoDTO>> buscarVehiculosPorMatriculaFiltrada(
			@RequestParam String query) {
		logger.info("Buscando vehiculos mediante filtros de matricula {}", query);
		List<VehiculoDTO> resultado = mapperService.forEachVehiculo(vService.buscarPorMatriculaParcial(query));
		return ResponseEntity.ok(resultado);
		
	}
}
