package com.taller_control.control_taller.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller_control.control_taller.dtos.MaterialDTO;
import com.taller_control.control_taller.models.Material;
import com.taller_control.control_taller.services.MapperServiceImpl;
import com.taller_control.control_taller.services.MaterialServiceImpl;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {
	
	private final Logger logger = LoggerFactory.getLogger(MaterialController.class);
	
	private final MaterialServiceImpl mService;
	private final MapperServiceImpl mapperService;
	
	public MaterialController(MaterialServiceImpl mService, MapperServiceImpl mapperService) {
		super();
		this.mService = mService;
		this.mapperService = mapperService;
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<MaterialDTO> registrarMaterial(
			@RequestBody MaterialDTO dto, Boolean onReparation) {
		
		logger.info("Registrando material: {} en reparacion: {}", dto.getNombre(), onReparation);
		
		Material m = mapperService.toMaterial(dto);
		MaterialDTO nuevoDto = mapperService.toMaterialDTO(mService.crearMaterial(m));
		logger.info("Material creado con id: {}", nuevoDto.getId());
		return ResponseEntity.ok(nuevoDto);
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<MaterialDTO>> listarMateriales(){
		List<Material> materiales = mService.listarMateriales();
		List<MaterialDTO> dtos = mapperService.toListMaterialDTO(materiales);
		return ResponseEntity.ok(dtos);
	}
	
	

}
