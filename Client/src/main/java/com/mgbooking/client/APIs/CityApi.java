package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.CityDTO;
import com.mgbooking.client.DTO.ResultApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CityApi {
    @POST("/City/CreateCity")
    Call<ResultApi>CreateCity(@Header("Authorization") String authorization, @Body CityDTO CityDto);
}
