package com.mgbooking.server.Configurations;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JWTTokenFilter extends OncePerRequestFilter {
    private String secretKey = "sRbgDVJHhto1l0DxFi09N/5phc9FEEWfN4MQIzWKBEs="; // Replace with your secret key

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        HttpSession session = request.getSession();

        // Retrieve the tokensList from session
        List<String> tokensList = (List<String>) session.getAttribute("tokensList");

        // If the Authorization header contains a Bearer token
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // Extract the token after "Bearer "

            // Check if the token is already in the tokensList
            boolean isTokenInList = tokensList != null && tokensList.contains(token);

            if (isTokenInList) {
                // Token is found in the tokensList, so process it
                try {
                    Claims claims = extractAllClaims(token);

                    // Extract roles from claims (assuming it's a list of roles)
                    List<String> roles = claims.get("roles", List.class); // roles should be stored as a list of strings

                    // Convert roles into SimpleGrantedAuthorities
                    if (roles != null && !roles.isEmpty()) {
                        List<SimpleGrantedAuthority> authorities = roles.stream()
                                .map(role -> new SimpleGrantedAuthority(role)) // Convert each role to a SimpleGrantedAuthority
                                .toList();

                        // Set authentication with roles and claims
                        JWTAuthentication authentication = new JWTAuthentication(claims, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }

                } catch (Exception e) {
                    // Handle token parsing errors here
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token: " + e.getMessage());
                    return;
                }
            }
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }

    // Method to extract claims from JWT token
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
