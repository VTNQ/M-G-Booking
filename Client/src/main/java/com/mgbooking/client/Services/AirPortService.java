package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.AirPortDTO;

public interface AirPortService {
    public Object CreateAirPort(String token,AirPortDTO AirPortDTO);
    public AirPortDTO FindByAirport(String token,int id);
    public Object EditAirPort(String token,AirPortDTO AirPortDTO);
}
