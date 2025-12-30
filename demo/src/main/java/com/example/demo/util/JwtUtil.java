package com.example.demo.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private final String SECRET="CodeIoTamilJwtSecretKeyForSpringBoot";
	private final long EXPIRATION=1000*60*15;//15 minutes expiring time
	private final Key secretKey=Keys
			.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
	
	@SuppressWarnings("deprecation")
	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
				.signWith(secretKey,SignatureAlgorithm.HS256)
				.compact();
	}
	public String extractEmail(String token) {
		return Jwts.parser()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	@SuppressWarnings("deprecation")
	public boolean validateJwtToken(String token) {
		try { 
			extractEmail(token);
		return true;
		}
		catch(JwtException exception) {
			return false;
		}
	}
}
