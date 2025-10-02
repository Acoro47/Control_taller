package com.taller_control.control_taller.services;

import java.util.List;

import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.models.Reparacion;

public interface ReparacionService {

	public List<ReparacionDTO> buscarPorVehiculo(String matricula);
	
	public List<Reparacion> buscarPorNombreMaterial(String nombre);
	
	public ReparacionDTO guardarReparacion(ReparacionDTO reparacion);
	
	public Reparacion buscarPorId(Long id);
	
	public List<Reparacion> listarReparaciones();
	
	public void eliminarReparacion(Long id);
	
	public List<Reparacion> mostrarReparacionesPorMatriculaDeVehiculo(String matricula);
	
	public Reparacion crearReparacion(Reparacion reparacion);
}
