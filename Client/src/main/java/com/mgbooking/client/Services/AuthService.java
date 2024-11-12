package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.LoginDTO;
import jakarta.servlet.http.HttpSession;

public interface AuthService {
    public String login(LoginDTO loginDTO, HttpSession session);
}
