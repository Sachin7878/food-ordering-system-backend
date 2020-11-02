package com.app.model;

public class AuthResponseModel {
	private String token;

	public AuthResponseModel() {

	}

	public AuthResponseModel(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
