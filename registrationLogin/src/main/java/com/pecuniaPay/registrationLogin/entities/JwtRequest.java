package com.pecuniaPay.registrationLogin.entities;

import java.io.Serializable;

public class JwtRequest implements Serializable{
	String userName;
	String passsword;
	public JwtRequest() {
		
		// TODO Auto-generated constructor stub
	}
	public JwtRequest(String userName, String passsword) {
		super();
		this.userName = userName;
		this.passsword = passsword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasssword() {
		return passsword;
	}
	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}
	
	
	

}
