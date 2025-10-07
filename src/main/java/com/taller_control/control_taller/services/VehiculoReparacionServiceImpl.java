package com.taller_control.control_taller.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.taller_control.control_taller.dtos.LiquidoDTO;
import com.taller_control.control_taller.dtos.MaterialDTO;
import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.models.Vehiculo;
import com.taller_control.control_taller.repositories.ReparacionRepository;
import com.taller_control.control_taller.repositories.VehiculoRepository;

import jakarta.persistence.EntityNotFoundException;

public class VehiculoReparacionServiceImpl implements VehiculoReparacionService{
	
	private final VehiculoRepository vRepo;
	private final ReparacionRepository rRepo;
	private final MaterialServiceImpl mServ;
	private final LiquidoServiceImpl lServ;
	
	public VehiculoReparacionServiceImpl(VehiculoRepository vRepo, ReparacionRepository rRepo,
			MaterialServiceImpl mServ,LiquidoServiceImpl lServ ) {
		super();
		this.vRepo = vRepo;
		this.rRepo = rRepo;
		this.mServ = mServ;
		this.lServ = lServ;

		}

	@Override
	public ReparacionDTO agregarVehiculoAReparacion(ReparacionDTO rDto) {
		Vehiculo v = vRepo.findByMatricula(rDto.getMatricula())
				.orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el vehiculo"));
		

		return null;
	}
	
	
	
	public ReparacionDTO mapearReparacionADTO(Reparacion r) {
		ReparacionDTO rDto = new ReparacionDTO();
		
		rDto.setId(longToString(r.getId()));
		rDto.setMatricula(r.getVehiculo().getMatricula());
		rDto.setDescripcion(r.getDescripcion());
		rDto.setFechaFin(parseLocalDateTimeToString(r.getFechaFin()));
		rDto.setFechaInicio(parseLocalDateTimeToString(r.getFechaInicio()));
		rDto.setFechaInicioPausa(parseLocalDateTimeToString(r.getFechaInicioPausa()));
		rDto.setFechaFinPausa(parseLocalDateTimeToString(r.getFechaFinPausa()));
		rDto.setFechaCreacion(parseLocalDateTimeToString(r.getFechaCreacion()));
		rDto.setTotalHoras(longToString(r.getTotalHoras()));
		rDto.setEstado(r.getEstado().toString());
		
		List<MaterialDTO> matDtos = r.getMateriales().stream()
				.map(mServ::mapearEntidadMaterial)
				.collect(Collectors.toList());
		List<LiquidoDTO> liqDtos = r.getLiquidos().stream()
				.map(lServ::mapearEntidadLiquido)
				.collect(Collectors.toList());
		
		rDto.setMateriales(matDtos);
		rDto.setLiquidos(liqDtos);
		
		return rDto;
	}
	
	public String localDateTimeToString(LocalDateTime date, String pattern) {
		if (date == null) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return date.format(formatter);
	}
	
	private String longToString(Long totalHorasLong) {
		return Optional.ofNullable(totalHorasLong)
				.map(l -> l.toString())
				.orElse("");
	}
	
	private Long stringToLong(String totalHorasStr) {
		
		return Optional.ofNullable(totalHorasStr)
				.filter(s -> !s.trim().isEmpty())
				.map(Long::parseLong)
				.orElse(0L);
	}
	
	private LocalDateTime parseStringToLocalDateTime(String fechaStr) {
		if (fechaStr == null || fechaStr.trim().isEmpty()) {
			return null;
		}
		return LocalDateTime.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
	}
	
	private String parseLocalDateTimeToString(LocalDateTime date) {
		
		if (date == null) return "";
		return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}
	

}
