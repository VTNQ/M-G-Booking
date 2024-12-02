package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.APIs.FlightApi;
import com.mgbooking.client.DTO.FlightDTO;
import com.mgbooking.client.DTO.FlightListDto;
import com.mgbooking.client.DTO.ResultFlightDTO;
import com.mgbooking.client.DTO.SearchFlightDTO;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<String, String> convertToQueryParams(SearchFlightDTO searchFlightDTO) {
        Map<String, String> searchParams = new HashMap<>();
        searchParams.put("departureTime", searchFlightDTO.getDepartureTime());
        searchParams.put("arrivalAirport", String.valueOf(searchFlightDTO.getArrivalAirport()));
        searchParams.put("departureAirport", String.valueOf(searchFlightDTO.getDepartureAirport()));


        return searchParams;
    }

    @Override
    public List<ResultFlightDTO> SearchFlights(int departureAirport, int arrivalAirport,
                                               String departureTime,
                                               String TypeFlight) {
        try {
            FlightApi flightApi=ApiClient.getRetrofit().create(FlightApi.class);
            Response<List<ResultFlightDTO>>response=flightApi.SearchFlight(departureAirport, arrivalAirport,
            departureTime,
            TypeFlight).execute();
            if(response.isSuccessful()){
                return response.body();
            }else{
                return  null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public BigDecimal FindMinPrice(String departureTime) {
        try {
            FlightApi flightApi=ApiClient.getRetrofit().create(FlightApi.class);
            Response<BigDecimal>response=flightApi.FindMinPirce(departureTime).execute();
            if(response.isSuccessful()){
                return response.body();
            }else{
                return null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ResultFlightDTO> SearchFlightsByArrivalTime(int departureAirport, int arrivalAirport, String departureTime, String ArrivalTime, String TypeFlight) {
        try {
            FlightApi flightApi=ApiClient.getRetrofit().create(FlightApi.class);
            Response<List<ResultFlightDTO>>response=flightApi.SearchFlightByArrivalTime(departureAirport,arrivalAirport,departureTime,ArrivalTime,TypeFlight).execute();
            if(response.isSuccessful()){
                return response.body();
            }else{
                return null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
