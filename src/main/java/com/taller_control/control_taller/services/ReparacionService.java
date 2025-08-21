package com.taller_control.control_taller.services;

import java.time.Duration;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taller_control.control_taller.models.Estado;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.repositories.ReparacionRepository;

@Service
public class ReparacionService {
	
	private final Logger logger = LoggerFactory.getLogger(ReparacionService.class);
	
	private final ReparacionRepository reparacionRepo;
	
	public ReparacionService(ReparacionRepository repo) {
		this.reparacionRepo = repo;
	}
	
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
	
	public String minutesToHoras(Long minutos) {
		
		long horas = minutos / 60;
		long resto = minutos % 60;
		
		return String.format("%02d:%02d", horas, resto);
	}

}
