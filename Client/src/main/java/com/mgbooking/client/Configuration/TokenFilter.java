package com.mgbooking.client.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class TokenFilter extends OncePerRequestFilter {
    private String secretKey = "sRbgDVJHhto1l0DxFi09N/5phc9FEEWfN4MQIzWKBEs=";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=getTokenFromCookies(request);
        if(token!=null){

            try {
                Claims claims= Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
                List<String>roles=claims.get("roles",List.class);
                if(roles!=null && !roles.isEmpty()){
                    List<SimpleGrantedAuthority> authorities=roles.stream().map(role->new SimpleGrantedAuthority(role)).toList();
                    JWTAuthentication authentication=new JWTAuthentication(claims,authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        filterChain.doFilter(request, response);
    }
    private String getTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}