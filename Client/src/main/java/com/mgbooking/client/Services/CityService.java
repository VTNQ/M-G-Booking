package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.City;
import com.mgbooking.client.DTO.CityDTO;

import java.util.List;

public interface CityService {
    public boolean CreateCity(String token, CityDTO cityDTO);
    public CityDTO FindCityById(String token, int id);
    public boolean UpdateCity(String token,CityDTO cityDTO);
    public boolean DeleteCity(String token,int id);
    public List<City>findAllCities(String token,int id);
}
