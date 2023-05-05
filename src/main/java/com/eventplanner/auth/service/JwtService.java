package com.eventplanner.auth.service;

import com.eventplanner.auth.dto.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import com.eventplanner.auth.dto.AuthRequest;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {
    private final String SECRET = "hsS46sgg27dYHgfshgU4qm28ndk3ffA3885Ajsbd37naDasihd32FSawdjia2Q4rfn23ad218rFSDfwefSDGhjse324";

    public void validateToken(final String token) {
        Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }

    public Token generateToken(AuthRequest authRequest) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", authRequest.getRole());
        return createToken(claims, authRequest.getUsername());
    }

    private Token createToken(Map<String, Object> claims, String userName) {

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

        return new Token(token);
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
