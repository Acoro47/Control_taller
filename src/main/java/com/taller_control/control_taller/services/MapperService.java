package com.taller_control.control_taller.services;

import java.time.LocalDateTime;
import java.util.List;

import com.taller_control.control_taller.dtos.LiquidoDTO;
import com.taller_control.control_taller.dtos.MaterialDTO;
import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Liquido;
import com.taller_control.control_taller.models.Material;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.models.Vehiculo;

public interface MapperService {
	
	// Vehiculos
	public VehiculoDTO toVehiculoDTO(Vehiculo v);
	public Vehiculo toVehiculo(VehiculoDTO vdto);
	
	
	// Reparaciones
	public ReparacionDTO toReparacionDTO(Reparacion r);
	public Reparacion toReparacion(ReparacionDTO rDto);
	
	
	// Materiales
	public List<MaterialDTO> toListMaterialDTO(List<Material> materiales);
	public List<Material> toListMaterial(List<MaterialDTO> dtos);
	
	public MaterialDTO toMaterialDTO(Material m);
	public Material toMaterial(MaterialDTO mDto);
	
	// Liquidos	
	public LiquidoDTO toLiquidoDTO(Liquido liquid);
	public Liquido toLiquido(LiquidoDTO liquidDTO);
	
	
	
	// Genericos
	public String dateToString(LocalDateTime date);
	public LocalDateTime dateToLocalDateTime(String dateStr);
	
	public Long stringToLong(String horasStr);
	public String longToString(Long horas);	

	public double parseDoubleOrDefault(String value);
	public String doubleToString(Double horas);
	
	public int parseIntOrDefault(String value, int defaultValue);
	public float parseFloatOrDefault(String value, float defaultValue);
	
	public List<VehiculoDTO> forEachVehiculo(List<Vehiculo> vehiculos);
	public List<ReparacionDTO> forEachReparacion(List<Reparacion> reparaciones);

}
