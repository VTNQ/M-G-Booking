package com.aot.be.services;

import com.aot.be.configurations.JwtTokenProvider;
import com.aot.be.configurations.UserPrincipal;
import com.aot.be.model.dtos.reponse.LoginResponse;
import com.aot.be.model.dtos.reponse.UserProfileResponse;
import com.aot.be.model.dtos.request.LoginRequest;
import com.aot.be.model.dtos.request.RegisterRequest;
import com.aot.be.model.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public LoginResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        normalizeEmail(request.getEmail()),
                        request.getPassword()
                );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        String accessToken = jwtTokenProvider.generateToken(authentication);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .userId(principal.getId())
                .email(principal.getEmail())
                .fullName(principal.getFullName())
                .role(principal.getRole())
                .build();
    }

    @Transactional
    public LoginResponse register(RegisterRequest request) {
        String email = normalizeEmail(request.getEmail());
        if (userService.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
        }
        if (userService.existsByPhone(request.getPhone())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone already in use");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName().trim());
        user.setPhone(request.getPhone().trim());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());
        user.setAddress(request.getAddress());
        user.setIsActive(true);
        user.setAccountType(1);
        user.setCreateAt(Instant.now());

        User saved = userService.save(user);
        String roleName = userService.resolveRoleName(saved.getAccountType());
        String token = jwtTokenProvider.generateTokenFromEmail(
                saved.getEmail(),
                saved.getId().longValue(),
                roleName
        );

        return LoginResponse.builder()
                .accessToken(token)
                .userId(saved.getId())
                .email(saved.getEmail())
                .fullName(saved.getFullName())
                .role(roleName)
                .build();
    }

    public UserProfileResponse currentUser(UserPrincipal principal) {
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }
        return UserProfileResponse.builder()
                .id(principal.getId())
                .email(principal.getEmail())
                .fullName(principal.getFullName())
                .role(principal.getRole())
                .active(principal.isEnabled())
                .build();
    }

    private String normalizeEmail(String email) {
        if (!StringUtils.hasText(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
        }
        return email.trim().toLowerCase();
    }
}

