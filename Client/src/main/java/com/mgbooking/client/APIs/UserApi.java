package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.AccountDto;
import com.mgbooking.client.DTO.RegisterUser;
import com.mgbooking.client.DTO.ResultApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserApi {
    @POST("/registerUser")
    Call<ResultApi>RegisterUser(@Body RegisterUser registerUser);
    @GET("/auth")
    Call<AccountDto>GetAccount(@Header("Authorization") String authorization);

}
