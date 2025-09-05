package com.taller_control.control_taller.services;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
	
	private final Map<String, String> usuariosValidos = Map.of(
			"abel", "1234",
			"admin", "admin"
			);
	
	@Override
	public boolean validarCredentials(String usuario, String pass) {
		return usuariosValidos.containsKey(usuario) && usuariosValidos.get(usuario).equals(pass);
	}

}
