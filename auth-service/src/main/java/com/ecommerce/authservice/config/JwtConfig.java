package com.ecommerce.authservice.config;

import com.ecommerce.common.security.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long expiration;

	@Value("${jwt.refresh-expiration}")
	private long refreshExpiration;

	@Bean
	public JwtService jwtService() {
		return new JwtService(secret, expiration, refreshExpiration);
	}
}