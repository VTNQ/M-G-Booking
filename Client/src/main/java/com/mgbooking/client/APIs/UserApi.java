package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.AccountDto;
import com.mgbooking.client.DTO.RegisterUser;
import com.mgbooking.client.DTO.ResultApi;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserApi {
    @POST("/registerUser")
    Call<ResultApi>RegisterUser(@Body RegisterUser registerUser);
    @GET("/auth")
    Call<AccountDto>GetAccount(@Header("Authorization") String authorization);
    @PUT("/auth/UpdateAccount")
    Call<Object>UpdateAccount(@Header("Authorization") String authorization, @Body AccountDto accountDto);

}
