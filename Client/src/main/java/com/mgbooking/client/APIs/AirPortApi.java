package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.AirPortDTO;
import com.mgbooking.client.DTO.ResultApi;
import retrofit2.Call;
import retrofit2.http.*;

public interface AirPortApi {
    @POST("/AirPort/CreateAirPort")
    Call<Object>CreateAirPort(@Header("Authorization") String authorization, @Body AirPortDTO AirPortDTO);
    @GET("/AirPort/FindById/{id}")
    Call<AirPortDTO>FindById(@Header("Authorization") String authorization, @Path("id") int id);
    @PUT("/AirPort/EditAirPort")
    Call<Object>EditAirPort(@Header("Authorization") String authorization, @Body AirPortDTO AirPortDTO);
}
