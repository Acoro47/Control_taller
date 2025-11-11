package com.taller_control.control_taller.services;

import java.util.List;

import com.taller_control.control_taller.models.Liquido;

public interface ReparacionLiquidoService {
	
	List<Liquido> listarLiquidosDeReparacion(Long reparacionId);

}
