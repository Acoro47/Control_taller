package com.taller_control.control_taller.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taller_control.control_taller.dtos.LiquidoDTO;
import com.taller_control.control_taller.dtos.MaterialDTO;
import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.models.Liquido;
import com.taller_control.control_taller.models.Material;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.repositories.ReparacionRepository;
import com.taller_control.control_taller.repositories.VehiculoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReparacionService {
	
	private final Logger logger = LoggerFactory.getLogger(ReparacionService.class);
	
	private final ReparacionRepository reparacionRepo;
	private final VehiculoRepository vRepo;
	private final MaterialService mServ;
	private final LiquidoService lServ;
	
	public ReparacionService(ReparacionRepository repo,VehiculoRepository repoV, MaterialService serv, LiquidoService lser) {
		this.reparacionRepo = repo;
		this.vRepo = repoV;
		this.mServ = serv;
		this.lServ = lser;
	}
	
	public List<Reparacion> buscarPorVehiculo(String matricula){
		return reparacionRepo.findByVehiculoMatricula(matricula);
	}
	
	public List<Reparacion> buscarPorNombreMaterial(String nombre){
		return reparacionRepo.findByMaterialesNombre(nombre);
	}
	
	public Reparacion guardarReparacion(ReparacionDTO reparacion) {
		logger.info("Guardando reparación");
		return reparacionRepo.save(mapearDTOAReparacion(reparacion));
	}
	
	public Reparacion buscarPorId(Long id) {
		logger.info("Buscando reparación con id: {}", id);
		return reparacionRepo.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Reparación no encontrada"));
	}
	
	public List<Reparacion> listarReparaciones(){
		return reparacionRepo.findAll();
	}

	public void eliminarReparacion(Long id) {
		if(!reparacionRepo.existsById(id)) {
			throw new EntityNotFoundException("Reparación no se ha podido eliminar porque no se encuentra");
		}
		reparacionRepo.deleteById(id);
	}
	
	public List<Reparacion> mostrarReparacionesPorMatriculaDeVehiculo(String matricula){
		return reparacionRepo.findByVehiculoMatricula(matricula);
	}
	
	public ReparacionDTO mapearEntidadReparacion(Reparacion r) {
		ReparacionDTO rDto = new ReparacionDTO();
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
	
	public ReparacionDTO mapearDtoAReparacion(Reparacion rep) {
		ReparacionDTO dto = new ReparacionDTO();
		
		dto.setFechaInicio(rep.getFechaInicio().toString());
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
	
	public Reparacion crearReparacionDesdeDTO(ReparacionDTO dto) {
		
		Reparacion r = new Reparacion();
		
		r.setDescripcion(dto.getDescripcion());
		
		return null;
	}
	
}
