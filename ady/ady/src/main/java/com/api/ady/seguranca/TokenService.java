package com.api.ady.seguranca;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class TokenService {
private static final Key CHAVE_SECRETA = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

public String gerarToken(String usuario) {
    return Jwts.builder()
            .setSubject(usuario)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 dia de expiração
            .signWith(CHAVE_SECRETA, SignatureAlgorithm.HS256)
            .compact();
}
public boolean isTokenValido(String token) {
	try {
		Jwts.parserBuilder().setSigningKey(CHAVE_SECRETA).build().parseClaimsJws(token);
		return true;
	} catch (Exception e) {
		return false;
	}
	}
public String getUsuario(String token) {
	Claims claims = Jwts.parserBuilder().setSigningKey(CHAVE_SECRETA).build().parseClaimsJws(token).getBody();
	return claims.getSubject();
}
}

