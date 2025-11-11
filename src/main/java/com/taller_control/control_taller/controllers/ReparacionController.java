package com.taller_control.control_taller.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.models.Vehiculo;
import com.taller_control.control_taller.services.MapperServiceImpl;
import com.taller_control.control_taller.services.ReparacionServiceImpl;
import com.taller_control.control_taller.services.VehiculoServiceImpl;

@RestController
@RequestMapping("/api/reparaciones")
public class ReparacionController {
	
	private final Logger logger = LoggerFactory.getLogger(ReparacionController.class);
	
	private final ReparacionServiceImpl rService;
	private final VehiculoServiceImpl vService;
	private final MapperServiceImpl mapperService;
	
	public ReparacionController(ReparacionServiceImpl rService, 
								VehiculoServiceImpl vService,
								MapperServiceImpl mapperService
								) {
		this.rService = rService;
		this.vService = vService;
		this.mapperService = mapperService;
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<ReparacionDTO> registrarReparacion(
			@RequestBody ReparacionDTO rDto) {
		logger.info("Registrando reparacion: {}", rDto.getMatricula());
		
		Reparacion r = mapperService.toReparacion(rDto);
		Vehiculo v = vService.buscarPorMatricula(rDto.getMatricula());
		rService.agregarVehiculoAReparacion(v, r);
		VehiculoDTO vDto = mapperService.toVehiculoDTO(v);
		ReparacionDTO repaDto = rService.agregarVehiculoDTOAReparacionDTO(vDto, rDto);

		logger.info("Vehiculo añadido a dto: {}", repaDto.getMatricula());	
		Reparacion guardada = rService.guardarReparacion(r);
		repaDto = mapperService.toReparacionDTO(guardada);
				
		return ResponseEntity.ok(repaDto);
	}
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<ReparacionDTO> obtenerReparación(@PathVariable String id) {
		Long idLong = mapperService.stringToLong(id);
		Reparacion r =  rService.buscarPorId(idLong);
		ReparacionDTO rDto = mapperService.toReparacionDTO(r);
		return ResponseEntity.ok(rDto);
		
	}
	
	@GetMapping("/matricula/{matricula}")
	public ResponseEntity<List<ReparacionDTO>> listarReparacionesPorMatricula(@PathVariable String matricula) {
		List<ReparacionDTO> rDto = mapperService.forEachReparacion(rService.buscarPorVehiculo(matricula));
		return ResponseEntity.ok(rDto);
	}
	
	@GetMapping("/all")
	public List<ReparacionDTO> listar(){
		List<ReparacionDTO> reparacionesDTO = mapperService.forEachReparacion(rService.listarReparaciones());
		
		return reparacionesDTO;
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		rService.eliminarReparacion(id);
	}
	
	@GetMapping("/totalReparaciones")
	public ResponseEntity<Integer> obtenerTotalReparaciones() {
		int total = rService.buscarTotalReparaciones();
		return ResponseEntity.ok(total);
	}
	
	@GetMapping("/iniciar/{id}")
	public ResponseEntity<ReparacionDTO> iniciarReparacion(@PathVariable String id){
		if (id == null) {
			return ResponseEntity.badRequest().build();
		}
		Long idLong = mapperService.stringToLong(id);
		Reparacion r = rService.buscarPorId(idLong);
		if (r == null) {
			return ResponseEntity.notFound().build();
		}
		Reparacion iniciada = rService.iniciarReparacion(r);
		
		if (iniciada == null) {
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.build();
		}
		ReparacionDTO rDto = mapperService.toReparacionDTO(r);
		return ResponseEntity.ok(rDto);
	}
	
	@GetMapping("/pausar/{id}")
	public ResponseEntity<ReparacionDTO> pausarReparacion(@PathVariable String id) {
		
		if (id == null) {
			return ResponseEntity.badRequest().build();
		}
		Long idLong = mapperService.stringToLong(id);
		Reparacion r = rService.buscarPorId(idLong);
		if (r == null) {
			return ResponseEntity.notFound().build();
		}
		Reparacion pausada = rService.pausarReparacion(r);
		ReparacionDTO pausadaDto = mapperService.toReparacionDTO(pausada);
		return ResponseEntity.ok(pausadaDto);
	}
	
	@GetMapping("/reiniciar/{id}")
	public ResponseEntity<ReparacionDTO> reiniciarReparacion(@PathVariable String id){
		if (id == null) {
			return ResponseEntity.badRequest().build();
		}
		
		Long idLong = mapperService.stringToLong(id);
		Reparacion r = rService.buscarPorId(idLong);
		if (r == null) {
			return ResponseEntity.notFound().build();
		}
		Reparacion reiniciada = rService.reiniciarReparacion(r);
		if (reiniciada == null) {
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.build();
		}
		ReparacionDTO reiniciadaDto = mapperService.toReparacionDTO(reiniciada);
		return ResponseEntity.ok(reiniciadaDto);
	}
	
	@GetMapping("/finalizar/{id}")
	public ResponseEntity<ReparacionDTO> finalizarReparacion(@PathVariable String id){
		if (id == null) {
			return ResponseEntity.badRequest().build();
		}
		Long idLong = mapperService.stringToLong(id);
		Reparacion r = rService.buscarPorId(idLong);
		if (r == null) {
			return ResponseEntity.notFound().build();
		}
		Reparacion finalizada = rService.finalizarReparacion(r);
		if (finalizada == null) {
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.build();
		}
		ReparacionDTO finalizadaDto = mapperService.toReparacionDTO(r);
		return ResponseEntity.ok(finalizadaDto);
	}
	
	@GetMapping("/iniciada")
		public ResponseEntity<ReparacionDTO> reparacionIniciada(){
			Reparacion r = rService.buscarIniciada();
			if (r == null) {
				return ResponseEntity
						.noContent()
						.build();
			}
			ReparacionDTO dto = mapperService.toReparacionDTO(r);
			return ResponseEntity.ok(dto);
	}
}
