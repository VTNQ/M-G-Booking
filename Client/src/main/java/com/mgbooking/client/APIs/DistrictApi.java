package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.District.District;
import com.mgbooking.client.DTO.District.DistrictDTO;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface DistrictApi {
    @GET("/District/GetDistrict/{id}")
    Call<List<District>> getDistrict(@Header("Authorization") String auth,@Path("id") int id);
    @POST("/District/add")
    Call<Object>AddDistrict(@Header("Authorization") String auth,@Body DistrictDTO district);
    @GET("/District/FindByDistrict/{id}")
    Call<DistrictDTO>FindByDistrict(@Header("Authorization") String auth,@Path("id") int id);
    @PUT("/District/update")
    Call<Object>UpdateDistrict(@Header("Authorization") String auth,@Body DistrictDTO district);
}
