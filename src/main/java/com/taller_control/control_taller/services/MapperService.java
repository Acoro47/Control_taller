package com.taller_control.control_taller.services;

import java.time.LocalDateTime;

import com.taller_control.control_taller.dtos.LiquidoDTO;
import com.taller_control.control_taller.dtos.MaterialDTO;
import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Liquido;
import com.taller_control.control_taller.models.Material;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.models.Vehiculo;

public interface MapperService {
	
	public VehiculoDTO toVehiculoDTO(Vehiculo v);
	public Vehiculo toVehiculo(VehiculoDTO vdto);
	
	public ReparacionDTO toReparacionDTO(Reparacion r);
	public Reparacion toReparacion(ReparacionDTO rDto);
	
	public MaterialDTO toMaterialDTO(Material m);
	public Material toMaterial(MaterialDTO mDto);
	
	public LiquidoDTO toLiquidoDTO(Liquido liquid);
	public Liquido toLiquido(LiquidoDTO liquidDTO);
	
	
	public String dateToString(LocalDateTime date, String pattern);
	public LocalDateTime dateToLocalDateTime(String dateStr, String pattern);
	
	public Long stringToLong(String horasStr);
	public String longToString(Long horas);
	
	public int parseIntOrDefault(String value, int defaultValue);
	public float parseFloatOrDefault(String value, float defaultValue);

}
