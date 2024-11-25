package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.AirPortDTO;

import java.util.List;

public interface AirPortService {
    public Object CreateAirPort(String token,AirPortDTO AirPortDTO);
    public AirPortDTO FindByAirport(String token,int id);
    public Object EditAirPort(String token,AirPortDTO AirPortDTO);
    public List<AirPortDTO>FindAllByCountry(String token,int id);
}
