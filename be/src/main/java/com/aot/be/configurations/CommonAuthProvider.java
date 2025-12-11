package com.aot.be.configurations;

import com.aot.be.services.AdminService;
import com.aot.be.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class CommonAuthProvider implements AuthenticationProvider {
    private final UserService userService;
    private final AdminService adminService;
    private final PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginKey = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();

        // thử login user trước (email)
        try {
            UserDetails user = userService.loadUserByUsername(loginKey);
            if (encoder.matches(rawPassword, user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            }
        } catch (Exception ignored) {}

        // thử login admin (username)
        try {
            UserDetails admin = adminService.loadUserByUsername(loginKey);
            if (encoder.matches(rawPassword, admin.getPassword())) {
                return new UsernamePasswordAuthenticationToken(admin, null, admin.getAuthorities());
            }
        } catch (Exception ignored) {}

        throw new BadCredentialsException("Invalid credentials!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
