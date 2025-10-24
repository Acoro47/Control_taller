package com.taller_control.control_taller.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taller_control.control_taller.models.Material;
import com.taller_control.control_taller.repositories.MaterialRepository;

@Service
public class MaterialServiceImpl implements MaterialService{

	private final MaterialRepository matRepo;
	
	public MaterialServiceImpl(MaterialRepository repo) {
		this.matRepo = repo;
	}
	
	@Override
	public List<Material> listarMateriales() {
		List<Material> materiales = matRepo.findAll();
		
		return materiales;
	}

	@Override
	public Material crearMaterial(Material m) {
		Material guardado = matRepo.save(m);
		return guardado;
	}
	
}
