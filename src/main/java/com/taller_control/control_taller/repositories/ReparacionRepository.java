package com.taller_control.control_taller.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taller_control.control_taller.models.Reparacion;

@Repository
public interface ReparacionRepository extends JpaRepository<Reparacion, Long>{

}
