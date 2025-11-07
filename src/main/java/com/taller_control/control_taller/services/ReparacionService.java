package com.taller_control.control_taller.services;

import java.util.List;

import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.models.Vehiculo;

public interface ReparacionService {
	
	// Buscando Reparaciones

	public List<Reparacion> buscarPorVehiculo(String matricula);
	
	public List<ReparacionDTO> buscarPorNombreMaterial(String nombre);
	
	public Reparacion buscarPorId(Long id);
	
	public List<Reparacion> listarReparaciones();
	
	public List<ReparacionDTO> mostrarReparacionesPorMatriculaDeVehiculo(String matricula);
	
	public Integer buscarTotalReparaciones();
	
	public Reparacion buscarIniciada();
	

	// Guardando / Eliminando reparaciones
	
	public Reparacion guardarReparacion(Reparacion reparacion);
	
	public void eliminarReparacion(Long id);
	
	
	// Gestion de reparaciones		
	
	public ReparacionDTO agregarVehiculoDTOAReparacionDTO(VehiculoDTO vDto, ReparacionDTO rDto);
	
	public Reparacion agregarVehiculoAReparacion(Vehiculo v, Reparacion r);
	
	public Reparacion iniciarReparacion(Reparacion repa);
	
	public Reparacion pausarReparacion(Reparacion repa);
	
	public Reparacion reiniciarReparacion(Reparacion repa);
	
	public Reparacion finalizarReparacion(Reparacion repa);
}
