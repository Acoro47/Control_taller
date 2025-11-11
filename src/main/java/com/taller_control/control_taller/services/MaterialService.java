package com.taller_control.control_taller.services;

import java.util.List;
import java.util.Optional;

import com.taller_control.control_taller.models.Material;
import com.taller_control.control_taller.models.Reparacion;

public interface MaterialService {

	List<Material> listarMateriales();
	List<Material> listarPorReparacionId(Long reparacionId);
	Optional<Material> findById(Long id);
	Material crearMaterial(Material m);
	Material crearParaReparacion(Material m, Reparacion reparacion);
	void eliminar(Long id);
	boolean existsByReparacionId(Long reparacionId);
}
