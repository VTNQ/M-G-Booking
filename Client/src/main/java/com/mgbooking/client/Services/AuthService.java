package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.AccountDto;
import com.mgbooking.client.DTO.LoginDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    public String login(LoginDTO loginDTO, HttpServletResponse response, HttpServletRequest request) throws Exception;
    public AccountDto FindByAccount(String token);
}
