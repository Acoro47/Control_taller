package com.taller_control.control_taller.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller_control.control_taller.dtos.MaterialDTO;
import com.taller_control.control_taller.models.Material;
import com.taller_control.control_taller.services.MapperServiceImpl;
import com.taller_control.control_taller.services.ReparacionMaterialService;



@RestController
@RequestMapping("/api/reparaciones/{reparacionId}/materiales")
public class ReparacionMaterialController {
	
	private final Logger logger = LoggerFactory.getLogger(ReparacionMaterialController.class);
	
	private final ReparacionMaterialService rmService;
	private final MapperServiceImpl mapper;
	
	public ReparacionMaterialController(ReparacionMaterialService rmService,
			MapperServiceImpl mapper) {
		super();
		this.rmService = rmService;
		this.mapper = mapper;
	}
	
	
	@GetMapping
	public ResponseEntity<List<MaterialDTO>> listarPorReparacion(@PathVariable String reparacionId){
		Long repId = mapper.stringToLong(reparacionId);
		List<Material> materiales = rmService.listarMaterialesDeReparacion(repId);
		if (materiales.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		List<MaterialDTO> dtos = mapper.toListMaterialDTO(materiales);
		
		return ResponseEntity.ok(dtos);
	}
	
	

}
