package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.ListFlightDto;
import com.mgbooking.client.DTO.ResultApi;
import com.mgbooking.client.DTO.UpdateFlightDTO;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface AirlinesApi {

    @POST("/Airline/AddFlight")
    @Multipart
    Call<Object> AddFlight(@Header("Authorization") String auth,
                           @Part("name") RequestBody name,
                           @Part("country_id") RequestBody country_id,
                           @Part MultipartBody.Part image);
    @GET("/Airline/GetFlight")
    Call<List<ListFlightDto>> getFlight(@Header("Authorization") String auth);
    @GET("/Airline/FindFlight/{id}")
    Call<UpdateFlightDTO>FindFlight(@Header("Authorization") String auth,@Path("id")int id);
    @Multipart
    @POST("/Airline/UpdateFlight")
    Call<ResultApi>Update(@Header("Authorization") String auth, @Part("id")RequestBody id, @Part("name")RequestBody name,@Part("idCountry") RequestBody idCountry,@Part MultipartBody.Part image);
    @GET("/Airline/FindAirline/{id}")
    Call<List<ListFlightDto>>FindAirlineByCountry(@Header("Authorization") String auth,@Path("id")int id);
}
