package com.taller_control.control_taller.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller_control.control_taller.dtos.LiquidoDTO;
import com.taller_control.control_taller.models.Liquido;
import com.taller_control.control_taller.services.MapperServiceImpl;
import com.taller_control.control_taller.services.ReparacionLiquidoService;

@RestController
@RequestMapping("/api/reparaciones/{reparacionId}/liquidos")
public class ReparacionLiquidoController {
	
	private final Logger logger = LoggerFactory.getLogger(ReparacionLiquidoController.class);
	
	private final ReparacionLiquidoService rService;
	private final MapperServiceImpl mapper;
	
	public ReparacionLiquidoController(ReparacionLiquidoService rService,
			MapperServiceImpl mapper) {
		super();
		this.rService = rService;
		this.mapper = mapper;
	}
	
	@GetMapping
	public ResponseEntity<List<LiquidoDTO>> listarPorReparacion(@PathVariable String reparacionId) {
		Long repId = mapper.stringToLong(reparacionId);
		List<Liquido> liquidos = rService.listarLiquidosDeReparacion(repId);
		if (liquidos.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		List<LiquidoDTO> dtos = mapper.toListLiquidoDTO(liquidos);
		
		return ResponseEntity.ok(dtos);
	}
	
	

}
