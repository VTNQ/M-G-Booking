package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.APIs.LoginApi;
import com.mgbooking.client.APIs.UserApi;
import com.mgbooking.client.Configuration.JwtUtil;
import com.mgbooking.client.DTO.AccountDto;
import com.mgbooking.client.DTO.LoginDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AuthServiceImplement  implements  AuthService{
    @Autowired
    private  JwtUtil jwtUtil;
    public List<String> getTokensFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<String> tokensList = (List<String>) session.getAttribute("tokensList");

        if (tokensList != null) {
            return tokensList; // Return the list of tokens if available
        }
        return Collections.emptyList(); // Return an empty list if no tokens are found
    }
    @Override
    public String login(LoginDTO loginDTO, HttpServletResponse response,HttpServletRequest request) {
        try {
            LoginApi loginApi= ApiClient.getRetrofit().create(LoginApi.class);
            String token=loginApi.Login(loginDTO).execute().body().get("AccessToken");
            List<String> tokensList = getTokensFromSession(request);
            if (tokensList.size()<=0) {
                tokensList = new ArrayList<>();
            }

            // Add the generated jwt to the tokens list
            tokensList.add(token);  // Adding the current access token (JWT)

            // Store the updated list of tokens back in session
            HttpSession session = request.getSession();
            session.setAttribute("tokensList", tokensList);
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
                }else if(jwtUtil.extractRoles(token).contains("ROLE_OWNER")){
                    return  "redirect:/Owner";
                }else{
                    return null;
                }
            }else{
                throw new RuntimeException("Token không hợp lệ.");
            }
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public AccountDto FindByAccount(String token) {
        try {
            UserApi userApi= ApiClient.getRetrofit().create(UserApi.class);
            Response<AccountDto>response=userApi.GetAccount("Bearer " + token).execute();
            if(response.isSuccessful()){
                return response.body();
            }else{
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Object Logout(String token, HttpServletResponse response) {
        try {
            LoginApi loginApi=ApiClient.getRetrofit().create(LoginApi.class);
            Object object=loginApi.Logout(token).execute().body();
            if(object!=null){
                Cookie accessToken = new Cookie("accessToken", null);
                accessToken.setHttpOnly(true);
                accessToken.setPath("/");
                accessToken.setMaxAge(0);
                response.addCookie(accessToken);
                return object;
            }else {
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
