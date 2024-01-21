package com.demo.stocks.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${stocks.app.jwtSecret}")
    private String jwtSecret;

    @Value("${stocks.app.jwtExpiry}")
    private int jwtExpiry;

    @Value("${stocks.app.jwtCookieName}")
    private String jwtCookieName;

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument: " + e.getMessage());
        }
        return false;
    }

    public Cookie generateJwtToken(String username) {
        Date issueTime = new Date();

        Cookie cookie = new Cookie(jwtCookieName,
                Jwts.builder()
                .setSubject(username)
                .setIssuedAt(issueTime)
                .setExpiration(new Date(issueTime.getTime() + this.jwtExpiry))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact());
        cookie.setMaxAge(jwtExpiry / 1000);
//        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        return cookie;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.jwtSecret));
    }

}
