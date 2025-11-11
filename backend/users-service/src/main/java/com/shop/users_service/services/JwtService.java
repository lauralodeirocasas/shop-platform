package com.shop.users_service.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    // Clave secreta (mínimo 32 chars para HS256). Se inyecta desde application.properties o variable de entorno.
    private final Key signingKey;

    // Minutos de validez del access token (15 por defecto)
    private final long accessTokenMinutes;

    //spring lee los valores desde aplication.properties
    public JwtService(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.access-token-minutes:15}") long accessTokenMinutes
    ) {

        //El algoritmo de cifrado (HS256) necesita una clave de al menos 32 caracteres.
        if (secret == null || secret.length() < 32) {
            throw new IllegalStateException("app.jwt.secret debe tener al menos 32 caracteres.");
        }
        //convierte la clave que pusimos en un objeto criptográfico
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenMinutes = accessTokenMinutes;
    }

    /**
     * Genera un JWT HS256 con subject = userId y claims adicionales (p.ej. email).
     * subject: normalmente el ID del usuario.
     * claims: información adicional (por ejemplo, email, nombre…).
     */
    public String generateToken(String subject, Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        long expMillis = now + (accessTokenMinutes * 60_000);

        return Jwts.builder()
                .setSubject(subject)                    // userId
                .addClaims(claims)                      // email, etc.
                .setIssuedAt(new Date(now))             // fecha de emisión
                .setExpiration(new Date(expMillis))     // fecha de expiración
                .signWith(signingKey, SignatureAlgorithm.HS256) // firma digita
                .compact(); // lo convierte en texto
    }
}

