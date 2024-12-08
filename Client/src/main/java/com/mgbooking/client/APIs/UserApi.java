package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.Account.AccountAdmin;
import com.mgbooking.client.DTO.Account.RegisterAdmin;
import com.mgbooking.client.DTO.AccountDto;
import com.mgbooking.client.DTO.RegisterUser;
import com.mgbooking.client.DTO.ResultApi;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface UserApi {
    @GET("/GetAccountAdmin")
    Call<List<AccountAdmin>>GetAccountAdmin(@Header("Authorization")String authorization);
    @POST("/CreateAdminAccount")
    Call<Object>CreateAdminAccount(@Header("Authorization")String authorization,@Body RegisterAdmin registerAdmin);
    @POST("/registerUser")
    Call<ResultApi>RegisterUser(@Body RegisterUser registerUser);
    @GET("/auth")
    Call<AccountDto>GetAccount(@Header("Authorization") String authorization);
    @PUT("/auth/UpdateAccount")
    Call<Object>UpdateAccount(@Header("Authorization") String authorization, @Body AccountDto accountDto);

}
