package com.ecommerce.authservice.service;

import com.ecommerce.authservice.dto.AuthResponse;
import com.ecommerce.authservice.dto.LoginRequest;
import com.ecommerce.authservice.dto.RegisterRequest;
import com.ecommerce.authservice.entity.Role;
import com.ecommerce.authservice.entity.User;
import com.ecommerce.authservice.repository.UserRepository;
import com.ecommerce.common.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;

	public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {

		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	@Override
	public void register(RegisterRequest request) {

		User user = new User(request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()),
				Role.valueOf(request.getRole()));

		userRepository.save(user);
	}

	@Override
	public AuthResponse login(LoginRequest request) {

		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("Invalid email"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {

			throw new RuntimeException("Invalid password");
		}

		String accessToken = jwtService.generateAccessToken(user.getEmail(), user.getRole().name());

		String refreshToken = jwtService.generateRefreshToken(user.getEmail());

		return new AuthResponse(accessToken, refreshToken);
	}

	@Override
	public AuthResponse refreshToken(String refreshToken) {

		if (!jwtService.isTokenValid(refreshToken)) {

			throw new RuntimeException("Invalid refresh token");
		}

		String email = jwtService.extractEmail(refreshToken);

		User user = userRepository.findByEmail(email).orElseThrow();

		String accessToken = jwtService.generateAccessToken(user.getEmail(), user.getRole().name());

		return new AuthResponse(accessToken, refreshToken);
	}
}