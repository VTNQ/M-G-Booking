package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.LoginDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

import java.util.Map;

public interface LoginApi {
    @POST("/auth/login")
    Call<Map<String,String>>Login(@Body LoginDTO loginDTO);
    @POST("/auth/logout")
    Call<Object>Logout(@Header("Authorization")String auth);
}
