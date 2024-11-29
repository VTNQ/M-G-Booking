package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.FlightDTO;
import com.mgbooking.client.DTO.FlightListDto;
import com.mgbooking.client.DTO.ResultFlightDTO;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface FlightApi {
    @POST("/Flight/CreateFlight")
    Call<Object>AddFlight(@Header("Authorization") String auth, @Body FlightDTO flightDTO);
    @GET("/Flight/FindFlight/{id}")
    Call<FlightListDto>FindFlight(@Header("Authorization")String auth, @Path("id") int id);
    @PUT("/Flight/UpdateFlight")
    Call<Object>UpdateFlight(@Header("Authorization") String auth, @Body FlightListDto flightDTO);
    @GET("/Flight/SearchFlight")
    Call<List<ResultFlightDTO>>SearchFlight(@Query("departureAirport")int departureAirport, @Query("arrivalAirport")int arrivalAirport,
                                            @Query("departureTime")String departureTime,
                                            @Query("TypeFlight")String TypeFlight);
}
