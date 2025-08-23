package com.taller_control.control_taller.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller_control.control_taller.components.JwtUtil;
import com.taller_control.control_taller.dtos.LoginRequestDTO;
import com.taller_control.control_taller.services.AuthService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	
	private final AuthService auth;
	private final JwtUtil jwtUtil;

	public AuthController(AuthService auth, JwtUtil jwtUtil) {
		super();
		this.auth = auth;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest){
		
		boolean valido = auth.validarCredenciales(loginRequest.getUsuario(), loginRequest.getPassword());
		
		if (valido) {
			String token = jwtUtil.generateToken(loginRequest.getUsuario());
			return ResponseEntity.ok(token);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
		}
	}
	
	@GetMapping("/secure/hello")
	public ResponseEntity<String> helloSecure(){
		return ResponseEntity.ok("Hola Abel, tu token es válido");
	}

}
