package com.pecuniaPay.registrationLogin.entities;

import java.io.Serializable;

public class JwtResponse implements Serializable {
	String token;

	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	public JwtResponse() {
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
