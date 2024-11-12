package com.mgbooking.server.Configurations;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthentication extends AbstractAuthenticationToken {
    private Claims claims;

    // Constructor to accept Claims and authorities (if necessary)
    public JWTAuthentication(Claims claims, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.claims = claims;
        setAuthenticated(true);
    }

    public Claims getClaims() {
        return claims;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return claims.getSubject(); // Typically, the "sub" claim is the principal (username)
    }
}
