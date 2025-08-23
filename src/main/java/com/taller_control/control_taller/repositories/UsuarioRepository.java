package com.taller_control.control_taller.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taller_control.control_taller.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByUsername(String username);

}
