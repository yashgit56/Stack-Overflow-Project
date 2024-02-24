package com.challengers.dtos;

public class AuthenticationResponse {
	private boolean isAuthenticated ;
	

	public AuthenticationResponse() {
		super();
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	
}
