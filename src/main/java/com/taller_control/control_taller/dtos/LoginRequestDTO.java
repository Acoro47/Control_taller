package com.taller_control.control_taller.dtos;

public class LoginRequestDTO {
	private String usuario;
	private String password;
	
	public LoginRequestDTO(String usuario, String password) {
		super();
		this.usuario = usuario;
		this.password = password;
	}

	public LoginRequestDTO() {
		super();
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
