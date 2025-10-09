package com.taller_control.control_taller.services;

import java.util.List;

import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.models.Vehiculo;

public interface ReparacionService {

	public List<Reparacion> buscarPorVehiculo(String matricula);
	
	public List<ReparacionDTO> buscarPorNombreMaterial(String nombre);
	
	public Reparacion guardarReparacion(Reparacion reparacion);
	
	public Reparacion buscarPorId(Long id);
	
	public List<Reparacion> listarReparaciones();
	
	public void eliminarReparacion(Long id);
	
	public List<ReparacionDTO> mostrarReparacionesPorMatriculaDeVehiculo(String matricula);
	
	public ReparacionDTO agregarVehiculoDTOAReparacionDTO(VehiculoDTO vDto, ReparacionDTO rDto);
	
	public Reparacion agregarVehiculoAReparacion(Vehiculo v, Reparacion r);
	
	public Integer buscarTotalReparaciones();
}
