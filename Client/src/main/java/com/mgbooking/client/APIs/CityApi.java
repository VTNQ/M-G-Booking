package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.CityDTO;
import com.mgbooking.client.DTO.ResultApi;
import retrofit2.Call;
import retrofit2.http.*;

public interface CityApi {
    @POST("/City/CreateCity")
    Call<ResultApi>CreateCity(@Header("Authorization") String authorization, @Body CityDTO CityDto);
    @GET("/City/FindCity/{id}")
    Call<CityDTO>FindCityById(@Header("Authorization") String authorization, @Path("id") int id);
    @DELETE("/City/DeleteCity/{id}")
    Call<ResultApi>DeleteCityById(@Header("Authorization") String authorization, @Path("id") int id);
}
