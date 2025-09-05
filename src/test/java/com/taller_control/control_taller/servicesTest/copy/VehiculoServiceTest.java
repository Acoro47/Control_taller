package com.taller_control.control_taller.servicesTest.copy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.any;

import com.taller_control.control_taller.models.Vehiculo;
import com.taller_control.control_taller.repositories.VehiculoRepository;
import com.taller_control.control_taller.services.VehiculoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class VehiculoServiceTest {
	
	@Mock
	private VehiculoRepository repo;
	
	@InjectMocks
	private VehiculoServiceImpl service;
	
	private Vehiculo v;
	
	@BeforeEach
	void setup() {
		v = new Vehiculo();
		v.setId(1L);
		v.setMatricula("1234-KWL");
		v.setMarca("NISSAN");
		v.setAnio(2021);
	}
	
	// Patrón de nombre de los tests
	// metodoBajoPrueba_condicionEsperada_resultadoEsperado
	
	@Test
	void guardarVehiculo_ok() {
		when(repo.existsByMatricula("1234-KWL")).thenReturn(false);
		when(repo.save(v)).thenReturn(v);
		
		Vehiculo result = service.guardarVehiculo(v);
		
		assertNotNull(result);
		assertEquals("1234-KWL", result.getMatricula());
		verify(repo).save(v);
	}
	
	@Test
	void guardarVehiculo_matriculaDuplicada_lanzaExcepcion() {
		// Simulamos que la matricula ya existe en la bbdd
		when(repo.existsByMatricula("1234-KWL")).thenReturn(true);

		// Verificamos que el servicio lanza la excepción esperada
		assertThrows(IllegalArgumentException.class, ()-> service.guardarVehiculo(v));
		
		// Nunca debe guardar los datos en la bbdd
		verify(repo).existsByMatricula("1234-KWL");
		verify(repo, never()).save(any(Vehiculo.class));
		
	}
	
	@Test
	void guardarVehiculo_errorEnRepositorio_lanzaExcepcion() {
		// Simulamos que la matrícula no existe
		when(repo.existsByMatricula("1234-KWL")).thenReturn(false);
		
		// Simulamos que el save falla por un error interno
		when(repo.save(v)).thenThrow(new RuntimeException("Error en base de datos"));
		
		// Verificamos que el servicio propaga la excepción
		RuntimeException ex = assertThrows(RuntimeException.class, () -> service.guardarVehiculo(v));
		assertEquals("Error en base de datos", ex.getMessage());
		
		// Verificamos que se consultó la matrícula y se intentó guardar
		verify(repo).existsByMatricula("1234-KWL");
		verify(repo).save(v);
	}
	
	@Test
	void guardarVehiculo_matriculaVacia_lanzaExcepcion() {
		v.setMatricula("");
		assertThrows(IllegalArgumentException.class, ()-> service.guardarVehiculo(v));
		verify(repo, never()).save(any(Vehiculo.class));
	}

}
