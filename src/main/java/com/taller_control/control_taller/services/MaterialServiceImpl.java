package com.taller_control.control_taller.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taller_control.control_taller.dtos.MaterialDTO;
import com.taller_control.control_taller.models.Material;
import com.taller_control.control_taller.repositories.MaterialRepository;

@Service
public class MaterialServiceImpl implements MaterialService{

	private final MaterialRepository matRepo;
	
	public MaterialServiceImpl(MaterialRepository repo) {
		this.matRepo = repo;
	}
	
	public MaterialDTO mapearEntidadMaterial(Material m) {
		
		MaterialDTO mat = new MaterialDTO();
		
		mat.setNombre(m.getNombre());
		mat.setCoste(m.getCoste().toString());
		
		return mat;
	}
	
	public Material mapearDtoAMaterial(MaterialDTO dto) {
		
		Material m = new Material();
		
		m.setNombre(dto.getNombre());	
		m.setCoste(dto.getCoste() != null ? Float.parseFloat(dto.getCoste()) : 0f);
		
		return m;
	}

	@Override
	public List<Material> listarMateriales() {
		List<Material> materiales = matRepo.findAll();
		
		return materiales;
	}
	
}
