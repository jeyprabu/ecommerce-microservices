package com.ecommerce.common.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtService {

	private final String secret;

	private final long expiration;

	private final long refreshExpiration;

	public JwtService(String secret, long expiration, long refreshExpiration) {

		this.secret = secret;
		this.expiration = expiration;
		this.refreshExpiration = refreshExpiration;
	}

	private SecretKey getSigningKey() {

		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateAccessToken(String email, String role) {

		return Jwts.builder().subject(email).claim("role", role).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expiration)).signWith(getSigningKey()).compact();
	}

	public String generateRefreshToken(String email) {

		return Jwts.builder().subject(email).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + refreshExpiration)).signWith(getSigningKey())
				.compact();
	}

	public String extractEmail(String token) {

		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload().getSubject();
	}

	public String extractRole(String token) {

		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload().get("role",
				String.class);
	}

	public boolean isTokenValid(String token) {

		try {

			Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);

			return true;

		} catch (Exception ex) {

			return false;
		}
	}
}