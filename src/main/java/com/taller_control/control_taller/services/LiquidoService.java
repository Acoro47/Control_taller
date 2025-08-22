package com.taller_control.control_taller.services;

import org.springframework.stereotype.Service;

import com.taller_control.control_taller.dtos.LiquidoDTO;
import com.taller_control.control_taller.models.Liquido;
import com.taller_control.control_taller.repositories.LiquidoRepository;

@Service
public class LiquidoService {
	
private final LiquidoRepository liRepo;
	
	public LiquidoService(LiquidoRepository repo) {
		this.liRepo = repo;
	}
	
	
	public LiquidoDTO mapearEntidadLiquido(Liquido l) {
		
		LiquidoDTO liq = new LiquidoDTO();
		
		liq.setNombre(l.getNombre());
		liq.setPrecioLitro(l.getPrecioLitro());
		
		return liq;
	}
	

}
