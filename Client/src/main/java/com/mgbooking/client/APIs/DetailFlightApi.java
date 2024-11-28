package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.DetailFlight;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface DetailFlightApi {
    @GET("/DetailFlight/{id}")
    Call<List<DetailFlight>> getDetailFlight(@Header("Authorization") String auth, @Path("id") int id);


}
