package com.taller_control.control_taller.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taller_control.control_taller.dtos.LiquidoDTO;
import com.taller_control.control_taller.dtos.MaterialDTO;
import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Estado;
import com.taller_control.control_taller.models.Liquido;
import com.taller_control.control_taller.models.Material;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.models.Vehiculo;
import com.taller_control.control_taller.repositories.ReparacionRepository;
import com.taller_control.control_taller.repositories.VehiculoRepository;

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
	public List<Reparacion> buscarPorVehiculo(String matricula){
		return reparacionRepo.findByVehiculoMatricula(matricula);
	}
	
	@Override
	public List<Reparacion> buscarPorNombreMaterial(String nombre){
		return reparacionRepo.findByMaterialesNombre(nombre);
	}
	
	@Override
	public ReparacionDTO guardarReparacion(ReparacionDTO reparacion) {
		logger.info("Guardando reparación");
		Reparacion r = reparacionRepo.save(mapearDTOAReparacion(reparacion));
		
		return mapearEntidadReparacion(r);
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
	
	public ReparacionDTO mapearEntidadReparacion(Reparacion r) {
		ReparacionDTO rDto = new ReparacionDTO();
		rDto.setId(r.getId().toString());
		rDto.setDescripcion(r.getDescripcion());
		rDto.setFechaFin(r.getFechaFin().toString());
		rDto.setFechaInicio(r.getFechaInicio().toString());
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
	
	public Reparacion mapearDTOAReparacion(ReparacionDTO dto) {
		Reparacion r = new Reparacion();
		r.setDescripcion(dto.getDescripcion());
		r.setFechaFin(dto.getFechaFin() != null ? LocalDateTime.parse(dto.getFechaFin()) : null);
		r.setFechaInicio(dto.getFechaInicio() != null ? LocalDateTime.parse(dto.getFechaInicio()) : null);
		
		List<Material> mat = dto.getMateriales().stream()
				.map(mServ::mapearDtoAMaterial)
				.collect(Collectors.toList());
		
		List<Liquido> liq = dto.getLiquidos().stream()
				.map(lServ::mapearDtoALiquido)
				.collect(Collectors.toList());
		
		r.setMateriales(mat);
		r.setLiquidos(liq);
		
		return r;
	}
	
	public ReparacionDTO mapearReparacionADTO(Reparacion rep) {
		ReparacionDTO dto = new ReparacionDTO();
		String pattern = "dd/MM/yyyy HH:mm:ss";
		
		dto.setFechaInicio(localDateTimeToString(rep.getFechaInicio(), pattern));
		logger.info("Pasar de LocalDateTime a String: {}", dto.getFechaInicio());
		dto.setFechaFin(rep.getFechaFin().toString());
		dto.setFechaPausa(rep.getFechaPausa().toString());
		dto.setFechaReinicio(rep.getFechaReinicio().toString());
		dto.setDescripcion(rep.getDescripcion());
		
		List<MaterialDTO> mDto = rep.getMateriales().stream()
				.map(mServ::mapearEntidadMaterial)
				.collect(Collectors.toList());
		
		List<LiquidoDTO> lDto = rep.getLiquidos().stream()
				.map(lServ::mapearEntidadLiquido)
				.collect(Collectors.toList());
		dto.setLiquidos(lDto);
		dto.setMateriales(mDto);
		
		return dto;
	}
	
	@Override
	public Reparacion crearReparacion(Reparacion reparacion) {
		return reparacionRepo.save(reparacion);
		
	}
	
	public String localDateTimeToString(LocalDateTime date, String pattern) {
		if (date == null) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return date.format(formatter);
	}
	
}
