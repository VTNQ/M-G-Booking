package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.RegisterOwnerDto;
import com.mgbooking.client.DTO.ResultApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OwnerApi {
    @POST("/registerOwner")
    Call<ResultApi>RegisterOwner(@Body RegisterOwnerDto registerOwnerDto);
}
