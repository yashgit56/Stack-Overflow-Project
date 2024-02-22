package com.challengers.dtos;

import lombok.Data;

@Data
public class RegisterDTO {
	
	private Long id ;
	
	private String Username ;
	
	private String email ;
	
	private String password ;

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
