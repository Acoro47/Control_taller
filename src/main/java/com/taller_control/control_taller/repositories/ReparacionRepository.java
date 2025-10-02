package com.taller_control.control_taller.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.models.Reparacion;

@Repository
public interface ReparacionRepository extends JpaRepository<Reparacion, Long>{

	// Buscar por vehiculo
	List<Reparacion> findByVehiculoMatricula(String matricula);
	// Buscar por materiales
	List<Reparacion> findByMaterialesNombre(String nombre);
	
	// Buscar reparacion con sus materiales
	
}
