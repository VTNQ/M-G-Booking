package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.Country;
import com.mgbooking.client.DTO.ResultApi;
import org.springframework.data.domain.Page;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface CountryApi {
    @GET("/Country")
    Call<List<Country>> getAllCountries(@Header("Authorization") String authorization);

    @POST("/Country/CreateCountry")
    public Call<ResultApi> CreateCountry(@Header("Authorization") String authorization, @Body Country country);

    @GET("/Country/FindByCountry/{id}")
    Call<Country> FindByCountry(@Header("Authorization") String authorization, @Path("id") long id);
    @PUT("/Country/UpdateCountry")
    Call<ResultApi>UpdateCountry(@Header("Authorization") String authorization, @Body Country country);
    @DELETE("/Country/DeleteCountry/{id}")
    Call<ResultApi>DeleteCountry(@Header("Authorization") String authorization, @Path("id") long id);
}

