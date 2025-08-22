package com.taller_control.control_taller.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taller_control.control_taller.dtos.LiquidoDTO;
import com.taller_control.control_taller.dtos.MaterialDTO;
import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.repositories.ReparacionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReparacionService {
	
	private final Logger logger = LoggerFactory.getLogger(ReparacionService.class);
	
	private final ReparacionRepository reparacionRepo;
	private final MaterialService mServ;
	private final LiquidoService lServ;
	
	public ReparacionService(ReparacionRepository repo, MaterialService serv, LiquidoService lser) {
		this.reparacionRepo = repo;
		this.mServ = serv;
		this.lServ = lser;
	}
	
	public List<Reparacion> buscarPorVehiculo(String matricula){
		return reparacionRepo.findByVehiculoMatricula(matricula);
	}
	
	public List<Reparacion> buscarPorNombreMaterial(String nombre){
		return reparacionRepo.findByMaterialesNombre(nombre);
	}
	
	public Reparacion guardarReparacion(Reparacion reparacion) {
		logger.info("Guardando reparación");
		return reparacionRepo.save(reparacion);
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
		rDto.setFechaFin(r.getFechaFin());
		rDto.setFechaInicio(r.getFechaInicio());
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
	
	
}
