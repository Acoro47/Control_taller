package com.taller_control.control_taller.services;

import java.util.List;

import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Reparacion;

public interface ReparacionService {

	public List<ReparacionDTO> buscarPorVehiculo(String matricula);
	
	public List<ReparacionDTO> buscarPorNombreMaterial(String nombre);
	
	public ReparacionDTO guardarReparacion(ReparacionDTO reparacion);
	
	public ReparacionDTO buscarPorId(Long id);
	
	public List<ReparacionDTO> listarReparaciones();
	
	public void eliminarReparacion(Long id);
	
	public List<ReparacionDTO> mostrarReparacionesPorMatriculaDeVehiculo(String matricula);
	
	public ReparacionDTO crearReparacion(Reparacion reparacion);
}
