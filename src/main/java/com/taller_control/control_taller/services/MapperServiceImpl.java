package com.taller_control.control_taller.services;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.taller_control.control_taller.dtos.LiquidoDTO;
import com.taller_control.control_taller.dtos.MaterialDTO;
import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Liquido;
import com.taller_control.control_taller.models.Material;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.models.Vehiculo;

@Service
public class MapperServiceImpl implements MapperService {
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	

	@Override
	public VehiculoDTO toVehiculoDTO(Vehiculo v) {
		
		if (v == null) return null;

		VehiculoDTO vDto = new VehiculoDTO();
		vDto.setId(longToString(v.getId()));
		vDto.setMatricula(v.getMatricula());
		vDto.setMarca(v.getMarca());
		vDto.setModelo(v.getModelo());
		vDto.setAnio(v.getAnio() != null ? v.getAnio().toString() : "");
		vDto.setKm(v.getKm() != null ? v.getKm().toString() : "");
		vDto.setValorCompra(v.getValorCompra() != null ? v.getValorCompra().toString() : "");
		vDto.setValorVenta(v.getValorVenta() != null ? v.getValorVenta().toString(): "");
		
		List<ReparacionDTO> repaDTO = new ArrayList<>();
		List<Reparacion> reparaciones = v.getReparaciones();
		
		reparaciones.forEach(r -> {
			ReparacionDTO repDTO = toReparacionDTO(r);
			repaDTO.add(repDTO);
		});
		
		vDto.setReparaciones(repaDTO);
		
		
		return vDto;
	}

	@Override
	public Vehiculo toVehiculo(VehiculoDTO vdto) {

		if (vdto == null) return null;
		
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
		
		if (r == null) return null;
		
		ReparacionDTO rDto = new ReparacionDTO();
		
		rDto.setId(longToString(r.getId()));
		rDto.setMatricula(r.getVehiculo() != null ? r.getVehiculo().getMatricula() : null );
		rDto.setDescripcion(r.getDescripcion());
		rDto.setFechaFin(dateToString(r.getFechaFin()));
		rDto.setFechaInicio(dateToString(r.getFechaInicio()));
		rDto.setFechaInicioPausa(dateToString(r.getFechaInicioPausa()));
		rDto.setFechaFinPausa(dateToString(r.getFechaFinPausa()));
		rDto.setFechaCreacion(dateToString(r.getFechaCreacion()));
		rDto.setTotalHoras(doubleToString(r.getTotalHoras()));
		rDto.setEstado(r.getEstado().toString());
		
		List<MaterialDTO> matDtos = r.getMateriales().stream()
				.map(this::toMaterialDTO)
				.collect(Collectors.toList());
		List<LiquidoDTO> liqDtos = r.getLiquidos().stream()
				.map(this::toLiquidoDTO)
				.collect(Collectors.toList());
		
		rDto.setMateriales(matDtos);
		rDto.setLiquidos(liqDtos);
		
		return rDto;
	}

	@Override
	public Reparacion toReparacion(ReparacionDTO rDto) {
		
		if (rDto == null) return null;
		
		Reparacion r = new Reparacion();
		
		r.setDescripcion(rDto.getDescripcion());
		r.setFechaFin(dateToLocalDateTime(rDto.getFechaFin()));
		r.setFechaInicio(dateToLocalDateTime(rDto.getFechaInicio()));
		r.setFechaInicioPausa(dateToLocalDateTime(rDto.getFechaInicioPausa()));
		r.setFechaFinPausa(dateToLocalDateTime(rDto.getFechaFinPausa()));
		
		List<Material> mat = rDto.getMateriales().stream()
				.map(this::toMaterial)
				.collect(Collectors.toList());
		
		List<Liquido> liq = rDto.getLiquidos().stream()
				.map(this::toLiquido)
				.collect(Collectors.toList());
		
		r.setMateriales(mat);
		r.setLiquidos(liq);
		r.setTotalHoras(parseDoubleOrDefault(rDto.getTotalHoras()));
		
		return r;

	}

	@Override
	public MaterialDTO toMaterialDTO(Material m) {

		if (m == null) return null;
		
		MaterialDTO mat = new MaterialDTO();
		
		mat.setNombre(m.getNombre());
		mat.setCoste(m.getNombre() != null ? m.getCoste().toString() : "0");
		
		return mat;
		
	}

