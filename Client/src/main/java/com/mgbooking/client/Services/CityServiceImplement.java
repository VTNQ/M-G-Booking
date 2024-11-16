package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.APIs.CityApi;
import com.mgbooking.client.DTO.CityDTO;
import com.mgbooking.client.DTO.ResultApi;
import org.springframework.stereotype.Service;
import retrofit2.Response;

@Service
public class CityServiceImplement implements CityService{

    @Override
    public boolean CreateCity(String token, CityDTO cityDTO) {
        try{
            CityApi cityApi= ApiClient.getRetrofit().create(CityApi.class);
            Response<ResultApi> response=cityApi.CreateCity("Bearer " +token,cityDTO).execute();
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
    public CityDTO FindCityById(String token, int id) {
        try {
            CityApi cityApi= ApiClient.getRetrofit().create(CityApi.class);
            Response<CityDTO>response=cityApi.FindCityById("Bearer " +token,id).execute();
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
    public boolean UpdateCity(String token, CityDTO cityDTO) {
        try{
            CityApi cityApi= ApiClient.getRetrofit().create(CityApi.class);
            Response<ResultApi> response=cityApi.CreateCity("Bearer " +token,cityDTO).execute();
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
    public boolean DeleteCity(String token, int id) {
        try{
            CityApi cityApi= ApiClient.getRetrofit().create(CityApi.class);
            Response<ResultApi> response=cityApi.DeleteCityById("Bearer " +token,id).execute();
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
}
