package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.CityDTO;

public interface CityService {
    public boolean CreateCity(String token, CityDTO cityDTO);
    public CityDTO FindCityById(String token,int id);
    public boolean UpdateCity(String token,CityDTO cityDTO);
    public boolean DeleteCity(String token,int id);
}