	@Override
	public Material toMaterial(MaterialDTO mDto) {

		if (mDto == null) return null;
		
		Material m = new Material();
		
		m.setNombre(mDto.getNombre());	
		m.setCoste(mDto.getCoste() != null ? Float.parseFloat(mDto.getCoste()) : 0f);
		
		return m;
		
		
	}

	@Override
	public LiquidoDTO toLiquidoDTO(Liquido liquid) {
		
		if (liquid == null) return null;
		
		LiquidoDTO liq = new LiquidoDTO();
		
		liq.setNombre(liquid.getNombre());
		liq.setPrecioLitro(liquid.getPrecioLitro().toString());
		
		return liq;
	}

	@Override
	public Liquido toLiquido(LiquidoDTO liquidDTO) {
		
		if (liquidDTO == null) return null;
		
		Liquido l = new Liquido();
		
		l.setNombre(liquidDTO.getNombre());
		l.setCantidad(liquidDTO.getCantidad() != null ? Float.parseFloat(liquidDTO.getCantidad()) : 0f);
		l.setPrecioLitro(liquidDTO.getPrecioLitro() != null ? Float.parseFloat(liquidDTO.getPrecioLitro()) : 0f);
		
		return l;
	}

	@Override
	public String dateToString(LocalDateTime date) {
		
		return date != null ? date.format(formatter) : "";
	}

	@Override
	public LocalDateTime dateToLocalDateTime(String dateStr) {
		return (dateStr != null && !dateStr.isEmpty()) ? LocalDateTime.parse(dateStr, formatter) : null;
	}

	@Override
	public Long stringToLong(String horasStr) {
		return Optional.ofNullable(horasStr)
				.filter(s -> !s.trim().isEmpty())
				.map(Long::parseLong)
				.orElse(0L);
	}

	@Override
	public String longToString(Long horas) {
		return Optional.ofNullable(horas)
				.map(l -> l.toString())
				.orElse("");
	}

	@Override
	public int parseIntOrDefault(String value, int defaultValue) {

		try {
			return (value != null && !value.isEmpty()) ? Integer.parseInt(value) : defaultValue;
			
		} catch (NumberFormatException e) {
			return defaultValue;
		}
		
	}

	@Override
	public float parseFloatOrDefault(String value, float defaultValue) {

		try {
			return (value != null && !value.isEmpty()) ? Float.parseFloat(value) : defaultValue;
			
		} catch (NumberFormatException e) {
			return defaultValue;
		}
		
	}

	@Override
	public List<VehiculoDTO> forEachVehiculo(List<Vehiculo> vehiculos) {
		return vehiculos.stream()
				.map(this::toVehiculoDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<ReparacionDTO> forEachReparacion(List<Reparacion> reparaciones) {
		return reparaciones.stream()
				.map(this::toReparacionDTO)
				.collect(Collectors.toList());
	}

	@Override
	public double parseDoubleOrDefault(String value) {
		return Optional.ofNullable(value)
				.filter(s -> !s.trim().isEmpty())
				.map(Double::parseDouble)
				.orElse(0.0);
	}

	@Override
	public String doubleToString(Double horas) {
		return Optional.ofNullable(horas)
				.map(l -> l.toString())
				.orElse("");
	}

	@Override
	public List<MaterialDTO> toListMaterialDTO(List<Material> materiales) {
		if (materiales.size() == 0) {
			return null;
		}
		return materiales.stream()
		.map(this::toMaterialDTO)
		.collect(Collectors.toList());
	}

	@Override
	public List<Material> toListMaterial(List<MaterialDTO> dtos) {
		if (dtos.size() == 0) {
			return null;
		}
		
		return dtos.stream()
				.map(this::toMaterial)
				.collect(Collectors.toList());
	}

	@Override
	public List<LiquidoDTO> toListLiquidoDTO(List<Liquido> liquidos) {
		if (liquidos.size() == 0) {
			return null;
		}
		return liquidos.stream()
				.map(this::toLiquidoDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<Liquido> toListLiquido(List<LiquidoDTO> dtos) {
		if (dtos.size() == 0) {
			return null;
		}
		return dtos.stream()
				.map(this::toLiquido)
				.collect(Collectors.toList());
	}
	
	

	

}
