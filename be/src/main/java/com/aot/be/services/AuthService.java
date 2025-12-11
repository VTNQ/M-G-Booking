package com.aot.be.services;

import com.aot.be.configurations.UserPrincipal;
import com.aot.be.model.dtos.reponse.LoginResponse;
import com.aot.be.model.dtos.reponse.UserProfileResponse;
import com.aot.be.model.dtos.request.LoginRequest;
import com.aot.be.model.dtos.request.RegisterRequest;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    LoginResponse register(RegisterRequest request);

    UserProfileResponse currentUser(UserPrincipal principal);
}

