package com.taller_control.control_taller.services;

import java.util.List;

import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Vehiculo;

public interface VehiculoService {
	
	public Vehiculo guardarVehiculo(Vehiculo v);
	public Vehiculo buscarVehiculoPorId(Long id);
	public List<Vehiculo> listar();
	public void eliminarVehiculo(Long id);
	public Vehiculo buscarPorMatricula(String matricula);
	public List<Vehiculo> buscarPorMarca(String marca);
	public List<Vehiculo> buscarPorAnio(Integer anio);
	public Vehiculo buscarVehiculoConReparaciones(Long id);
	public Vehiculo crearVehiculoDesdeDTO(VehiculoDTO dto);
	public Integer buscarTotalVehiculos();
	public List<Vehiculo> buscarPorMatriculaParcial(String query);

}
