package com.ecommerce.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

	@Email
	private String email;

	@NotBlank
	private String password;

	public LoginRequest() {
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}