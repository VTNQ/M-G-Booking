package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.APIs.FlightApi;
import com.mgbooking.client.DTO.FlightDTO;
import com.mgbooking.client.DTO.FlightListDto;
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

    @Override
    public FlightListDto GetFlights(String token, int id) {
        try {
            FlightApi flightApi=ApiClient.getRetrofit().create(FlightApi.class);
            Response<FlightListDto>response=flightApi.FindFlight("Bearer " +token,id).execute();
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

    @Override
    public Object UpdateFlight(String token, FlightListDto flightDTO) {
        try {
            FlightApi flightApi=ApiClient.getRetrofit().create(FlightApi.class);
            Object object=flightApi.UpdateFlight("Bearer " + token,flightDTO).execute().body();
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
