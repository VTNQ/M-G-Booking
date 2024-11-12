package com.mgbooking.client.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private String SecretKey="sRbgDVJHhto1l0DxFi09N/5phc9FEEWfN4MQIzWKBEs=";
    public List<String>extractRoles(String token) {
        Claims claims= Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody();
        return claims.get("roles",List.class);
    }
    private Claims extractClaims(String token) {
        // Replace with your secret key or public key (for RS256, for example)
        return Jwts.parser()
                .setSigningKey(SecretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validatetoken(String token) {
        try {
            Claims claims = extractClaims(token); // Extract claims from the token

            return !claims.getExpiration().before(new Date()); // Token is valid if expiration date is in the future
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


}
