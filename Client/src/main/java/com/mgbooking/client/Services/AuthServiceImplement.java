package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.APIs.LoginApi;
import com.mgbooking.client.Configuration.JwtUtil;
import com.mgbooking.client.DTO.LoginDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImplement  implements  AuthService{
    @Autowired
    private  JwtUtil jwtUtil;
    @Override
    public String login(LoginDTO loginDTO, HttpSession session) {
        try {
            LoginApi loginApi= ApiClient.getRetrofit().create(LoginApi.class);
            String token=loginApi.Login(loginDTO).execute().body().get("AccessToken");
            if(token!=null){
                session.setAttribute("accessToken", token);

                if(jwtUtil.extractRoles(token).contains("ROLE_ADMIN")){
                    return "redirect:/admin";
                }else if(jwtUtil.extractRoles(token).contains("ROLE_USER")){
                    return "redirect:/Home";
                }else{
                    return "redirect:/login";
                }
            }else{
                throw new RuntimeException("Token không hợp lệ.");
            }
        }catch (Exception e) {
            return null;
        }
    }
}