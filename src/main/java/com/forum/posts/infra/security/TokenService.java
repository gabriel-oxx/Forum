package com.forum.posts.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.forum.posts.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
	@Value("${api.security.token.secret}")
	private String secret;

	public String generateToken(User user) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.create()
					       .withIssuer("ForumAPI")
					       .withSubject(user.getUsername())
					       .withExpiresAt(expirationMoment())
					       .sign(algorithm);
		}catch(JWTCreationException exception) {
			throw new RuntimeException("Erro ao tentar gerar o token JWT", exception);
		}
	}

	public String getSubject(String tokenJWT){
		try {
			var algorithm = Algorithm.HMAC256(secret);
return JWT.require(algorithm)
		       .withIssuer("ForumAPI")
		       .build()
		       .verify(tokenJWT)
		       .getSubject();
		}catch(JWTVerificationException exception) {
			throw new RuntimeException("Token JWT inválido ou expirado!");
		}
	}

	public Instant expirationMoment(){
return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00"));
	}
}
