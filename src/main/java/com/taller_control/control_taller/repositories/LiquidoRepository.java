package com.taller_control.control_taller.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taller_control.control_taller.models.Liquido;

@Repository
public interface LiquidoRepository extends JpaRepository<Liquido, Long>{
	
	// Buscar por nombre
	// Buscar por precio
	// Buscar por reparacion
	Optional<List<Liquido>> findByReparacionId(String reparacionId);

}
