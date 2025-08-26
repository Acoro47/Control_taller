package com.taller_control.control_taller.dtos;

public class RegisterRequestDTO {
	private String usuario;
	private String password;
	private String confirmarPass;
	
	public RegisterRequestDTO(String usuario, String password, String confirmarPass) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.confirmarPass = confirmarPass;
	}

	public RegisterRequestDTO() {
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

	public String getConfirmarPass() {
		return confirmarPass;
	}

	public void setConfirmarPass(String confirmarPass) {
		this.confirmarPass = confirmarPass;
	}
	
	

}
