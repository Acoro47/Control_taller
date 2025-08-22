package com.taller_control.control_taller.services;

import org.springframework.stereotype.Service;

import com.taller_control.control_taller.dtos.MaterialDTO;
import com.taller_control.control_taller.models.Material;
import com.taller_control.control_taller.repositories.MaterialRepository;

@Service
public class MaterialService {

	private final MaterialRepository matRepo;
	
	public MaterialService(MaterialRepository repo) {
		this.matRepo = repo;
	}
	
	public MaterialDTO mapearEntidadMaterial(Material m) {
		
		MaterialDTO mat = new MaterialDTO();
		
		mat.setNombre(m.getNombre());
		mat.setCoste(m.getCoste());
		
		return mat;
	}
	
}
