package com.taller_control.control_taller.services;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.taller_control.control_taller.dtos.LiquidoDTO;
import com.taller_control.control_taller.dtos.MaterialDTO;
import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Liquido;
import com.taller_control.control_taller.models.Material;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.models.Vehiculo;

public class MapperServiceImpl implements MapperService {
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

	@Override
	public VehiculoDTO toVehiculoDTO(Vehiculo v) {

		return null;
	}

	@Override
	public Vehiculo toVehiculo(VehiculoDTO vdto) {

		if (vdto == null) throw new NullPointerException("El Vehiculo DTO es nulo");
		
		Vehiculo v = new Vehiculo();
		
		v.setMatricula(vdto.getMatricula());
		v.setMarca(vdto.getMarca());
		v.setModelo(vdto.getModelo());
		
		v.setAnio(parseIntOrDefault(vdto.getAnio(), Year.now().getValue()));
		
		v.setKm(vdto.getKm() != null ? Float.parseFloat(vdto.getKm()) : 0f);
		v.setValorCompra(parseFloatOrDefault(vdto.getValorCompra(), 0f));
		v.setValorVenta(parseFloatOrDefault(vdto.getValorVenta(), 0f));
		
		if(vdto.getReparaciones() != null && !vdto.getReparaciones().isEmpty()) {
			List<Reparacion> repa = vdto.getReparaciones().stream()
					.map(this::toReparacion)
					.peek(r -> r.setVehiculo(v))
					.collect(Collectors.toList());
			v.setReparaciones(repa);
		}
				
		return v;
	}

	@Override
	public ReparacionDTO toReparacionDTO(Reparacion r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reparacion toReparacion(ReparacionDTO rDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MaterialDTO toMaterialDTO(Material m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Material toMaterial(MaterialDTO mDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LiquidoDTO toLiquidoDTO(Liquido liquid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Liquido toLiquido(LiquidoDTO liquidDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dateToString(LocalDateTime date, String pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDateTime dateToLocalDateTime(String dateStr, String pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long stringToLong(String horasStr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String longToString(Long horas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int parseIntOrDefault(String value, int defaultValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float parseFloatOrDefault(String value, float defaultValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
