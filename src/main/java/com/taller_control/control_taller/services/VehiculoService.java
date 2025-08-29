package com.taller_control.control_taller.services;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.taller_control.control_taller.dtos.ReparacionDTO;
import com.taller_control.control_taller.dtos.VehiculoDTO;
import com.taller_control.control_taller.models.Reparacion;
import com.taller_control.control_taller.models.Vehiculo;
import com.taller_control.control_taller.repositories.VehiculoRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class VehiculoService {
	
	private final Logger logger = LoggerFactory.getLogger(VehiculoService.class);
	
	private final VehiculoRepository repo;
	private final ReparacionService rService;
	
	public VehiculoService(VehiculoRepository rep, ReparacionService serv) {
		this.repo = rep;
		this.rService = serv;
	}
	
	public Vehiculo guardarVehiculo(Vehiculo v) {
		logger.info("Guardando vehiculo desde el VehiculoService");
		if (v.getMatricula() == null || v.getMatricula().isBlank()) throw new IllegalArgumentException("La matricula es incorrecta");
		if (repo.existsByMatricula(v.getMatricula())) {
			logger.warn("Intento de guardar vehículo con matrícula duplicada: {}", v.getMatricula());
			throw new IllegalArgumentException("La matricula ya existe");
		}
		
		return repo.save(v);
	}
	
	public Vehiculo buscarVehiculoPorId(Long id) {
		logger.info("Buscando vehiculo con id: {}", id);
		return repo.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("No se ha encontrado el vehiculo"));
	}
	
	public List<Vehiculo> listar(){
		return repo.findAll();
	}
	
	public void eliminarVehiculo(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityNotFoundException("No se ha encontrado el vehiculo");
		}
		repo.deleteById(id);
	}
	
	public Vehiculo buscarPorMatricula(String matricula) {
		return repo.findByMatricula(matricula)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"El vehiculo no se ha encontrado"));
	}
	
	public List<Vehiculo> buscarPorMarca(String marca){
		logger.info("Buscando vehiculo con marca: {}", marca);
		return repo.findByMarca(marca);
	}
	
	public List<Vehiculo> buscarPorAnio(Integer anio){
		logger.info("Buscando vehiculo del año: {}", anio);
		return repo.findByAnio(anio);
	}
	
	public Vehiculo buscarVehiculoConReparaciones(Long id) {
		return repo.buscarVehiculoConReparacionesYMateriales(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"El vehiculo no se ha encontrado"));
	}
	
	
	public VehiculoDTO mapearEntidadVehiculoADTO(Vehiculo v) {
		VehiculoDTO vDto = new VehiculoDTO();
		
		vDto.setMatricula(v.getMatricula());
		
		vDto.setMarca(v.getMarca());
		
		vDto.setModelo(v.getModelo());
		
		vDto.setAnio(v.getAnio().toString());
		
		vDto.setKm(v.getKm().toString());
		
		vDto.setValorCompra(v.getValorCompra().toString());
		
		vDto.setValorVenta(v.getValorVenta().toString());
		
		List<ReparacionDTO> repaDTO = new ArrayList<>();
		logger.info("Reparaciones: {}", repaDTO);
		List<Reparacion> reparaciones = v.getReparaciones() != null ? v.getReparaciones() : new ArrayList<Reparacion>();
		logger.info("Reparaciones: {}", repaDTO);
		if (!reparaciones.isEmpty()) {
			reparaciones.forEach(r -> {
				ReparacionDTO repDTO = rService.mapearEntidadReparacion(r);
				repaDTO.add(repDTO);
			});
			
		}
		vDto.setReparaciones(repaDTO);	
		
		return vDto;
	}
	
	public VehiculoDTO mapearEntidadVehiculoConDetallesADTO(Vehiculo v) {
		VehiculoDTO vDto = new VehiculoDTO();
		
		vDto.setMatricula(v.getMatricula());
		vDto.setMarca(v.getMarca());
		vDto.setModelo(v.getModelo());
		vDto.setAnio(v.getAnio().toString());
		vDto.setKm(v.getKm().toString());
		vDto.setValorCompra(v.getValorCompra().toString());
		vDto.setValorVenta(v.getValorVenta().toString());
		
		List<ReparacionDTO> repaDTO = new ArrayList<>();
		List<Reparacion> reparaciones = v.getReparaciones();
		
		reparaciones.forEach(r -> {
			ReparacionDTO repDTO = rService.mapearEntidadReparacion(r);
			repaDTO.add(repDTO);
		});
		
		vDto.setReparaciones(repaDTO);
		
		
		return vDto;
	}
	
	public Vehiculo mapearDTOAVehiculo(VehiculoDTO dto) {
		Vehiculo v = new Vehiculo();
		
		v.setMatricula(dto.getMatricula());
		v.setMarca(dto.getMarca());
		v.setModelo(dto.getModelo());
		try {
			v.setAnio(dto.getAnio() != null ? Integer.parseInt(dto.getAnio()) : Year.now().getValue());
		} catch(NumberFormatException e) {
			v.setAnio(Year.now().getValue());
		}
		
		v.setKm(dto.getKm() != null ? Float.parseFloat(dto.getKm()) : 0f);
		v.setValorCompra(dto.getValorCompra() != null ? Float.parseFloat(dto.getValorCompra()) : 0f);
		v.setValorVenta(dto.getValorVenta() != null ? Float.parseFloat(dto.getValorVenta()) : 0f);
		
		List<Reparacion> repa = new ArrayList<>();
		List<ReparacionDTO> repaDto = dto.getReparaciones() != null ? dto.getReparaciones() : new ArrayList<ReparacionDTO>();
		
		if (!repaDto.isEmpty()) {
			repaDto.forEach(r -> {
				Reparacion rep = rService.mapearDTOAReparacion(r);
				rep.setVehiculo(v);
				repa.add(rep);
			});
			v.setReparaciones(repa);
		}
		
		return v;
	}
	
	public Vehiculo crearVehiculoDesdeDTO(VehiculoDTO dto) {
		
		Vehiculo v = new Vehiculo();
		
		v.setMatricula(dto.getMatricula());
		v.setMarca(dto.getMarca());
		v.setModelo(dto.getModelo());
		try {
			v.setAnio(dto.getAnio() != null ? Integer.parseInt(dto.getAnio()) : Year.now().getValue());
		} catch(NumberFormatException e) {
			v.setAnio(Year.now().getValue());
		}
		v.setKm(dto.getKm() != null ? Float.parseFloat(dto.getKm()) : 0f);
		v.setValorCompra(dto.getValorCompra() != "" ? Float.parseFloat(dto.getValorCompra()) : 0f);
		v.setValorVenta(dto.getValorVenta() != "" ? Float.parseFloat(dto.getValorVenta()) : 0f);
		v.setReparaciones(new ArrayList<>());
		
		return v;
	}

}
