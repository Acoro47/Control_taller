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
import com.taller_control.control_taller.models.Estado;
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
	public List<Reparacion> buscarPorVehiculo(String matricula){
		List<Reparacion> reparaciones = reparacionRepo.findByVehiculoMatricula(matricula);
				
		return reparaciones;
		
	}
	
	@Override
	public List<ReparacionDTO> buscarPorNombreMaterial(String nombre){
		return null; //reparacionRepo.findByMaterialesNombre(nombre);
	}
	
	@Override
	public Reparacion guardarReparacion(Reparacion reparacion) {
		logger.info("Guardando reparación para vehiculo con matricula: {}",reparacion.getVehiculo().getMatricula());
		
		reparacion.setFechaCreacion(LocalDateTime.now());
		reparacion.setEstado(Estado.PENDIENTE);
		reparacionRepo.save(reparacion);
		return reparacion;
	}
	
	@Override
	public Reparacion buscarPorId(Long id) {
		logger.info("Buscando reparación con id: {}", id);
		return reparacionRepo.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Reparación no encontrada"));
	}
	
	@Override
	public List<Reparacion> listarReparaciones(){
		
		return reparacionRepo.findAll()
                .stream()
                .collect(Collectors.toList());
	}
	
	
	@Override
	public void eliminarReparacion(Long id) {
		if(!reparacionRepo.existsById(id)) {
			throw new EntityNotFoundException("Reparación no se ha podido eliminar porque no se encuentra");
		}
		reparacionRepo.deleteById(id);
	}
	
	@Override
	public List<ReparacionDTO> mostrarReparacionesPorMatriculaDeVehiculo(String matricula){
		//return reparacionRepo.findByVehiculoMatricula(matricula);
		return null;
	}
	

	@Override
	public ReparacionDTO agregarVehiculoDTOAReparacionDTO(VehiculoDTO vDto, ReparacionDTO rDto) {
		rDto.setMatricula(vDto.getMatricula());
		return rDto;
	}

	@Override
	public Reparacion agregarVehiculoAReparacion(Vehiculo v, Reparacion r) {
		r.setVehiculo(v);
		return r;
	}

	@Override
	public Integer buscarTotalReparaciones() {
		List<Reparacion> todas = reparacionRepo.findAll();
		int total = todas.size();
		return total;
	}
}
