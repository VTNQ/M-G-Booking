package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.APIs.LoginApi;
import com.mgbooking.client.Configuration.JwtUtil;
import com.mgbooking.client.DTO.LoginDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImplement  implements  AuthService{
    @Autowired
    private  JwtUtil jwtUtil;
    @Override
    public String login(LoginDTO loginDTO, HttpServletResponse response) {
        try {
            LoginApi loginApi= ApiClient.getRetrofit().create(LoginApi.class);
            String token=loginApi.Login(loginDTO).execute().body().get("AccessToken");
            if(token!=null){
                Cookie accessToken=new Cookie("accessToken",token);
                accessToken.setHttpOnly(true);
                accessToken.setPath("/");
                accessToken.setMaxAge(60 * 60 * 24);
                response.addCookie(accessToken);

                if(jwtUtil.extractRoles(token).contains("ROLE_ADMIN")){
                    return "redirect:/Admin/Home";
                }else if(jwtUtil.extractRoles(token).contains("ROLE_USER")){
                    return "redirect:/Home";
                }else if(jwtUtil.extractRoles(token).contains("ROLE_SUPERADMIN")){
                    return "redirect:/SuperAdmin/Home";
                }else{
                    return  null;
                }
            }else{
                throw new RuntimeException("Token không hợp lệ.");
            }
        }catch (Exception e) {
            return null;
        }
    }
}
