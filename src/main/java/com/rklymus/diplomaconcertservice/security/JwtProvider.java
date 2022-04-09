package com.rklymus.diplomaconcertservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {
    public static final String TOKEN_PREFIX = "Bearer ";
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .signWith(key)
                .compact();
//        Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
//        return Jwts.builder()
//                .setSubject(email)
//                .setExpiration(date)
//                .claim("role", role)
//                .signWith(key)
//                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("Token is invalid");
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
    }
}