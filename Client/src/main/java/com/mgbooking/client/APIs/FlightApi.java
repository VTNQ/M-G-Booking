package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.FlightDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FlightApi {
    @POST("/Flight/CreateFlight")
    Call<Object>AddFlight(@Header("Authorization") String auth, @Body FlightDTO flightDTO);
}
