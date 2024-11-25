package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.City;
import com.mgbooking.client.DTO.CityDTO;
import com.mgbooking.client.DTO.ResultApi;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface CityApi {
    @POST("/City/CreateCity")
    Call<ResultApi>CreateCity(@Header("Authorization") String authorization, @Body CityDTO CityDto);
    @GET("/City/FindCity/{id}")
    Call<CityDTO>FindCityById(@Header("Authorization") String authorization, @Path("id") int id);
    @DELETE("/City/DeleteCity/{id}")
    Call<ResultApi>DeleteCityById(@Header("Authorization") String authorization, @Path("id") int id);
    @GET("/City/{id}")
    Call<List<City>>findAllCity(@Header("Authorization") String authorization, @Path("id") int id);
}
