package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.APIs.DetailFlightApi;
import com.mgbooking.client.DTO.DetailFlight;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.util.List;
@Service
public class DetailFlightServiceImplement implements DetailFlightService{
    @Override
    public List<DetailFlight> findDetail(String token,int id) {
        try {
            DetailFlightApi detailFlightApi= ApiClient.getRetrofit().create(DetailFlightApi.class);
            Response<List<DetailFlight>>response=detailFlightApi.getDetailFlight("Bearer " +token,id).execute();
            if(response.isSuccessful()){
                return response.body();
            }else{
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
