package com.taller_control.control_taller.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller_control.control_taller.models.Material;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.repositories.MaterialRepository;

@Service
public class MaterialServiceImpl implements MaterialService{

	private final Logger logger = LoggerFactory.getLogger(MaterialServiceImpl.class);
	private final MaterialRepository matRepo;
	
	public MaterialServiceImpl(MaterialRepository matRepo) {
		this.matRepo = matRepo;
	}

	@Override
	public List<Material> listarMateriales() {
		return matRepo.findAll();
	}

	@Override
	public List<Material> listarPorReparacionId(Long reparacionId) {
		return null;
	}

	@Override
	public Optional<Material> findById(Long id) {
		return matRepo.findById(id);
	}

	@Override
	@Transactional
	public Material crearMaterial(Material m) {
		if (m == null) throw new IllegalArgumentException("Material es nulo");
		Material guardado = matRepo.save(m);
		logger.info("Material guardado: {}", guardado.getId());
		return guardado;
	}

	@Override
	@Transactional
	public Material crearParaReparacion(Material m, Reparacion reparacion) {
		if (m == null) throw new IllegalArgumentException("Material es nulo");
		if (reparacion == null) throw new IllegalArgumentException("Reparacion es nula");
		
		Material guardado = matRepo.save(m);
		logger.info("Material creado para reparación id={}, materialId={}", reparacion.getId(), guardado.getId());
		return guardado;
	}

	@Override
	@Transactional
	public void eliminar(Long id) {
		if (!matRepo.existsById(id)) {
			logger.warn("Intento de eliminar material inexistente id={}", id);
			return;
		}
		matRepo.deleteById(id);
		logger.info("Material eliminado id={}",id);
		
	}

	@Override
	public boolean existsByReparacionId(Long reparacionId) {
		return false;
	}
	
	
	
}
