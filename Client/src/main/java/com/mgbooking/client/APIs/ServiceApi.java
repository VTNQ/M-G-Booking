package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.Service.Service;
import com.mgbooking.client.DTO.Service.ServiceDTO;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ServiceApi {
    @GET("/Service/FindAll/{id}")
    Call<List<Service>> FindAll(@Header("Authorization")String auth, @Path("id") int id);
    @POST("/Service/add")
    Call<Object>add(@Header("Authorization")String auth, @Body ServiceDTO service);
    @GET("/Service/findById/{id}")
    Call<ServiceDTO>findById(@Header("Authorization")String auth, @Path("id") int id);
}
