package com.taller_control.control_taller.services;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
	
	private final Map<String, String> usuariosValidos = Map.of(
			"abel", "1234",
			"admin", "admin"
			);
	
	public boolean validarCredenciales(String usuario, String pass) {
		return usuariosValidos.containsKey(usuario) && usuariosValidos.get(usuario).equals(pass);
	}

}
