package com.taller_control.control_taller.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taller_control.control_taller.dtos.LiquidoDTO;
import com.taller_control.control_taller.dtos.MaterialDTO;
import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Liquido;
import com.taller_control.control_taller.models.Material;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.models.Vehiculo;
import com.taller_control.control_taller.repositories.ReparacionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReparacionServiceImpl implements ReparacionService{
	
	private final Logger logger = LoggerFactory.getLogger(ReparacionServiceImpl.class);
	
	private final ReparacionRepository reparacionRepo;
	private final MaterialServiceImpl mServ;
	private final LiquidoServiceImpl lServ;
	
	public ReparacionServiceImpl(ReparacionRepository repo, MaterialServiceImpl serv, LiquidoServiceImpl lser) {
		this.reparacionRepo = repo;
		this.mServ = serv;
		this.lServ = lser;
	}
	
	@Override
	public List<ReparacionDTO> buscarPorVehiculo(String matricula){
		List<Reparacion> reparaciones = reparacionRepo.findByVehiculoMatricula(matricula);
		List<ReparacionDTO> listaDto = new ArrayList<>();
		VehiculoDTO vDto = new VehiculoDTO();
		vDto.setMatricula(matricula);
		
		reparaciones.forEach(r -> {
			ReparacionDTO dto = mapearReparacionADTO(r,vDto);
			listaDto.add(dto);
		});
		
		return listaDto;
		
	}
	
	@Override
	public List<Reparacion> buscarPorNombreMaterial(String nombre){
		return reparacionRepo.findByMaterialesNombre(nombre);
	}
	
	@Override
	public ReparacionDTO guardarReparacion(ReparacionDTO reparacion, VehiculoDTO vDto) {
		logger.info("Guardando reparación");
		Reparacion r = reparacionRepo.save(mapearDTOAReparacion(reparacion, vDto));
		
		return mapearReparacionADTO(r,vDto);
	}
	
	@Override
	public Reparacion buscarPorId(Long id) {
		logger.info("Buscando reparación con id: {}", id);
		return reparacionRepo.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Reparación no encontrada"));
	}
	
	@Override
	public List<Reparacion> listarReparaciones(){
		return reparacionRepo.findAll();
	}

	@Override
	public void eliminarReparacion(Long id) {
		if(!reparacionRepo.existsById(id)) {
			throw new EntityNotFoundException("Reparación no se ha podido eliminar porque no se encuentra");
		}
		reparacionRepo.deleteById(id);
	}
	
	@Override
	public List<Reparacion> mostrarReparacionesPorMatriculaDeVehiculo(String matricula){
		return reparacionRepo.findByVehiculoMatricula(matricula);
	}
	
	public Reparacion mapearDTOAReparacion(ReparacionDTO repaDto, VehiculoDTO vehiculoDTO) {
		Reparacion r = new Reparacion();
		
		r.setDescripcion(repaDto.getDescripcion());
		r.setFechaFin(parseStringToLocalDateTime(repaDto.getFechaFin()));
		r.setFechaInicio(parseStringToLocalDateTime(repaDto.getFechaInicio()));
		r.setFechaInicioPausa(parseStringToLocalDateTime(repaDto.getFechaInicioPausa()));
		r.setFechaFinPausa(parseStringToLocalDateTime(repaDto.getFechaFinPausa()));
		
		if (vehiculoDTO != null) {
			Vehiculo v = new Vehiculo();
			v.setId(stringToLong(vehiculoDTO.getId()));
			r.setVehiculo(v);
			
		}
		
		List<Material> mat = repaDto.getMateriales().stream()
				.map(mServ::mapearDtoAMaterial)
				.collect(Collectors.toList());
		
		List<Liquido> liq = repaDto.getLiquidos().stream()
				.map(lServ::mapearDtoALiquido)
				.collect(Collectors.toList());
		
		r.setMateriales(mat);
		r.setLiquidos(liq);
		r.setTotalHoras(stringToLong(repaDto.getTotalHoras()));
		
		return r;
	}
	
	public ReparacionDTO mapearReparacionADTO(Reparacion r,VehiculoDTO vehiculoDTO) {
		ReparacionDTO rDto = new ReparacionDTO();
		
		rDto.setId(longToString(r.getId()));
		rDto.setMatricula(vehiculoDTO.getMatricula());
		rDto.setDescripcion(r.getDescripcion());
		rDto.setFechaFin(parseLocalDateTimeToString(r.getFechaFin()));
		rDto.setFechaInicio(parseLocalDateTimeToString(r.getFechaInicio()));
		rDto.setFechaInicioPausa(parseLocalDateTimeToString(r.getFechaInicioPausa()));
		rDto.setFechaFinPausa(parseLocalDateTimeToString(r.getFechaFinPausa()));
		rDto.setFechaCreacion(parseLocalDateTimeToString(r.getFechaCreacion()));
		rDto.setTotalHoras(longToString(r.getTotalHoras()));
		rDto.setEstado(r.getEstado().toString());
		rDto.setVdto(vehiculoDTO);
		
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
	
	@Override
	public Reparacion crearReparacion(Reparacion reparacion) {
		reparacion.setFechaCreacion(LocalDateTime.now());
		return reparacionRepo.save(reparacion);
		
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
