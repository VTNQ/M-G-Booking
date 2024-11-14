package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.APIs.CountryApi;
import com.mgbooking.client.DTO.Country;
import com.mgbooking.client.DTO.ResultApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.awt.print.Pageable;
import java.util.List;
@Service
public class CountryServiceImplement implements CountryService {
    @Override
    public List<Country> GetCountry(String token) {
        try{
            CountryApi countryApi= ApiClient.getRetrofit().create(CountryApi.class);
            Response<List<Country>>response=countryApi.getAllCountries("Bearer " +token).execute();
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

    @Override
    public boolean CreateCountry(String token,Country country) {
        try {
            CountryApi countryApi=ApiClient.getRetrofit().create(CountryApi.class);
            Response<ResultApi>response=countryApi.CreateCountry("Bearer " + token,country).execute();
            if(response.isSuccessful()){
                return response.body().isResult();
            }else{
                return  false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Country FindCountry(String token, int id) {
        try {
            CountryApi countryApi=ApiClient.getRetrofit().create(CountryApi.class);
            Response<Country>response=countryApi.FindByCountry("Bearer " + token,id).execute();
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

    @Override
    public boolean UpdateCountry(String token, Country country) {
        try {
            CountryApi countryApi=ApiClient.getRetrofit().create(CountryApi.class);
            Response<ResultApi>response=countryApi.UpdateCountry("Bearer " + token,country).execute();
            if(response.isSuccessful()){
                return response.body().isResult();
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean DeleteCountry(String token, int id) {
        try {
        CountryApi countryApi=ApiClient.getRetrofit().create(CountryApi.class);
        Response<ResultApi>response=countryApi.DeleteCountry("Bearer " + token,id).execute();
        if(response.isSuccessful()){
            return response.body().isResult();
        }else{
            return false;
        }

        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }


}
