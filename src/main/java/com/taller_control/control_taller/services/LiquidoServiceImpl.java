package com.taller_control.control_taller.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taller_control.control_taller.dtos.LiquidoDTO;
import com.taller_control.control_taller.models.Liquido;
import com.taller_control.control_taller.repositories.LiquidoRepository;

@Service
public class LiquidoServiceImpl implements LiquidoService{
	
private final LiquidoRepository liRepo;
	
	public LiquidoServiceImpl(LiquidoRepository repo) {
		this.liRepo = repo;
	}
	
	
	public LiquidoDTO mapearEntidadLiquido(Liquido l) {
		
		LiquidoDTO liq = new LiquidoDTO();
		
		liq.setNombre(l.getNombre());
		liq.setPrecioLitro(l.getPrecioLitro().toString());
		
		return liq;
	}
	
	public Liquido mapearDtoALiquido(LiquidoDTO dto) {
		
		Liquido l = new Liquido();
		
		l.setNombre(dto.getNombre());
		l.setCantidad(dto.getCantidad() != null ? Float.parseFloat(dto.getCantidad()) : 0f);
		l.setPrecioLitro(dto.getPrecioLitro() != null ? Float.parseFloat(dto.getPrecioLitro()) : 0f);
		
		return l;
	}


	@Override
	public List<Liquido> listarLiquidos() {

		return null;
	}
	

}
