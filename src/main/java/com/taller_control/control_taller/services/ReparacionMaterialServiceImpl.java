package com.taller_control.control_taller.services;

import java.util.List;

import com.taller_control.control_taller.models.Material;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.repositories.ReparacionRepository;

public class ReparacionMaterialServiceImpl implements ReparacionMaterialService{
	
	private final ReparacionRepository repRepo;

    public ReparacionMaterialServiceImpl(ReparacionRepository repRepo) {
        this.repRepo = repRepo;
    }

	@Override
	public List<Material> listarMaterialesDeReparacion(Long reparacionId) {
		Reparacion reparacion = repRepo.findById(reparacionId)
				.orElseThrow(() -> new  RuntimeException("Reparación no encontrada"));
		
		return reparacion.getMateriales();
	}

}
