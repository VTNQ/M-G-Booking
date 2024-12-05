package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.AirPortDTO;
import com.mgbooking.client.DTO.Airport;

import java.util.List;

public interface AirPortService {
    public Object CreateAirPort(String token,AirPortDTO AirPortDTO);
    public AirPortDTO FindByAirport(String token,int id);
    public Object EditAirPort(String token,AirPortDTO AirPortDTO);
    public List<Airport>FindAllByCountry(String token, int id);
}
