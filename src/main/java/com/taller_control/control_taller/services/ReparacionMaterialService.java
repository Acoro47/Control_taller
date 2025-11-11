package com.taller_control.control_taller.services;

import java.util.List;

import com.taller_control.control_taller.models.Material;

public interface ReparacionMaterialService {
	
	List<Material> listarMaterialesDeReparacion(Long reparacionId);

}
