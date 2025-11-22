package com.aot.be.configurations;

import com.aot.be.model.entities.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Getter
public class UserPrincipal implements UserDetails {
    private final Integer id;
    private final String email;
    private final String password;
    private final String role;
    private final Boolean isActive;
    private final String fullName;
    private final Collection<? extends GrantedAuthority> authorities;

    private UserPrincipal(Integer id,
                          String email,
                          String password,
                          String role,
                          Boolean isActive,
                          String fullName,
                          Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
        this.fullName = fullName;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user, String roleName) {
        String resolvedRole = Objects.requireNonNullElse(roleName, "USER");
        Collection<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + resolvedRole.toUpperCase())
        );
        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                resolvedRole,
                user.getIsActive(),
                user.getFullName(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(isActive);
    }
}
