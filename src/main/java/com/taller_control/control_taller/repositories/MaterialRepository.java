package com.taller_control.control_taller.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taller_control.control_taller.models.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long>{
	
	Optional<Material> findByNombre(String nombre);
	
}
