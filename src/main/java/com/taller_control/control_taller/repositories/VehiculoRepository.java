package com.taller_control.control_taller.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taller_control.control_taller.models.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long>{
	
	// Buscar por matricula
	Optional<Vehiculo> findByMatricula(String matricula);
	boolean existsByMatricula(String matricula);
	// Buscar por marca
	List<Vehiculo> findByMarca(String marca);
	// Buscar por año
	List<Vehiculo> findByAnio(Integer año);
	
	//Buscar por filtro de matricula
	List<Vehiculo> findByMatriculaContainingIgnoreCase(String query);
	
	// Buscar vehiculo concreto con sus reparaciones
	@Query("""
			SELECT DISTINCT v
			FROM Vehiculo v
			LEFT JOIN FETCH v.reparaciones r
			WHERE v.id = :idVehiculo
			""")
	Optional<Vehiculo> buscarVehiculoConReparacionesYMateriales(
			@Param("idVehiculo") Long id
			);

}
