package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.AirPortApi;
import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.DTO.AirPortDTO;
import com.mgbooking.client.DTO.Airport;
import com.mgbooking.client.DTO.ResultApi;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.util.List;

@Service
public class AirPortServiceImplement implements AirPortService {
    @Override
    public Object CreateAirPort(String token,AirPortDTO AirPortDTO) {
        try {
            AirPortApi airPortApi= ApiClient.getRetrofit().create(AirPortApi.class);
            Object object=airPortApi.CreateAirPort("Bearer " + token,AirPortDTO).execute().body();
            if(object!=null){
                return object;
            }else{
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AirPortDTO FindByAirport(String token, int id) {
        try {
            AirPortApi airPortApi= ApiClient.getRetrofit().create(AirPortApi.class);
            Response<AirPortDTO>response=airPortApi.FindById("Bearer " +token,id).execute();
            if(response.isSuccessful()){
                return  response.body();
            }else {
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Object EditAirPort(String token, AirPortDTO AirPortDTO) {
        try {
            AirPortApi airPortApi= ApiClient.getRetrofit().create(AirPortApi.class);
            Object object=airPortApi.EditAirPort("Bearer " + token,AirPortDTO).execute().body();
            if(object!=null){
                return object;
            }else{
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Airport> FindAllByCountry(String token, int id) {
        try {
        AirPortApi airPortApi=ApiClient.getRetrofit().create(AirPortApi.class);
        Response<List<Airport>>response=airPortApi.FindAllByCountry("Bearer " +token,id).execute();
        if(response.isSuccessful()){
            return response.body();
        }else{
            return null;
        }
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
    }
}
