package com.taller_control.control_taller.services;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller_control.control_taller.models.Estado;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.repositories.ReparacionRepository;

@Service
public class ReparacionService {
	
	@Autowired
	private ReparacionRepository reparacionRepo;
	
	public Reparacion abrirReparacion(Reparacion reparacion) {
		
		reparacion.setFechaInicio(LocalDateTime.now());
		reparacion.setEstado(Estado.EN_REPARACION);
		return reparacionRepo.save(reparacion);
	}
	
	public Reparacion pausarReparacion(Long id) {
		Reparacion rep = reparacionRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Reparación no encontrada"));
		if (rep.getEstado() == Estado.EN_REPARACION) {
			long minutos = Duration.between(rep.getFechaInicio(), LocalDateTime.now()).toMinutes();
			rep.setTotalHoras(rep.getTotalHoras() + minutos);
			rep.setEstado(Estado.PENDIENTE);
			return reparacionRepo.save(rep);
		}
		throw new RuntimeException("No se puede pausar una reparación que no está activa");
	}
	
	public Reparacion cerrarReparacion(Long id) {
		return null;
	}

}
