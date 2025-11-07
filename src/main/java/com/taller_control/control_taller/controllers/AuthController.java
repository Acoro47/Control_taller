package com.taller_control.control_taller.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller_control.control_taller.components.JwtUtil;
import com.taller_control.control_taller.dtos.LoginRequestDTO;
import com.taller_control.control_taller.dtos.RegisterRequestDTO;
import com.taller_control.control_taller.models.TokenResponse;
import com.taller_control.control_taller.models.Usuario;
import com.taller_control.control_taller.services.UsuarioServiceImpl;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	
	private final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	//private final AuthService auth;
	private final JwtUtil jwtUtil;
	
	private final UsuarioServiceImpl userService;

	public AuthController(UsuarioServiceImpl auth, JwtUtil jwtUtil) {
		super();
		this.userService = auth;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequestDTO dto){
		
		Usuario u = new Usuario();
		u.setUsername(dto.getUsuario());
		u.setPassword(dto.getPassword());
		Usuario saved = userService.registrarUsuario(u);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(Map.of("id", saved.getId(), "username", saved.getUsername()));
	}
	
	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@RequestBody LoginRequestDTO loginRequest){
		logger.info("Usuario: {}, Contraseña: {}", loginRequest.getUsuario(), loginRequest.getPassword());
		
		boolean valido = userService.validarCredenciales(loginRequest.getUsuario(), loginRequest.getPassword());
		logger.info("Válido: {}", valido);
		
		if (valido) {
			String token = jwtUtil.generateToken(loginRequest.getUsuario());
			return ResponseEntity.ok(new TokenResponse(
					userService.loadUserByUsername(loginRequest.getUsuario()).getUsername(),
					"ok",
					token));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	@GetMapping("/secure/hello")
	public ResponseEntity<Map<String,String>> helloSecure(){
		Map<String, String> resp = new HashMap<>();
		resp.put("message", "Hola Abel, tu token es válido");
		return ResponseEntity.ok(resp);
	}

}
