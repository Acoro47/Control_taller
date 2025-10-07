package com.taller_control.control_taller.services;

import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.models.Vehiculo;

public interface VehiculoReparacionService {
	
	public ReparacionDTO agregarVehiculoAReparacion(ReparacionDTO r);

}
