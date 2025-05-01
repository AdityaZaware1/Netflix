package com.ben.User.Service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtProvider {

    public static SecretKey secretKey = Keys.hmacShaKeyFor(JwtConfiguration.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        String roles = populateRoles(authorities);

        String jwt = Jwts
                .builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+  24 * 60 * 60 * 1000))
                .claim("email", auth.getName())
                .claim("authorities", roles)
                .signWith(secretKey)
                .compact();
        return jwt;
    }

    public void validateToken(final String token) {
        Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JwtConfiguration.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static String getEmailFromToken(String token) {

        token = token.substring(7);

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token).getBody();

        String email = String.valueOf(claims.get("email"));

        return email;
    }

    private static String populateRoles(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auth = new HashSet<>();

        for (GrantedAuthority a : authorities) {
            auth.add(a.getAuthority());
        }

        return String.join(",", auth);
    }
}
