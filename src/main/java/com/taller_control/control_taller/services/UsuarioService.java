package com.taller_control.control_taller.services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taller_control.control_taller.models.Roles;
import com.taller_control.control_taller.models.Usuario;
import com.taller_control.control_taller.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	
	private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	private final UsuarioRepository repo;
	private final PasswordEncoder encoder;

	public UsuarioService(UsuarioRepository repo, PasswordEncoder passEncoder) {
		this.repo = repo;
		this.encoder = passEncoder;
	}
	
	public Usuario registrarUsuario(Usuario usuario) {
		String password = usuario.getPassword();
		String passEncode = encoder.encode(password);
		usuario.setPassword(passEncode);
		usuario.setRol(Roles.ROLE_CLASSIC);
		usuario.setEnabled(true);
		usuario.setCreatedAt(LocalDateTime.now());
		
		repo.save(usuario);
		return usuario;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username.isBlank()) {
			throw new NullPointerException("El nombre de usuario no es correcto");
		}
		Usuario u = repo.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
		
		return new User(
				u.getUsername(),
				u.getPassword(),
				u.getEnabled(),
				true,
				true,
				true,
				List.of(
						new SimpleGrantedAuthority(u.getRol().name())
						)
				);
	}
	
	public boolean validarCredenciales(String username, String password) {
		logger.info("Usuario: {}, Contraseña: {}", username, password);
					
		
		return repo.findByUsername(username)
				.map(user -> encoder.matches(password, user.getPassword()))
				.orElse(false);
	}
}
