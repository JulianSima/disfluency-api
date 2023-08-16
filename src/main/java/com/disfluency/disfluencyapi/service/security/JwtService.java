package com.disfluency.disfluencyapi.service.security;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class JwtService {

    @Value("${jwt.jwtSecret}")
    private String jwtSecret;

    @Value("${jwt.jwtSalt}")
    private String jwtSalt;

    @Value("${jwt.jwtExpirationMs}")
    private int jwtExpirationMs;

    @SneakyThrows
    public String generateJwtToken(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(generateKeyFromSecret())
                .compact();
    }

    @SneakyThrows
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(generateKeyFromSecret())
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(generateKeyFromSecret())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            log.error("Invalid JWT: {}", e.getMessage());
        }
        return false;
    }

    private Key generateKeyFromSecret() throws NoSuchAlgorithmException, InvalidKeySpecException {
        var secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        var spec = new PBEKeySpec(jwtSecret.toCharArray(), jwtSalt.getBytes(), 65536, 256);
        return new SecretKeySpec(secretKeyFactory.generateSecret(spec).getEncoded(), "HMACSHA256");
    }

    public Optional<String> parseJwtFromRequest(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return Optional.of(headerAuth.substring(7));
        }

        return Optional.empty();
    }
}
