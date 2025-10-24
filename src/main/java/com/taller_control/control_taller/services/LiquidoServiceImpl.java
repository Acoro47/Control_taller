package com.taller_control.control_taller.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taller_control.control_taller.models.Liquido;
import com.taller_control.control_taller.repositories.LiquidoRepository;

@Service
public class LiquidoServiceImpl implements LiquidoService{
	
private final LiquidoRepository liRepo;
	
	public LiquidoServiceImpl(LiquidoRepository repo) {
		this.liRepo = repo;
	}

	@Override
	public List<Liquido> listarLiquidos() {

		return null;
	}
	

}
