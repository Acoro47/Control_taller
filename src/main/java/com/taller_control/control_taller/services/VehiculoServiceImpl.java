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
public class VehiculoServiceImpl implements VehiculoService{
	
	private final Logger logger = LoggerFactory.getLogger(VehiculoServiceImpl.class);
	
	private final VehiculoRepository repo;
	private final ReparacionServiceImpl rService;
	
	public VehiculoServiceImpl(VehiculoRepository rep, ReparacionServiceImpl serv) {
		this.repo = rep;
		this.rService = serv;
	}
	
	@Override
	public Vehiculo guardarVehiculo(Vehiculo v) {
		logger.info("Guardando vehiculo desde el VehiculoService");
		if (v.getMatricula() == null || v.getMatricula().isBlank()) throw new IllegalArgumentException("La matricula es incorrecta");
		if (repo.existsByMatricula(v.getMatricula())) {
			logger.warn("Intento de guardar vehículo con matrícula duplicada: {}", v.getMatricula());
			throw new IllegalArgumentException("La matricula ya existe");
		}
		
		return repo.save(v);
	}
	
	@Override
	public Vehiculo buscarVehiculoPorId(Long id) {
		logger.info("Buscando vehiculo con id: {}", id);
		return repo.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("No se ha encontrado el vehiculo"));
	}
	
	@Override
	public List<Vehiculo> listar(){
		return repo.findAll();
	}
	
	@Override
	public void eliminarVehiculo(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityNotFoundException("No se ha encontrado el vehiculo");
		}
		repo.deleteById(id);
	}
	
	@Override
	public VehiculoDTO buscarPorMatricula(String matricula) {
		Vehiculo v = repo.findByMatricula(matricula)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"El vehiculo no se ha encontrado"));
		if (v == null) {
			throw new NullPointerException("El vehiculo no existe");
		}
		VehiculoDTO vDto = mapearEntidadVehiculoADTO(v);
		
		return vDto;
	}
	
	@Override
	public List<Vehiculo> buscarPorMarca(String marca){
		logger.info("Buscando vehiculo con marca: {}", marca);
		return repo.findByMarca(marca);
	}
	
	@Override
	public List<Vehiculo> buscarPorAnio(Integer anio){
		logger.info("Buscando vehiculo del año: {}", anio);
		return repo.findByAnio(anio);
	}
	
	@Override
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
	
	@Override
	public Vehiculo crearVehiculoDesdeDTO(VehiculoDTO dto) {
		
		Vehiculo v = new Vehiculo();
		
		v.setMatricula(dto.getMatricula());
		v.setMarca(dto.getMarca());
		v.setModelo(dto.getModelo());
		try {
			v.setAnio(dto.getAnio() != null && !dto.getAnio().isEmpty() ? Integer.parseInt(dto.getAnio()) : Year.now().getValue());
		} catch(NumberFormatException e) {
			v.setAnio(Year.now().getValue());
		}
		v.setKm(parseFloatOrDefault(dto.getKm(), 0f));
		v.setValorCompra(parseFloatOrDefault(dto.getValorCompra(), 0f));
		v.setValorVenta(parseFloatOrDefault(dto.getValorVenta(), 0f));
		v.setReparaciones(new ArrayList<>());
		
		return v;
	}
	
	@Override
	public String buscarTotalVehiculos() {
		
		List<Vehiculo>vehiculosExistentes = repo.findAll();
		String total = String.valueOf(vehiculosExistentes.size());
		
		return total;
	}
	
	private Float parseFloatOrDefault(String s, float defaultValue) {
		try {
			return s != null && !s.isEmpty() ? Float.parseFloat(s) : defaultValue;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

}
