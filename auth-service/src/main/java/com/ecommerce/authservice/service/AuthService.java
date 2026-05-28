package com.ecommerce.authservice.service;

import com.ecommerce.authservice.dto.AuthResponse;
import com.ecommerce.authservice.dto.LoginRequest;
import com.ecommerce.authservice.dto.RegisterRequest;

public interface AuthService {

	void register(RegisterRequest request);

	AuthResponse login(LoginRequest request);

	AuthResponse refreshToken(String refreshToken);
}