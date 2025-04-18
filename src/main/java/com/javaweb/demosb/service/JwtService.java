package com.javaweb.demosb.service;

import com.javaweb.demosb.dto.CustomUserDetails;
import com.javaweb.demosb.entity.UserEntity;
import com.javaweb.demosb.exception.BusinessException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Slf4j
@Service
public class JwtService {
    @Value("${jwt.token.secret}")
    private String secret;

    private final long MillisInHour = 60 * 60 * 1000;
    private final long jwtExpiration = 12 * MillisInHour;

    public String generateToken(UserEntity user) {
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getUserId());
            claims.put("username", user.getUserName());
            claims.put("role", user.getRole());
            Date now = new Date();
            Date expiration = new Date(now.getTime() + jwtExpiration);
            return Jwts.builder()
                    .subject(user.getUserName())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(expiration)
                    .signWith(getSignInKey())
                    .compact();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

    public boolean isTokenValid(String jwt, CustomUserDetails customUserDetail) {
        final String username = extractUsername(jwt);
        return (username.equals(customUserDetail.getUsername()));
    }


    public SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
