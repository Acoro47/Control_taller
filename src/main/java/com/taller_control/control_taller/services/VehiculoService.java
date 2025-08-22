package com.taller_control.control_taller.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.models.Vehiculo;
import com.taller_control.control_taller.repositories.VehiculoRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class VehiculoService {
	
	private final Logger logger = LoggerFactory.getLogger(VehiculoService.class);
	
	private final VehiculoRepository repo;
	private final ReparacionService rService;
	
	public VehiculoService(VehiculoRepository rep, ReparacionService serv) {
		this.repo = rep;
		this.rService = serv;
	}
	
	public Vehiculo guardarVehiculo(Vehiculo v) {
		logger.info("Guardando vehiculo desde el VehiculoService");
		if (v.getMatricula() == null || v.getMatricula().isBlank()) throw new IllegalArgumentException("La matricula es incorrecta");
		if (repo.existsByMatricula(v.getMatricula())) {
			logger.warn("Intento de guardar vehículo con matrícula duplicada: {}", v.getMatricula());
			throw new IllegalArgumentException("La matricula ya existe");
		}
		
		return repo.save(v);
	}
	
	public Vehiculo buscarVehiculoPorId(Long id) {
		logger.info("Buscando vehiculo con id: {}", id);
		return repo.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("No se ha encontrado el vehiculo"));
	}
	
	public List<Vehiculo> listar(){
		return repo.findAll();
	}
	
	public void eliminarVehiculo(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityNotFoundException("No se ha encontrado el vehiculo");
		}
		repo.deleteById(id);
	}
	
	public Vehiculo buscarMatricula(String matricula) {
		logger.info("Buscando vehiculo con matricula: {}", matricula);
		return repo.findByMatricula(matricula)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"El vehiculo no se ha encontrado"));
	}
	
	public List<Vehiculo> buscarPorMarca(String marca){
		logger.info("Buscando vehiculo con marca: {}", marca);
		return repo.findByMarca(marca);
	}
	
	public List<Vehiculo> buscarPorAnio(Integer anio){
		logger.info("Buscando vehiculo del año: {}", anio);
		return repo.findByAnio(anio);
	}
	
	public Vehiculo buscarVehiculoConReparaciones(Long id) {
		return repo.buscarVehiculoConReparacionesYMateriales(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"El vehiculo no se ha encontrado"));
	}
	
	
	public VehiculoDTO mapearEntidadVehiculo(Vehiculo v) {
		VehiculoDTO vDto = new VehiculoDTO();
		
		vDto.setMatricula(v.getMatricula());
		vDto.setMarca(v.getMarca());
		vDto.setModelo(v.getModelo());
		vDto.setAnio(v.getAnio());
		List<ReparacionDTO> repaDTO = new ArrayList<>();
		List<Reparacion> reparaciones = v.getReparaciones();
		
		reparaciones.forEach(r -> {
			ReparacionDTO repDTO = rService.mapearEntidadReparacion(r);
			repaDTO.add(repDTO);
		});
		
		vDto.setReparaciones(repaDTO);
		
		return vDto;
	}
	
	public VehiculoDTO mapearEntidadVehiculoConDetalles(Vehiculo v) {
		VehiculoDTO vDto = new VehiculoDTO();
		
		vDto.setMatricula(v.getMatricula());
		vDto.setMarca(v.getMarca());
		vDto.setModelo(v.getModelo());
		vDto.setAnio(v.getAnio());
		List<ReparacionDTO> repaDTO = new ArrayList<>();
		List<Reparacion> reparaciones = v.getReparaciones();
		
		reparaciones.forEach(r -> {
			ReparacionDTO repDTO = rService.mapearEntidadReparacion(r);
			repaDTO.add(repDTO);
		});
		
		vDto.setReparaciones(repaDTO);
		
		
		return vDto;
	}

}
