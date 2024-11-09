package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.Country;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface CountryApi {
@GET("/Country")
    Call<List<Country>> getAllCountries();
}
