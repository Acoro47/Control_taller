package com.taller_control.control_taller.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.repositories.ReparacionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReparacionService {
	
	private final Logger logger = LoggerFactory.getLogger(ReparacionService.class);
	
	private final ReparacionRepository reparacionRepo;
	
	public ReparacionService(ReparacionRepository repo) {
		this.reparacionRepo = repo;
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
	
	
}
