package com.aot.be.controllers;

import com.aot.be.configurations.UserPrincipal;
import com.aot.be.model.dtos.reponse.ApiResponse;
import com.aot.be.model.dtos.reponse.LoginResponse;
import com.aot.be.model.dtos.reponse.UserProfileResponse;
import com.aot.be.model.dtos.request.LoginRequest;
import com.aot.be.model.dtos.request.RegisterRequest;
import com.aot.be.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success("Login successful", response));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<LoginResponse>> register(@Valid @RequestBody RegisterRequest request) {
        LoginResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Register successful", response));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserProfileResponse>> currentUser(@AuthenticationPrincipal UserPrincipal principal) {
        UserProfileResponse profile = authService.currentUser(principal);
        return ResponseEntity.ok(ApiResponse.success("Current user profile", profile));
    }
}

