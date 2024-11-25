package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.APIs.FlightApi;
import com.mgbooking.client.DTO.FlightDTO;
import org.springframework.stereotype.Service;
import retrofit2.Response;
@Service
public class FlightServiceImplement implements FlightService{
    @Override
    public Object CreateFlight(String token, FlightDTO flightDTO) {
       try {
           FlightApi flightApi= ApiClient.getRetrofit().create(FlightApi.class);
           Object object=flightApi.AddFlight("Bearer " + token,flightDTO).execute().body();
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
}
