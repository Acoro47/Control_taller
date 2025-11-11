package com.taller_control.control_taller.services;

import java.util.List;

import com.taller_control.control_taller.models.Liquido;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.repositories.ReparacionRepository;

public class ReparacionLiquidoServiceImpl implements ReparacionLiquidoService{
	
	private final ReparacionRepository repRepo;
	

	public ReparacionLiquidoServiceImpl(ReparacionRepository repRepo) {
		super();
		this.repRepo = repRepo;
	}


	@Override
	public List<Liquido> listarLiquidosDeReparacion(Long reparacionId) {
		
		Reparacion reparacion = repRepo.findById(reparacionId)
				.orElseThrow(()-> new RuntimeException("Reparación no encontrada"));
		
		return reparacion.getLiquidos();
	}

}
