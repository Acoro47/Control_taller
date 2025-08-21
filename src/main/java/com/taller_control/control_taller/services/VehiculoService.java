package com.taller_control.control_taller.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taller_control.control_taller.models.Vehiculo;
import com.taller_control.control_taller.repositories.VehiculoRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class VehiculoService {
	
	private final Logger logger = LoggerFactory.getLogger(VehiculoService.class);
	
	private final VehiculoRepository repo;
	
	public VehiculoService(VehiculoRepository rep) {
		this.repo = rep;
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
				.orElseThrow(()-> new EntityNotFoundException("No se ha encontrado el vehiculo"));
	}
	
	public List<Vehiculo> buscarPorMarca(String marca){
		logger.info("Buscando vehiculo con marca: {}", marca);
		return repo.findByMarca(marca);
	}
	
	public List<Vehiculo> buscarPorAnio(Integer anio){
		logger.info("Buscando vehiculo del año: {}", anio);
		return repo.findByAnio(anio);
	}

}
