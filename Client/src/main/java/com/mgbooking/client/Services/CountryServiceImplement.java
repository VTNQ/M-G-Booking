package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.APIs.CountryApi;
import com.mgbooking.client.DTO.Country;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.util.List;
@Service
public class CountryServiceImplement implements CountryService {
    @Override
    public List<Country> GetCountry() {
        try{
            CountryApi countryApi= ApiClient.getRetrofit().create(CountryApi.class);
            Response<List<Country>>response=countryApi.getAllCountries().execute();
            if(response.isSuccessful()){
                return response.body();
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
