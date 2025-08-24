package com.taller_control.control_taller.components;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtUtil {
	
	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final long EXPIRATION_TIME = 1000 * 60 * 60;
	
	public String generateToken(String usuario) {
		return Jwts.builder()
				.setSubject(usuario)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(key)
				.compact();
	}
	
	
	public boolean validateToken(String token, String username) {
		String tokenUsername = extractUsuario(token);
		return (tokenUsername.equals(username) && !isTokenExpired(token));
	
	}
	
	public String extractUsuario(String token) {
		return extractClaim(token, Claims::getSubject);
		
	}
	
	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claimsResolver.apply(claims);
				
	}
	
	private Claims extractAllClaims(String token) {
		try {
			return Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token)
					.getBody();
		}
		catch (ExpiredJwtException e) {
			System.out.println("Token expirado:" + e.getMessage());
			throw e;
		}
		catch (UnsupportedJwtException  e) {
			System.out.println("Token no soportado:" + e.getMessage());
			throw e;
		}
		catch (MalformedJwtException e) {
	        // Token mal formado
	        System.out.println("⚠️ Token mal formado: " + e.getMessage());
	        throw e;
	    } catch (SecurityException e) {
	        // Fallo en la firma
	        System.out.println("⚠️ Firma inválida: " + e.getMessage());
	        throw e;
	    } catch (IllegalArgumentException e) {
	        // Token vacío o null
	        System.out.println("⚠️ Token ilegal: " + e.getMessage());
	        throw e;
	    }
	}

}
