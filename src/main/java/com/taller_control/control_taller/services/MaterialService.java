package com.taller_control.control_taller.services;

import java.util.List;

import com.taller_control.control_taller.models.Material;

public interface MaterialService {

	List<Material> listarMateriales();
	Material crearMaterial(Material m);
}
