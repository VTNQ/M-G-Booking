package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.District.District;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

import java.util.List;

public interface DistrictApi {
    @GET("/District/GetDistrict/{id}")
    Call<List<District>> getDistrict(@Header("Authorization") String auth,@Path("id") int id);
}
