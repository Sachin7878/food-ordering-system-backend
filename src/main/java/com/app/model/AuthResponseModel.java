package com.app.model;

import org.springframework.beans.factory.annotation.Value;

public class AuthResponseModel {

	private String token;
	private String expiresIn;

	public AuthResponseModel() {
		super();
	}

	public AuthResponseModel(String token) {
		super();
		this.token = token;
		this.expiresIn = "18000";
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	@Value("${spring.expiresInSeconds}")
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
