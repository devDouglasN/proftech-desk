package com.douglas.proftechdesk.security;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {

	@Value("${jwt.secret}")
	private String secretPhrase;

	public String generateToken(UserSS userSS) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secretPhrase);
			String token = JWT.create()
					.withIssuer("proftechdesk")
					.withSubject(userSS.getUsername())
					.withExpiresAt(genExpirationDate())
					.sign(algorithm);
			
			return token;
			
		} catch (JWTCreationException e) {
			throw new RuntimeException("Error while generating token ", e);
		}
	}

	private Date genExpirationDate() {
		return Date.from(LocalDateTime.now().plusMinutes(180).toInstant(ZoneOffset.of("-03:00")));
	}

	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secretPhrase);
			return JWT.require(algorithm).withIssuer("proftechdesk").build().verify(token).getSubject();
		} catch (JWTVerificationException e) {
			return "";
		}
	}
}
